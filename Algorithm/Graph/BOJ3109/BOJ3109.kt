package Algorithm.Graph.BOJ3109

class BOJ3109 {

    private val dir = arrayOf(intArrayOf(-1, 1), intArrayOf(0, 1), intArrayOf(1, 1))
    private lateinit var map: Array<CharArray>
    private lateinit var visited: Array<BooleanArray>
    private var R = 0
    private var C = 0

    fun solve() = with(System.`in`.bufferedReader()) {
        val (r, c) = readLine().split(" ").map { it.toInt() }
        R = r
        C = c
        map = Array(R) { readLine().toCharArray() }

        visited = Array(R) { BooleanArray(C) }
        var cnt = 0
        for (i in 0..<R) {
            if (dfs(i, 0)) cnt++
        }
        println(cnt)
    }

    private fun dfs(r: Int, c: Int): Boolean {
        visited[r][c] = true
        if (c == C - 1) return true

        for (d in 0..<3) {
            val nr = r + dir[d][0]
            val nc = c + dir[d][1]
            if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == 'x') continue
            if (dfs(nr, nc)) return true
        }

        return false
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ3109().solve()
}