package Algorithm.Implementation.BOJ31863

class BOJ31863 {
    private val dir = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1))
    private var R = 0
    private var C = 0
    private lateinit var map: Array<IntArray>

    private data class Point(val r: Int = 0, val c: Int = 0)

    fun solve() = with(System.`in`.bufferedReader()) {
        val (_r, _c) = readLine().split(" ").map { it.toInt() }
        R = _r
        C = _c
        var buildingCount = 0
        var start = Point()
        map = Array(R) { i ->
            val str = readLine()
            IntArray(C) { j ->
                if (str[j] == '@') start = Point(i, j)
                buildingCount += str[j].plus()
                str[j].toStatus()
            }
        }

        val destroyCount = bfs(start)
        println("${destroyCount} ${buildingCount - destroyCount}")
    }

    private fun bfs(start: Point): Int {
        val q = ArrayDeque<Point>()
        q.addLast(start)

        var cnt = 0
        var loop = 2
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            if (curr != start) loop = 1
            for (d in 0..<4) {
                for (multiple in 1..loop) {
                    val nr = curr.r + dir[d][0] * multiple
                    val nc = curr.c + dir[d][1] * multiple
                    if (isOut(nr, nc) || map[nr][nc] == -1) break
                    if (map[nr][nc] > 0) {
                        map[nr][nc]--
                        if (map[nr][nc] == 0) {
                            cnt++
                            q.addLast(Point(nr, nc))
                        }
                    }
                }
            }
        }

        return cnt
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C

    private fun Char.toStatus(): Int {
        return when (this) {
            '*' -> 1
            '#' -> 2
            '|' -> -1
            else -> 0
        }
    }

    private fun Char.plus(): Int {
        return when (this) {
            '*', '#' -> 1
            else -> 0
        }
    }
}

fun main() {
    BOJ31863().solve()
}