package Algorithm.Graph.BOJ17435

import java.io.StreamTokenizer
import kotlin.math.log

class BOJ17435 {

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val m = toInt()
        val arr = IntArray(m + 1) { if (it != 0) toInt() else 0 }
        val lnM = log(MAX_RECUR, LOG_NUMBER).toInt() + 1
        val memo = Array(lnM) { IntArray(m + 1) }
        for (i in 1..m) {
            memo[0][i] = arr[i]
        }
        for (r in 1..<memo.size) {
            for (c in 1..m) {
                memo[r][c] = memo[r - 1][memo[r - 1][c]]
            }
        }

        println(buildString {
            repeat(toInt()) {
                var n = toInt()
                var x = toInt()
                while (true) {
                    val idx = log(n.toDouble(), LOG_NUMBER).toInt()
                    val minus = Math.pow(POW_NUMBER, idx.toDouble()).toInt()
                    n -= minus
                    x = memo[idx][x]
                    if (n == 0) {
                        appendLine(x)
                        break
                    }
                }
            }
        })
    }

    companion object {
        private const val MAX_RECUR = 500_000.0
        private const val LOG_NUMBER = 2.0
        private const val POW_NUMBER = 2.0
    }

}

fun main() {
    BOJ17435().solve()
}