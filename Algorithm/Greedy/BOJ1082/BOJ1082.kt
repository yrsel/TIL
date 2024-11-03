package Algorithm.Greedy.BOJ1082

import java.io.StreamTokenizer
import java.math.BigInteger

class BOJ1082 {

    private lateinit var prices: IntArray
    private var M = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }

        val N = input()
        prices = IntArray(N) { input() }
        M = input()
        val memo = Array(M + 1) { "" }

        memo.forEachIndexed { idx, _ ->
            var find = ""
            for (i in prices.indices) {
                if (idx - prices[i] >= 0) {
                    val max = if (find.isEmpty()) BigInteger.ZERO else BigInteger(find)
                    val comp = if (i == 0) {
                        memo[idx - prices[i]] + i
                    } else {
                        i.toString() + memo[idx - prices[i]]
                    }
                    if (max <= BigInteger(comp)) {
                        find = comp
                    }
                }
            }
            memo[idx] = find
        }
        println(BigInteger(memo[M]))
    }
}

fun main() {
    BOJ1082().solve()
}