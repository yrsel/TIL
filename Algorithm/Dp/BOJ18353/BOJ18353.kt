package Algorithm.Dp.BOJ18353

import java.io.BufferedReader
import java.io.InputStreamReader

class BOJ18353 {
    fun run() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        val dp = IntArray(N) { 1 } // 각 위치가 가장 작은 값으로 인식하고 1로 초기화

        var max = 0
        for (i in 0..<N) {
            for (j in 0..<i) {
                if (arr[j] > arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1
                }
            }
            max = max.coerceAtLeast(dp[i])
        }
        println(N - max)
    }
}


fun main() {
    BOJ18353().run()
}