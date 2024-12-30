package Algorithm.Graph.BOJ3010

class BOJ3010 {
    private val dir = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))

    fun solve() = with(System.`in`.bufferedReader()) {
        val map = Array(7) { i ->
            val str = readLine()
            str.replace(' ', 'x').toCharArray()
        }

        var result = 0
        for (r in 0..<7) {
            for (c in 0..<7) {
                if (map[r][c] != 'o') continue
                for (d in 0..<4) {
                    val blankCandidateR = r + dir[d][0] * 2
                    val blankCandidateC = c + dir[d][1] * 2
                    if (isOut(blankCandidateR, blankCandidateC)) continue
                    val chipCandidateR = r + dir[d][0]
                    val chipCandidateC = c + dir[d][1]
                    if (map[blankCandidateR][blankCandidateC] == '.'
                        && map[chipCandidateR][chipCandidateC] == 'o'
                    ) result++
                }
            }
        }

        println(result)
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= 7 || c >= 7
}

fun main() {
    BOJ3010().solve()
}