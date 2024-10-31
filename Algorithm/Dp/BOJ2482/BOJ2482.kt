package Algorithm.Dp.BOJ2482

private const val MOD = 1_000_000_003

class BOJ2482 {

    fun run() {
        val N = readln().toInt()
        val K = readln().toInt()

        val dp = Array(K + 1) { IntArray(N + 1) }
        for (n in 1..N) {
            dp[1][n] = n
        }
        for (k in 2..K) {
            for (n in 4..N) {
                if (k * 2 <= n) {
                    if (k * 2 == n) {
                        dp[k][n] = 2
                    } else {
                        dp[k][n] = (dp[k - 1][n - 2] % MOD + dp[k][n - 1] % MOD) % MOD
                    }
                }
            }
        }
        println(dp[K][N])
    }
}

fun main() {
    BOJ2482().run()
}