package Algorithm.BinarySearch.BOJ6236

import java.io.StreamTokenizer

class BOJ6236 {
    private lateinit var payOfDays: IntArray
    private var M = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        val N = toInt()
        M = toInt()
        payOfDays = IntArray(N) { toInt() }

        var left = payOfDays.max() - 1
        var right = 1_000_000_000
        while (left + 1 < right) {
            val mid = (left + right) / 2
            if (isSatisfied(mid)) {
                right = mid
            } else {
                left = mid
            }
        }
        println(right)
    }

    private fun isSatisfied(standard: Int): Boolean {
        var time = 1
        var money = standard
        payOfDays.forEach {
            if (money - it < 0) {
                money = standard - it
                ++time
            } else {
                money -= it
            }
        }
        return time <= M
    }
}

fun main() {
    BOJ6236().solve()
}