package Algorithm.Graph.BOJ1944

import java.util.*
import kotlin.collections.ArrayDeque

class BOJ1944 {
    private val dir = arrayOf(arrayOf(1, 0), arrayOf(0, 1), arrayOf(-1, 0), arrayOf(0, -1))

    private data class Point(val r: Int, val c: Int, var dist: Int = 0)

    private lateinit var map: Array<IntArray>
    private val points = mutableListOf<Point>()
    private val pq = PriorityQueue<Point> { o1, o2 -> o1.dist.compareTo(o2.dist) }
    private var N = 0
    private var M = 0
    private lateinit var parents: IntArray

    fun run() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        N = n; M = m

        var posIdx = 0
        map = Array(N) { IntArray(N) }
        for (i in 0..<N) {
            val str = readLine()
            for (j in 0..<N) {
                if (str[j] == 'S' || str[j] == 'K') {
                    map[i][j] = ++posIdx
                    points.add(Point(i, j))
                } else if (str[j] == '1') {
                    map[i][j] = -1
                } else {
                    map[i][j] = 0
                }
            }
        }

        points.forEach { setDistance(it) }

        parents = IntArray(M + 2) { it } // 1번부터 시작 + 로봇

        var pick = 0
        var sum = 0
        while (pq.isNotEmpty()) {
            val (from, to, dist) = pq.poll()
            if (union(from, to)) {
                pick++
                sum += dist
            }
            if (pick == M) break // 간선 M개 선택 완료
        }
        if (pick != M) sum = -1

        println(sum)
    }

    private fun setDistance(curPoint: Point) {
        val curPointNumber = map[curPoint.r][curPoint.c]
        val visited = Array(N) { BooleanArray(N) }
        val q = ArrayDeque<Point>()
        q.addLast(curPoint)
        visited[curPoint.r][curPoint.c] = true

        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (d in 0..<4) {
                val nr = curr.r + dir[d][0]
                val nc = curr.c + dir[d][1]
                if (map[nr][nc] == -1 || visited[nr][nc]) continue
                visited[nr][nc] = true
                q.addLast(Point(nr, nc, curr.dist + 1))
                if (curPointNumber < map[nr][nc]) {
                    pq.add(Point(curPointNumber, map[nr][nc], curr.dist + 1))
                }
            }
        }
    }

    private fun find(a: Int): Int {
        if (parents[a] == a) return a
        return find(parents[a]).also { parents[a] = it }
    }

    private fun union(a: Int, b: Int): Boolean {
        val aRoot = find(a)
        val bRoot = find(b)
        if (aRoot == bRoot) return false
        parents[bRoot] = aRoot
        return true
    }

}

fun main() {
    BOJ1944().run()
}
