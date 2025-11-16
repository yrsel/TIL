package Algorithm.Graph.BOJ11725

import java.io.StreamTokenizer

class BOJ11725 {
    private var N = 0
    private lateinit var connections: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        N = toInt()
        connections = Array(N + 1) { mutableListOf() }
        repeat(N - 1) {
            val from = toInt()
            val to = toInt()
            connections[from].add(to)
            connections[to].add(from)
        }

        val result = IntArray(N + 1)

        visited = BooleanArray(N + 1)
        visited[1] = true
        val q = ArrayDeque<Int>()
        q.addLast(1)
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (next in connections[curr]) {
                if (visited[next]) continue
                visited[next] = true
                result[next] = curr
                q.addLast(next)
            }
        }

        println(buildString {
            result.drop(2).forEach { appendLine(it) }
        }.trim())
    }
}

fun main() {
    BOJ11725().solve()
}