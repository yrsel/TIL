package Algorithm.BinarySearch.BOJ14627

import java.io.StreamTokenizer

class BOJ14627 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        val S = toInt()
        val C = toInt()
        val arr = IntArray(S) { toInt() }
        var left = 0
        var right = 1_000_000_000
        var sum = arr.fold(0L) { sum, it -> sum + it }

        while (left <= right) {
            val mid = (left + right) / 2
            if (mid == 0) break
            if (arr.isSatisfied(mid, C)) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        sum -= right.toLong() * C
        println(sum)
    }

    private fun IntArray.isSatisfied(unit: Int, standard: Int): Boolean {
        return standard <= this.fold(0) { sum, it -> sum + it / unit }
    }
}

fun main() {
    BOJ14627().solve()
}