package Kotlin.Coroutine.Example.Sync.Callback

import java.util.concurrent.CancellationException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

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

    private var activeSyncDisposable: Disposable? = null

    // disposal 캐싱하고 있다가 UI 종료시, onClear()로 취소 진행
    fun syncUserSetting() {
        Logger.d("SampleViewModel : syncUserSetting() - start")
        activeSyncDisposable = userSettingRepository.syncUserSetting(userId, object : Callback<UserSetting> {
            override fun onSuccess(value: UserSetting) {
                Logger.d("SampleViewModel : syncUserSetting() : success : ${value}")
            }

            override fun onFail(throwable: Throwable) {
                Logger.d("SampleViewModel : sycnUserSetting() : failed : ${throwable}")
            }
        })
    }

    fun onClear() {
        Logger.d("SampleViewModel : onClear()")
        activeSyncDisposable?.dispose()
        activeSyncDisposable = null
    }
}

class UserSettingRepository(
    private val localUserSettingDataSource: LocalUserSettingDataSource,
    private val remoteUserSettingDataSource: RemoteUserSettingDataSource
) {
    private val dispatcher: ExecutorService = Executors.newCachedThreadPool { runnable ->
        Thread(runnable).apply {
            isDaemon = true
        }
    }

    fun syncUserSetting(userId: String, callback: Callback<UserSetting>): Disposable {
        Logger.d("UserSettingRepository : syncUserSetting() - 원격 데이터 fetch ")

        return dispatcher.submit {
            remoteUserSettingDataSource.loadUserSetting(userId, object : Callback<UserSetting> {
                override fun onSuccess(value: UserSetting) {
                    if (Thread.interrupted()) {
                        callback.onFail(CancellationException())
                        return
                    }

                    val remoteUserSetting = value

                    Logger.d("UserSettingRepository : syncUserSetting() - 로컬 데이터 읽기")
                    localUserSettingDataSource.loadUserSetting(userId, object : Callback<UserSetting> {
                        override fun onSuccess(value: UserSetting) {
                            if (Thread.interrupted()) {
                                callback.onFail(CancellationException())
                                return
                            }

                            val localUserSetting = value
                            val updateUserSetting = localUserSetting.fold(remoteUserSetting)

                            Logger.d("UserSettingRepository : syncUserSetting() - 로컬 설정을 원격 설정 참고해서 업데이트 ")
                            localUserSettingDataSource.updateUserSetting(
                                updateUserSetting,
                                object : Callback<Unit> {
                                    override fun onSuccess(value: Unit) {
                                        if (Thread.interrupted()) {
                                            callback.onFail(CancellationException())
                                            return
                                        }

                                        Logger.d("UserSettingRepository : syncUserSetting() - Success")
                                        callback.onSuccess(updateUserSetting)
                                    }

                                    override fun onFail(throwable: Throwable) {
                                        Logger.d("UserSettingRepository : syncUserSetting() - Failed")
                                        callback.onFail(throwable)
                                    }
                                })
                        }

                        override fun onFail(throwable: Throwable) {
                            Logger.d("UserSettingRepository : syncUserSetting() - Failed : ${throwable}")
                            callback.onFail(throwable)
                        }
                    })
                }

                override fun onFail(throwable: Throwable) {
                    Logger.d("UserSettingRepository : syncUserSetting() - Failed : ${throwable}")
                    callback.onFail(throwable)
                }
            })
        }.asDisposable()
    }

}

interface UserSettingDataSource {
    fun loadUserSetting(userId: String, callback: Callback<UserSetting>)
    fun updateUserSetting(userSetting: UserSetting, callback: Callback<Unit>)
}

class LocalUserSettingDataSource : UserSettingDataSource {
    override fun loadUserSetting(userId: String, callback: Callback<UserSetting>) {
        Thread.sleep(100L)

        callback.onSuccess(
            UserSetting(
                userId = userId,
                primaryColor = "FFFF0000",
                secondaryColor = "FF0000FF"
            )
        )
    }

    override fun updateUserSetting(userSetting: UserSetting, callback: Callback<Unit>) {
        Thread.sleep(100L)
        callback.onSuccess(Unit)
    }
}

class RemoteUserSettingDataSource : UserSettingDataSource {
    override fun loadUserSetting(userId: String, callback: Callback<UserSetting>) {
        Thread.sleep(200L)

        callback.onSuccess(
            UserSetting(
                userId = userId,
                primaryColor = "FFFF0000",
                secondaryColor = "FF00FF00"
            )
        )
    }

    override fun updateUserSetting(userSetting: UserSetting, callback: Callback<Unit>) {
        throw UnsupportedOperationException()
    }

}

// callback
interface Callback<T> {
    fun onSuccess(value: T)
    fun onFail(throwable: Throwable)
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

interface Disposable {
    fun dispose()
}

// UI가 종료되면 작업을 취소하기 위해 Future 오브젝트를 감싸서 처리 진행
class FutureDisposable(private val future: Future<*>) : Disposable {
    override fun dispose() {
        if (future.isCancelled.not()) future.cancel(true)
    }
}

fun Future<*>.asDisposable(): Disposable {
    return FutureDisposable(this)
}