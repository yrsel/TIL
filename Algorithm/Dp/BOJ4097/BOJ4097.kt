package Algorithm.Dp.BOJ4097

class BOJ4097 {
    fun solve() {
        println(buildString {
            while (true) {
                val N = readln().toInt()
                if (N == 0) break
                val arr = IntArray(N) { readln().toInt() }
                val dp = IntArray(N) { Int.MIN_VALUE }
                for (i in dp.indices) {
                    if (i == 0) dp[0] = arr[i]
                    else {
                        dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]) // 이전까지의 최대에서 현재 값 더한 값 vs 현재 값
                    }
                }
                appendLine(dp.max())
            }
        })
    }
}

fun main() {
    BOJ4097().solve()
}