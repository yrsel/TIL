package Algorithm.BinarySearch.BOJ2343

import java.io.StreamTokenizer

class BOJ2343 {
    private var N = 0
    private var M = 0
    private lateinit var arr: IntArray

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        N = toInt()
        M = toInt()
        arr = IntArray(N) { toInt() }
        val max = arr.max()

        var left = max
        var right = 1_000_000_000
        while (left <= right) {
            val mid = (left + right) / 2
            if (isSatisfied(mid)) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        println(left)
    }

    private fun isSatisfied(standard: Int): Boolean {
        var cnt = 1
        var curSum = 0
        for (i in 0..<N) {
            if (curSum + arr[i] > standard) {
                curSum = 0
                cnt++
            }
            curSum += arr[i]
        }
        return cnt <= M
    }

}

fun main() {
    BOJ2343().solve()
}