package Algorithm.Dp.BOJ15990

class BOJ15990 {

    companion object {
        private const val MOD = 1_000_000_009
    }

    fun run() {
        val dp = Array(100_001) { LongArray(4) }
        initDp(dp)

        val sb = StringBuilder()
        repeat(readln().toInt()) {
            sb.appendLine(dp[readln().toInt()].reduce { sum, e -> (sum + e) % MOD })
        }
        println(sb)
    }

    private fun initDp(dp: Array<LongArray>) {
        dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0
        dp[2][1] = 0; dp[2][2] = 1; dp[2][3] = 0
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1

        for (i in 4..100_000) {
            dp[i][3] = (dp[i - 3][1] % MOD + dp[i - 3][2] % MOD) % MOD
            dp[i][2] = (dp[i - 2][1] % MOD + dp[i - 2][3] % MOD) % MOD
            dp[i][1] = (dp[i - 1][2] % MOD + dp[i - 1][3] % MOD) % MOD
        }
    }
}


fun main() {
    BOJ15990().run()
}
