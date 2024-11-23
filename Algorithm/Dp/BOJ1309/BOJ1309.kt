package Algorithm.Dp.BOJ1309

class BOJ1309 {
    private val MOD = 9901

    fun solve() {
        val N = readln().toInt()
        val dp = Array(N) { IntArray(3) }
        dp[0][0] = 1
        dp[0][1] = 1
        dp[0][2] = 1
        for (i in 1..<N) {
            dp[i][0] += (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD
            dp[i][1] += (dp[i - 1][0] + dp[i - 1][2]) % MOD
            dp[i][2] += (dp[i - 1][0] + dp[i - 1][1]) % MOD
        }
        val sum = dp[N - 1].sum()
        println(sum % MOD)
    }
}

fun main() {
    BOJ1309().solve()
}