package Algorithm.Dp.BOJ1520

import java.util.*

class BOJ1520 {

    private data class Point(val r: Int, val c: Int, val h: Int)

    private val dir = arrayOf(arrayOf(-1, 0), arrayOf(1, 0), arrayOf(0, 1), arrayOf(0, -1))
    private var R = 0
    private var C = 0

    fun run() {
        var st = StringTokenizer(readln(), " ")
        R = st.nextToken().toInt()
        C = st.nextToken().toInt()
        val map = Array(R) { IntArray(C) }
        val dp = Array(R) { IntArray(C) }
        val visited = Array(R) { BooleanArray(C) }

        for (i in 0..<R) {
            st = StringTokenizer(readln(), " ")
            for (j in 0..<C) {
                map[i][j] = st.nextToken().toInt()
            }
        }

        val pq = PriorityQueue<Point> { o1, o2 -> o2.h.compareTo(o1.h) }
        pq.offer(Point(0, 0, map[0][0]))

        dp[0][0] = 1
        visited[0][0] = true
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            for (d in 0..<4) {
                val nr = curr.r + dir[d][0]
                val nc = curr.c + dir[d][1]
                if (isOut(nr, nc) || map[nr][nc] >= curr.h) continue
                if (!visited[nr][nc]) pq.offer(Point(nr, nc, map[nr][nc]))
                visited[nr][nc] = true
                dp[nr][nc] += dp[curr.r][curr.c]
            }
        }

        println(dp[R - 1][C - 1])
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ1520().run()
}