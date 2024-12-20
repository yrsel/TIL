package Algorithm.Graph.BOJ17090

class BOJ17090 {

    private val dir = hashMapOf(
        'D' to intArrayOf(1, 0), 'R' to intArrayOf(0, 1),
        'L' to intArrayOf(0, -1), 'U' to intArrayOf(-1, 0)
    )
    private val outPositions = hashSetOf<Int>()
    private lateinit var map: Array<CharArray>
    private lateinit var visited: Array<BooleanArray>

    fun solve() = with(System.`in`.bufferedReader()) {
        val (R, C) = readLine().split(" ").map { it.toInt() }
        map = Array(R) { readLine().toCharArray() }
        visited = Array(R) { BooleanArray(C) }

        for (i in 0..<R) {
            for (j in 0..<C) {
                if (visited[i][j]) continue
                bfs(i, j, R, C)
            }
        }
        println(outPositions.size)
    }

    private fun bfs(r: Int, c: Int, R: Int, C: Int) {
        visited[r][c] = true
        val q = ArrayDeque<IntArray>()
        q.addLast(intArrayOf(r, c))
        val candidates = mutableListOf<Int>()
        candidates.add(r * C + c)
        var flag = false
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            val d = dir[map[curr[0]][curr[1]]]!!
            val nr = curr[0] + d[0]
            val nc = curr[1] + d[1]
            if (isOut(nr, nc, R, C)) {
                flag = true
                break
            } else if (visited[nr][nc]) {
                if (outPositions.contains(nr * C + nc)) flag = true
                break
            } else {
                visited[nr][nc] = true
                candidates.add(nr * C + nc)
                q.addLast(intArrayOf(nr, nc))
            }
        }
        if (flag) {
            outPositions.addAll(candidates)
        }
    }

    private fun isOut(r: Int, c: Int, R: Int, C: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ17090().solve()
}