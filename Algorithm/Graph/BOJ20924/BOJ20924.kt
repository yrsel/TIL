package Algorithm.Graph.BOJ20924

import java.io.StreamTokenizer

class BOJ20924 {

    private data class Node(val v: Int, val score: Int)

    private lateinit var visited: BooleanArray
    private lateinit var connections: Array<MutableList<Node>>
    private var rootLen = 0
    private var leafLen = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val n = toInt()
        val root = toInt() - 1
        connections = Array(n) { mutableListOf() }
        repeat(n - 1) {
            val from = toInt() - 1
            val to = toInt() - 1
            val d = toInt()
            connections[from].add(Node(to, d))
            connections[to].add(Node(from, d))
        }

        visited = BooleanArray(n)

        val RLNode = findRLNode(root) // 기가 노드
        findMaxLeafLen(RLNode)

        println("${rootLen} ${leafLen}")
    }

    private fun findRLNode(v: Int): Int {
        visited[v] = true
        if (connections[v].size != 1) return v

        val q = ArrayDeque<Int>()
        q.addLast(v)
        var sum = 0
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            if ((connections[curr].filter { !visited[it.v] }.size) == 1) {
                for (child in connections[curr]) {
                    if (visited[child.v]) continue
                    visited[child.v] = true
                    q.addLast(child.v)
                    sum += child.score
                }
            } else { // 기가노드가 리프노드거나, 기가노드를 발견했다면
                rootLen = sum
                return curr
            }
        }
        return -1
    }

    private fun findMaxLeafLen(v: Int) = connections[v].forEach { if (!visited[it.v]) dfs(it.v, it.score) }

    private fun dfs(v: Int, sum: Int) {
        visited[v] = true

        if (connections[v].none { !visited[it.v] }) {
            leafLen = leafLen.coerceAtLeast(sum)
            return
        }

        for (next in connections[v]) {
            if (!visited[next.v]) dfs(next.v, sum + next.score)
        }
    }
}

fun main() {
    BOJ20924().solve()
}