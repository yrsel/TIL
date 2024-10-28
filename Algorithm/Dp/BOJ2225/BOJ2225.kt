package Algorithm.Dp.BOJ2225

import java.util.*

private const val MOD = 1_000_000_000

class BOJ2225 {
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val dp = Array(k + 1) { IntArray(n + 1) }
        Arrays.fill(dp[1], 1)
        for (r in 1..k) {
            dp[r][1] = r
        }
        for (r in 2..k) {
            for (c in 2..n) {
                dp[r][c] = (dp[r - 1][c] % MOD + dp[r][c - 1] % MOD) % MOD
            }
        }

        println(dp[k][n])
    }
}

fun main() {
    BOJ2225().solve()
}