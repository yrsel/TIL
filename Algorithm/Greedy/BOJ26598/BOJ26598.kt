package Algorithm.Greedy.BOJ26598

class BOJ26598 {
    private lateinit var map: Array<CharArray>
    private var R = 0
    private var C = 0
    private lateinit var visited: Array<BooleanArray>
    private val dir = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
    fun solve() {
        val (_R, _C) = readln().split(" ").map { it.toInt() }
        R = _R
        C = _C
        map = Array(R) { readln().toCharArray() }
        visited = Array(R) { BooleanArray(C) }

        for (i in 0..<R) {
            for (j in 0..<C) {
                if (visited[i][j]) continue
                visited[i][j] = true
                if (!bfs(i, j)) {
                    println("BaboBabo")
                    return
                }
            }
        }

        println("dd")
    }

    private fun bfs(r: Int, c: Int): Boolean {
        val q = ArrayDeque<Pair<Int, Int>>()
        q.addLast(Pair(r, c))
        var minR = r
        var maxR = r
        var minC = c
        var maxC = c
        var cnt = 1
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (d in 0..<4) {
                val nr = curr.first + dir[d][0]
                val nc = curr.second + dir[d][1]
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] != map[r][c]) continue
                visited[nr][nc] = true
                q.addLast(Pair(nr, nc))
                minR = Math.min(minR, nr)
                maxR = Math.max(maxR, nr)
                minC = Math.min(minC, nc)
                maxC = Math.max(maxC, nc)
                cnt++
            }
        }
        return ((maxR - minR + 1) * (maxC - minC + 1)) == cnt
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ26598().solve()
}