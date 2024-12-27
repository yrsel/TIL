package Algorithm.Dp.BOJ10164

class BOJ10164 {
    private lateinit var dp: Array<IntArray>

    fun solve() {
        val (R, C, K) = readln().split(" ").map { it.toInt() }
        dp = Array(R) { IntArray(C) }
        val checkPoint = if (K == 0) Pair(0, 0) else Pair((K - 1) / C, (K - 1) % C)
        dp[0][0] = 1
        move(0, 0, checkPoint.first, checkPoint.second)
        move(checkPoint.first, checkPoint.second, R - 1, C - 1)
        println(dp[R - 1][C - 1])
    }

    private fun move(startR: Int, startC: Int, maxR: Int, maxC: Int) {
        for (r in startR + 1..maxR) dp[r][startC] = dp[r - 1][startC]
        for (c in startC + 1..maxC) dp[startR][c] = dp[startR][c - 1]
        for (r in startR + 1..maxR) {
            for (c in startC + 1..maxC) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
            }
        }
    }
}

fun main() {
    BOJ10164().solve()
}