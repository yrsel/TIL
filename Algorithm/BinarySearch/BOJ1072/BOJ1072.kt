package Algorithm.BinarySearch.BOJ1072

import java.io.StreamTokenizer

class BOJ1072 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val X = toInt()
        val Y = toInt()
        val origin = Y * 100L / X
        var left = 0
        var right = 1_000_000_000
        while (left + 1 < right) {
            val mid = (left + right) / 2
            val percent = (Y + mid) * 100L / (X + mid)
            if (percent != origin) {
                right = mid
            } else {
                left = mid
            }
        }
        if (origin == (right + Y) * 100L / (right + X)) right = -1
        println(right)
    }
}

fun main() {
    BOJ1072().solve()
}