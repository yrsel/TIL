package ComputerScience.OperatingSystem

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Coroutine에서 제공하는 Mutex 사용
 * mutex.withLock을 사용할 경우 lock과 , unlock을 라이브러리에서 처리해준다.
 * mutex의 경우 지연함수인 suspend 키워드를 명시적으로 작성해줘야 한다.
 */
private var count = 0
private lateinit var mutex: Mutex

suspend fun main() {
    safeAdd()
    safeMinus()
    println("count = ${count}")
}


suspend fun safeAdd() {
    mutex = Mutex()

    repeat(1_000_000) {
        mutex.withLock {
            count++
        }
    }
}


suspend fun safeMinus() {
    mutex = Mutex()
    repeat(1_000_000) {
        mutex.withLock {
            count--
        }
    }
}