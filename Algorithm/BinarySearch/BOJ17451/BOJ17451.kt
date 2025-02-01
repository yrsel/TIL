package Algorithm.BinarySearch.BOJ17451

import java.io.StreamTokenizer

class BOJ17451 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val n = toInt()
        val arr = IntArray(n) { toInt() }

        println(bs(arr))
    }

    private fun bs(arr: IntArray): Long {
        if (arr.size == 1) return arr[0].toLong()

        var left = 1L
        var right = Long.MAX_VALUE
        var mid: Long
        while (left <= right) {
            mid = (left + right) / 2
            if (isSatisfied(mid, arr)) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun isSatisfied(num: Long, arr: IntArray): Boolean {
        var pace = if (num % arr[0] == 0L) num else (num / arr[0]) * arr[0]
        for (i in 1..<arr.size) {
            if (pace < arr[i]) return false
            pace = (pace / arr[i]) * arr[i]
        }
        return true
    }
}

fun main() {
    BOJ17451().solve()
}