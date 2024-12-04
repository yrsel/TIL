package Algorithm.BinarySearch.BOJ13702

import java.io.StreamTokenizer

class BOJ13702 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }

        val N = toInt()
        val K = toInt()
        val arr = IntArray(N) { toInt() }
        if (N == 0) {
            println(0)
            return
        }
        var left = 0L
        var right = Integer.MAX_VALUE - 1

        while (left <= right) {
            val mid = ((left + right) / 2).toInt()
            if (arr.isSatisfied(mid, K)) {
                left = (mid + 1).toLong()
            } else {
                right = mid - 1
            }
        }
        println(right)
    }

    private fun IntArray.isSatisfied(quantity: Int, K: Int): Boolean {
        var cnt = 0
        this.forEach { cnt += it / quantity }
        return K <= cnt
    }

}

fun main() {
    BOJ13702().solve()
}
