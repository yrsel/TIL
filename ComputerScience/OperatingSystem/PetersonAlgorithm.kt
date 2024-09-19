package ComputerScience.OperatingSystem

import java.util.concurrent.atomic.AtomicReferenceArray

private var turn = 0

//private val flag = BooleanArray(2)

private val flag = AtomicReferenceArray<Boolean>(2)
private var count = 0

/**
 * 기본 BooleanArray 사용할 경우, 데드락이 발생하거나 count가 0 -1 ,1 ,... 다양하게 결과를 출력된다.
 *
 * 원자성을 보장하는 Atomic자료구조를 활용해서 데이터 동기화를 보장할 수 있다.
 */
fun main() {
    val addThread = addLoop()
    val subThread = subLoop()
    addThread.start()
    subThread.start()
    addThread.join()
    subThread.join()
    println("count = ${count}")
}


private fun addLoop() = Thread {
    repeat(50000) {
        flag[0] = true
        turn = 1
        while (flag[1] && turn == 1);
        count++
        flag[0] = false
    }
}


private fun subLoop() = Thread {
    repeat(50000) {
        flag[1] = true
        turn = 0
        while (flag[0] && turn == 0);
        count--
        flag[1] = false
    }
}
