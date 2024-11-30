package Algorithm.Dp.BOJ1633

import java.util.*

class BOJ1633 {
    private lateinit var scores: MutableList<IntArray>
    private lateinit var dp: Array<Array<IntArray>>
    private var size = 0

    fun solve() = with(System.`in`.bufferedReader()) {

        scores = mutableListOf()

        while (true) {
            val str = readLine()
            if (str.isNullOrBlank()) break
            val st = StringTokenizer(str, " ")
            val whiteScore = st.nextToken().toInt()
            val blackScore = st.nextToken().toInt()
            scores.add(intArrayOf(whiteScore, blackScore))
        }

        size = scores.size
        dp = Array(size) { Array(16) { IntArray(16) } }

        dp[0][0][1] = scores[0][0] // 백
        dp[0][1][0] = scores[0][1] // 흑

        var result = 0
        for (k in 1..<size) {
            for (i in 0..15) { // 흑
                for (j in 0..15) { // 백
                    var max = 0
                    if (j > 0) max = dp[k - 1][i][j - 1] + scores[k][0] // 백
                    if (i > 0) max = Math.max(max, dp[k - 1][i - 1][j] + scores[k][1]) // 흑
                    dp[k][i][j] = Math.max(max, dp[k - 1][i][j]) // 가장 큰 값으로 메모
                    if (i == 15 && j == 15) result = Math.max(result, dp[k][i][j]) // 모든 배치 완료
                }
            }
        }
        println(result)
//        println(dfs(0, 0, 0))
    }

//    private fun dfs(wIdx: Int, bIdx: Int, v: Int): Int {
//        if (v == size || (wIdx == 15 && bIdx == 15)) return 0
//
//        if (dp[v][wIdx][bIdx] != 0) return dp[v][wIdx][bIdx]
//
//        var max = Math.max(dp[v][wIdx][bIdx], dfs(wIdx, bIdx, v + 1))
//
//        if (wIdx < 15) max = Math.max(max, dfs(wIdx + 1, bIdx, v + 1) + scores[v][0])
//        if (bIdx < 15) max = Math.max(max, dfs(wIdx, bIdx + 1, v + 1) + scores[v][1])
//
//        dp[v][wIdx][bIdx] = max
//        return max
//    }
}


fun main() {
    BOJ1633().solve()
}