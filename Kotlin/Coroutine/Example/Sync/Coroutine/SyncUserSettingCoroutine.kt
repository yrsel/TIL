package Kotlin.Coroutine.Example.Sync.Coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {

    val viewModel = SampleViewModel(
        userSettingRepository = UserSettingRepository(
            localUserSettingDataSource = LocalUserSettingDataSource(),
            remoteUserSettingDataSource = RemoteUserSettingDataSource()
        )
    )

    viewModel.syncUserSetting()

    // Emulate user exit after 5 seconds
    delay(5000)
    viewModel.onClear()
    return@runBlocking
}

class SampleViewModel(private val userSettingRepository: UserSettingRepository) : CoroutineScope {

    override val coroutineContext: CoroutineContext = MockDispatchers.MAIN + SupervisorJob()
    private val userId = "TestUser#1"

    fun syncUserSetting() {
        Logger.d("SampleViewModel : syncUserSetting()")
        launch {
            Logger.d("SampleViewModel : syncUserSetting() start")
            val result = runCatching {
                withContext(Dispatchers.IO) {
                    userSettingRepository.syncUserSetting(userId)
                }
            }
            Logger.d("SampleViewModel : syncUserSetting() : result : $result")
        }
    }

    suspend fun onClear() {
        Logger.d("SampleViewModel : onClear()")
        coroutineContext[Job]?.cancelAndJoin()
    }
}

class UserSettingRepository(
    private val localUserSettingDataSource: LocalUserSettingDataSource,
    private val remoteUserSettingDataSource: RemoteUserSettingDataSource
) {
    suspend fun syncUserSetting(userId: String): UserSetting {
        // Sync process
        Logger.d("UserSettingRepository : syncUserSetting() - Fetch from remote data source")
        val remoteUserSetting = remoteUserSettingDataSource.loadUserSetting(userId)
        Logger.d("UserSettingRepository : syncUserSetting() - Load from local data source")
        val localUserSetting = localUserSettingDataSource.loadUserSetting(userId)
        Logger.d("UserSettingRepository : syncUserSetting() - Sync and Store to local data source")
        val updatedUserSetting = localUserSetting.fold(remoteUserSetting)
        return localUserSettingDataSource.updateUserSetting(updatedUserSetting)
    }
}

interface UserSettingDataSource {
    suspend fun loadUserSetting(userId: String): UserSetting
    suspend fun updateUserSetting(userSetting: UserSetting): UserSetting
}

class LocalUserSettingDataSource : UserSettingDataSource {
    override suspend fun loadUserSetting(userId: String): UserSetting {
        Logger.d("LocalUserSettingDataSource : loadUserSetting()")
        delay(100)
        return UserSetting(
            userId = userId,
            primaryColor = "FFFF0000",
            secondaryColor = "FF0000FF"
        )
    }

    override suspend fun updateUserSetting(userSetting: UserSetting): UserSetting {
        Logger.d("LocalUserSettingDataSource : updateUserSetting()")
        delay(100)
        return userSetting
    }
}

class RemoteUserSettingDataSource : UserSettingDataSource {
    override suspend fun loadUserSetting(userId: String): UserSetting {
        Logger.d("RemoteUserSettingDataSource : loadUserSetting()")
        delay(200)
        return UserSetting(
            userId = userId,
            primaryColor = "FFFF0000",
            secondaryColor = "FF00FF00"
        )
    }

    override suspend fun updateUserSetting(userSetting: UserSetting): UserSetting {
        throw UnsupportedOperationException()
    }
}

data class UserSetting(
    val userId: String,
    val primaryColor: String,
    val secondaryColor: String
) {
    fun fold(userSetting: UserSetting): UserSetting {
        // Local user setting could have more properties.
        return UserSetting(userId, primaryColor, secondaryColor)
    }
}

// Utilities for testing
object Logger {
    fun d(message: String) {
        println("${Thread.currentThread().name.padEnd(40)}\t$message")
    }
}

object MockDispatchers {
    val MAIN = Executors.newFixedThreadPool(1) { runnable ->
        Thread(runnable).apply {
            name = "main"
            isDaemon = true
        }
    }.asCoroutineDispatcher()
}