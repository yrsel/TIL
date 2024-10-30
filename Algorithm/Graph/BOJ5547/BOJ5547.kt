package Algorithm.Graph.BOJ5547

import java.io.StreamTokenizer

class BOJ5547 {

    private var R = 0
    private var C = 0
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>
    private val evenR =
        arrayOf(arrayOf(-1, -1), arrayOf(-1, 0), arrayOf(0, 1), arrayOf(1, 0), arrayOf(1, -1), arrayOf(0, -1))
    private val oddR =
        arrayOf(arrayOf(-1, 0), arrayOf(-1, 1), arrayOf(0, 1), arrayOf(1, 1), arrayOf(1, 0), arrayOf(0, -1))

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        C = input() + 2
        R = input() + 2
        map = Array(R) { IntArray(C) }
        visited = Array(R) { BooleanArray(C) }
        for (i in 1..R - 2) {
            for (j in 1..C - 2) {
                map[i][j] = input()
            }
        }
        println(bfs())
    }

    private fun bfs(): Int {
        var count = 0
        val q = ArrayDeque<Pair<Int, Int>>()
        q.addLast(Pair(0, 0))
        visited[0][0] = true
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            var curCnt = 0
            val curDir = if (curr.first % 2 == 0) evenR else oddR
            for (d in 0..<6) {
                val nr = curr.first + curDir[d][0]
                val nc = curr.second + curDir[d][1]
                if (isOut(nr, nc)) continue
                if (map[nr][nc] == 1) curCnt++
                else if (!visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true
                    q.addLast(Pair(nr, nc))
                }
            }
            count += curCnt
        }
        return count
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ5547().solve()
}