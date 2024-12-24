package Kotlin.Coroutine.Example.Sync.Rx

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val viewModel = SampleViewModel(
        userSettingRepository = UserSettingRepository(
            localUserSettingDataSource = LocalUserSettingDataSource(),
            remoteUserSettingDataSource = RemoteUserSettingDataSource()
        )
    )

    viewModel.syncUserSetting()

    Thread.sleep(5_000L)
    viewModel.onClear()
}


class SampleViewModel(private val userSettingRepository: UserSettingRepository) {

    private val userId = "TestUser#1"

    private var disposables: CompositeDisposable = CompositeDisposable()

    fun syncUserSetting() {
        Logger.d("SampleViewModel : syncUserSetting() - start")

        userSettingRepository.syncUserSetting(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.from(MockDispatchers.MAIN))
            .subscribe(
                { userSetting ->
                    Logger.d("SampleViewModel : syncUserSetting() : success : $userSetting")
                },
                { throwable ->
                    Logger.d("SampleViewModel : syncUserSetting() : failed : $throwable")
                })
            .addTo(disposables)
    }

    fun onClear() {
        Logger.d("SampleViewModel : onClear()")
        disposables.dispose()
    }
}

class UserSettingRepository(
    private val localUserSettingDataSource: LocalUserSettingDataSource,
    private val remoteUserSettingDataSource: RemoteUserSettingDataSource
) {
    fun syncUserSetting(userId: String): Single<UserSetting> {
        // Sync process
        return remoteUserSettingDataSource.loadUserSetting(userId)
            .zipWith(localUserSettingDataSource.loadUserSetting(userId)) { remoteUserSetting, localUserSetting ->
                localUserSetting.fold(
                    remoteUserSetting
                )
            }
            .flatMap { updatedUserSetting ->
                localUserSettingDataSource.updateUserSetting(updatedUserSetting)
                    .toSingle { updatedUserSetting }
            }
    }
}

interface UserSettingDataSource {
    fun loadUserSetting(userId: String): Single<UserSetting>
    fun updateUserSetting(userSetting: UserSetting): Completable
}

class LocalUserSettingDataSource : UserSettingDataSource {
    override fun loadUserSetting(userId: String): Single<UserSetting> {
        return Single.just(
            UserSetting(
                userId = userId,
                primaryColor = "FFFF0000",
                secondaryColor = "FF0000FF"
            )
        )
            .doOnSubscribe { Logger.d("LocalUserSettingDatasource : loadUserSetting()") }
            .delay(100L, TimeUnit.MILLISECONDS)
    }

    override fun updateUserSetting(userSetting: UserSetting): Completable {
        return Completable.complete()
            .doOnSubscribe { Logger.d("LocalUserSettingDatasource : updateUserSetting()") }
            .delay(100L, TimeUnit.MILLISECONDS)
    }
}

class RemoteUserSettingDataSource : UserSettingDataSource {
    override fun loadUserSetting(userId: String): Single<UserSetting> {
        return Single.just(
            UserSetting(
                userId = userId,
                primaryColor = "FFFF0000",
                secondaryColor = "FF00FF00"
            )
        )
            .doOnSubscribe { Logger.d("RemoteUserSettingDataSource : loadUserSetting()") }
            .delay(200, TimeUnit.MILLISECONDS)
    }

    override fun updateUserSetting(userSetting: UserSetting): Completable {
        return Completable.error(UnsupportedOperationException())
    }
}

// dto
data class UserSetting(
    val userId: String,
    val primaryColor: String,
    val secondaryColor: String
) {
    fun fold(userSetting: UserSetting): UserSetting {
        // 여기에서 [userSetting] 값들로  추가적인 로컬 환경 설정 진행 가능
        return UserSetting(userId, primaryColor, secondaryColor)
    }
}

// Util
object Logger {
    fun d(message: String) {
        println("${Thread.currentThread().name.padEnd(10)}  ${message}")
    }
}

object MockDispatchers {
    val MAIN = Executors.newFixedThreadPool(1) { runnable ->
        Thread(runnable).apply {
            name = "main"
            isDaemon = true
        }
    }
}