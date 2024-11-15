package Algorithm.Dp.BOJ2133

class BOJ2133 {
    fun solve() {
        val n = readln().toInt()
        val dp = IntArray(31)

        dp[2] = 3; dp[4] = 11
        for (i in 6..30 step 2) {
            dp[i] += dp[i - 2] * 3 + 2
            var idx = i - 4
            while (idx != 0) {
                dp[i] += dp[idx] * 2
                idx -= 2
            }
        }
        println(dp[n])
    }
}

fun main() {
    BOJ2133().solve()
}