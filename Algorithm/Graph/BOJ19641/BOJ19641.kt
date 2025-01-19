package Algorithm.Graph.BOJ19641

import java.io.StreamTokenizer

class BOJ19641 {
    private lateinit var connections: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private lateinit var status: Array<Point>
    private var number = 1

    private data class Point(var left: Int = 0, var right: Int = 0)

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        val N = toInt()
        connections = Array(N) { mutableListOf() }
        visited = BooleanArray(N)
        status = Array(N) { Point() }

        repeat(N) {
            val from = toInt() - 1
            while (true) {
                val to = toInt() - 1
                if (to == -2) break
                connections[from].add(to)
            }
        }

        search(toInt() - 1)
        println(buildString { status.forEachIndexed { idx, point -> appendLine("${idx + 1} ${point.left} ${point.right}") } })
    }

    private fun search(v: Int) {
        status[v].left = number++
        visited[v] = true

        connections[v].sort()
        for (candidate in connections[v]) if (!visited[candidate]) search(candidate)

        status[v].right = number++
    }
}

fun main() {
    BOJ19641().solve()
}