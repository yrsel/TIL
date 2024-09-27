package Algorithm.Dp.BOJ17485

import java.util.*

class BOJ17485 {
    private var C = 0
    fun run() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val R = st.nextToken().toInt()
        C = st.nextToken().toInt()
        val map = Array(R + 1) { IntArray(C + 2) }
        for (i in 1..R) {
            st = StringTokenizer(readLine(), " ")
            for (j in 1..C) {
                map[i][j] = st.nextToken().toInt()
            }
        }

        val dp = Array(3) { Array(R + 1) { IntArray(C+2) { 987_654_321 } } } // [0] : 왼쪽 아래, [1] : 아래 , [2] : 오른쪽 아래
        for(k in 0..<3) {
            for (i in 0..C + 1) {
                dp[k][0][i] = 0
            }
        }

        var result = 987_654_321
        for (i in 1..R) {
            for (j in 1..C) {
                dp[0][i][j] = Math.min(dp[1][i - 1][j + 1], dp[2][i - 1][j + 1]) + map[i][j]
                dp[1][i][j] = Math.min(dp[0][i - 1][j], dp[2][i - 1][j]) + map[i][j]
                dp[2][i][j] = Math.min(dp[0][i - 1][j - 1], dp[1][i - 1][j - 1]) + map[i][j]
            }
        }

        for (k in 0..<3) {
            for (i in 1..C) {
                result = result.coerceAtMost(dp[k][R][i])
            }
        }

        println(result)

    }
}

fun main() {
    BOJ17485().run()
}