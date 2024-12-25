package Algorithm.Graph.BOJ14567

import java.io.StreamTokenizer

class BOJ14567 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val M = toInt()
        val inOrders = IntArray(N + 1)
        val connections = Array(N + 1) { mutableListOf<Int>() }
        repeat(M) {
            val first = toInt()
            val second = toInt()
            inOrders[second]++
            connections[first].add(second)
        }

        val q = ArrayDeque<Int>()
        val nodes = inOrders.drop(1).mapIndexedNotNull { index, value -> if (value == 0) index + 1 else null }
        q.addAll(nodes)

        val result = IntArray(N + 1)
        var cnt = 1
        while (q.isNotEmpty()) {
            var size = q.size
            while (size-- > 0) {
                val curr = q.removeFirst()
                result[curr] = cnt
                for (next in connections[curr]) {
                    inOrders[next]--
                    if (inOrders[next] == 0) q.addLast(next)
                }
            }
            cnt++
        }

        println(buildString {
            result.drop(1).forEach { append("${it} ") }
        }.trimEnd())
    }
}

fun main() {
    BOJ14567().solve()
}