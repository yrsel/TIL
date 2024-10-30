package Algorithm.BruteForce.BOJ21315

import java.io.StreamTokenizer
import kotlin.math.pow

class BOJ21315 {

    private var N = 0
    private val status = IntArray(2)
    private var result: IntArray? = null
    private lateinit var arr: IntArray

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        N = input()
        arr = IntArray(N) { input() }

        search(0)
        println("${result!![1]} ${result!![0]}")
    }

    private fun search(idx: Int) {
        result ?: run {
            if (idx == 2) {
                if (status.all { it.pow() <= N } && isSatisfied(status[0], status[1])) {
                    result = status.copyOf()
                }
                return
            }

            for (i in 1..9) {
                status[idx] = i
                search(idx + 1)
            }
        }
    }

    private fun isSatisfied(second: Int, first: Int): Boolean {
        var copyStatus = arr.toList()
        repeat(second) { time ->
            copyStatus = swap(time.pow(), copyStatus)
        }
        copyStatus = toBack(second.pow(), copyStatus)
        repeat(first) { time ->
            copyStatus = swap(time.pow(), copyStatus)
        }
        copyStatus = toBack(first.pow(), copyStatus)
        return copyStatus.filterIndexed { idx, num -> idx + 1 != num }.none()
    }

    private fun swap(_bound: Int, copyStatus: List<Int>): List<Int> {
        val bound = if (_bound == 0) 1 else _bound
        val first = copyStatus.subList(bound, bound * 2)
        val second = copyStatus.subList(0, bound)
        val third = copyStatus.subList(bound * 2, copyStatus.size)
        val swapped = mutableListOf<Int>()
        swapped.addAll(first)
        swapped.addAll(second)
        swapped.addAll(third)
        return swapped
    }

    private fun toBack(bound: Int, copyStatus: List<Int>): List<Int> {
        val copy = copyStatus.subList(0, bound)
        val swapped = copyStatus.subList(bound, copyStatus.size).toMutableList()
        swapped.addAll(copy)
        return swapped
    }

    private fun Int.pow(): Int {
        return 2.0.pow(this).toInt()
    }
}

fun main() {
    BOJ21315().solve()
}