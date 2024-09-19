package ComputerScience.OperatingSystem

import kotlin.concurrent.thread

private var count = 0

/**
 * 전역변수 count를 두고
 * 카운트를 증가하는 addLoop()
 * 카운트를 감소시키는 subLoop()
 * 두 메서드는 각각의 개별적인 Thread에서 동작
 * context switching 발생하는 과정에서 데이터 동기화에 문제가 발생하여 전역변수 count의 값이 0이 아닌 값이 나올 수 있다.
 *
 * register에 count 값을 저장하고 count 에 더하기 연산 또는 빼기 연산을 진행한 뒤 context switching이 발생
 * 현재 증가 또는 감소한 count 값이 전역 count 메모리에 업데이트하지 못하고 register에만 존재한다.
 * context switching 발생해 작업을 시작할 쓰레드는 업데이트 되지 않은 count 전역변수의 값을 가져와서 사용하며 데이터 동기화가 깨지게 된다.
 */

fun main() {
    addLoop()
    subLoop()
    println("count = ${count}")
}

private fun addLoop() {
    thread(name = "plus 연산 스레드") {
        repeat(10000) {
            println("Thread.currentThread().name = ${Thread.currentThread().name}")
            count++
        }
    }
}

private fun subLoop() {
    thread(name = "minus 연산 스레드") {
        repeat(10000) {
            println("Thread.currentThread().name = ${Thread.currentThread().name}")
            count--
        }
    }.join()
}