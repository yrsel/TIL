package Algorithm.Graph.BOJ11657

import java.io.StreamTokenizer

class BOJ11657 {

    private data class Point(val from: Int, val to: Int, val w: Int)

    private lateinit var points: Array<Point>

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val M = toInt()
        points = Array(M) { Point(toInt(), toInt(), toInt()) }
        val costs = Array(N + 1) { 987_654_321L }
        costs[1] = 0L

        repeat(N) {
            for (i in 0..<M) {
                val curr = points[i]
                if (costs[curr.from] != 987_654_321L && costs[curr.to] > costs[curr.from] + curr.w) {
                    costs[curr.to] = costs[curr.from] + curr.w
                }
            }
        }

        repeat(N) {
            for (i in 0..<M) {
                val curr = points[i]
                if (costs[curr.from] != 987_654_321L && costs[curr.to] > costs[curr.from] + curr.w) {
                    println(-1)
                    return
                }
            }
        }

        println(buildString {
            for (i in 2..N) {
                if (costs[i] == 987_654_321L) appendLine(-1)
                else appendLine(costs[i])
            }
        })

    }
}

fun main() {
    BOJ11657().solve()
}