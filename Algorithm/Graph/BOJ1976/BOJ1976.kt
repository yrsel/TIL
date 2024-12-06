package Algorithm.Graph.BOJ1976

import java.io.StreamTokenizer

class BOJ1976 {

    private lateinit var visited: BooleanArray
    private lateinit var map: Array<IntArray>
    private lateinit var groups: IntArray

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val M = toInt()
        map = Array(N) { IntArray(N) { toInt() } }
        groups = IntArray(N)
        visited = BooleanArray(N)

        var group = 1
        for (i in 0..<N) {
            if (!visited[i]) bfs(i, group++)
        }

        val set = hashSetOf<Int>()
        repeat(M) {
            val v = toInt() - 1
            set.add(groups[v])
        }

        println(buildString {
            if (set.size == 1) appendLine("YES")
            else appendLine("NO")
        })
    }

    private fun bfs(v: Int, groupNumber: Int) {
        val q = ArrayDeque<Int>()
        q.addLast(v)
        visited[v] = true
        groups[v] = groupNumber

        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for ((idx, e) in map[curr].withIndex()) {
                if (e == 1 && !visited[idx]) {
                    visited[idx] = true
                    groups[idx] = groupNumber
                    q.addLast(idx)
                }
            }
        }
    }
}

fun main() {
    BOJ1976().solve()
}