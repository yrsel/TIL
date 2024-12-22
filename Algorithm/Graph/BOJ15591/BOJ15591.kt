package Algorithm.Graph.BOJ15591

import java.io.StreamTokenizer

class BOJ15591 {
    private lateinit var connections: Array<MutableList<Pair<Int, Int>>>
    private var N = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        N = toInt()
        val Q = toInt()
        connections = Array(N + 1) { mutableListOf() }

        repeat(N - 1) {
            val from = toInt()
            val to = toInt()
            val w = toInt()
            connections[from].add(Pair(to, w))
            connections[to].add(Pair(from, w))
        }

        println(buildString {
            repeat(Q) {
                val min = toInt()
                val start = toInt()
                appendLine(bfs(start, min))
            }
        })
    }

    private fun bfs(start: Int, minValue: Int): Int {
        var cnt = 0
        val visited = BooleanArray(N + 1)
        val q = ArrayDeque<Pair<Int, Int>>()
        visited[start] = true
        q.addLast(Pair(start, 1_000_000_001))
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (next in connections[curr.first]) {
                if (visited[next.first]) continue
                visited[next.first] = true
                val curMin = Math.min(curr.second, next.second)
                if (curMin >= minValue) cnt++
                q.addLast(Pair(next.first, curMin))
            }
        }
        return cnt
    }
}

fun main() {
    BOJ15591().solve()
}