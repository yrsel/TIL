package Algorithm.BruteForce.BOJ6987

import java.util.*

class BOJ6987 {
    private val result = StringBuilder()
    private lateinit var st: StringTokenizer
    private lateinit var visited: Array<BooleanArray>
    private var flag = false
    private lateinit var map: Array<IntArray>
    fun run() {
        repeat(4) {
            st = StringTokenizer(readln(), " ")
            map = Array(6) { IntArray(3) }
            for (i in 0..<18) {
                map[i / 3][i % 3] = st.nextToken().toInt()
            }

            flag = false
            visited = Array(6) { BooleanArray(6) }
            dfs(0, 0, 0)

            if (flag) result.append("1 ")
            else result.append("0 ")
        }

        println(result.toString().trim())
    }

    private fun dfs(idx: Int, winOrDraw: Int, cnt: Int) {
        if (flag || idx == 6) return
        if (cnt == 15) {
            for (i in 0..<6) {
                for (j in 0..<3) {
                    if (map[i][j] != 0) return
                }
            }
            flag = true
            return
        }

        if (map[idx][winOrDraw] == 0) {
            when (winOrDraw) {
                0 -> dfs(idx, 1, cnt)
                1 -> dfs(idx + 1, 0, cnt)
            }
        }

        for (i in 0..<6) {
            if (idx == i || visited[idx][i] || map[i][2 - winOrDraw] == 0) continue
            switchStatus(idx, i, winOrDraw, true)
            dfs(idx, winOrDraw, cnt + 1)
            switchStatus(idx, i, winOrDraw, false)
        }

    }

    private fun switchStatus(teamA: Int, teamB: Int, winOrDraw: Int, flag: Boolean) {
        visited[teamA][teamB] = flag
        visited[teamB][teamA] = flag
        if (flag) {
            map[teamA][winOrDraw]--
            map[teamB][2 - winOrDraw]--
        } else {
            map[teamA][winOrDraw]++
            map[teamB][2 - winOrDraw]++
        }
    }


}

fun main() {
    BOJ6987().run()
}