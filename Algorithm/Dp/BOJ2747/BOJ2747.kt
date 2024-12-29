package Algorithm.Dp.BOJ2747

class BOJ2747 {
    fun solve() {
        val n = readln().toInt()
        val dp = LongArray(n + 1)
        dp[0] = 0L
        dp[1] = 1L
        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        println(dp[n])
    }
}

fun main() {
    BOJ2747().solve()
}