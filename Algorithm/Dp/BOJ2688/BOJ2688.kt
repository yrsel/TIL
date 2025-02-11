package Algorithm.Dp.BOJ2688

import java.util.*

class BOJ2688 {
    fun solve() {
        val T = readln().toInt()
        val dp = Array(65) { LongArray(10) }
        for (r in 0..64) dp[r][0] = 1
        Arrays.fill(dp[1], 1)
        for (r in 2..64) {
            for (c in 1..9) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
            }
        }

        println(buildString {
            repeat(T) {
                appendLine(dp[readln().toInt()].sum())
            }
        })
    }
}

fun main() {
    BOJ2688().solve()
}