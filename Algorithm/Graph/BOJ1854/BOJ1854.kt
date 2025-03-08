package Algorithm.Graph.BOJ1854

import java.io.StreamTokenizer
import java.util.*

class BOJ1854 {

    private data class Point(val v: Int, val cost: Int)

    private lateinit var connections: Array<MutableList<Point>>
    private lateinit var pq: PriorityQueue<Point>
    private lateinit var costs: Array<PriorityQueue<Int>>

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val n = toInt()
        val m = toInt()
        val k = toInt()

        connections = Array(n + 1) { mutableListOf() }
        repeat(m) {
            connections[toInt()].add(Point(toInt(), toInt()))
        }

        costs = Array(n + 1) { PriorityQueue { o1, o2 -> o2.compareTo(o1) } }
        costs[1].add(0) // 1번이 자기 자신으로 가는 경우

        pq = PriorityQueue { o1, o2 -> o1.cost.compareTo(o2.cost) }
        pq.offer(Point(1, 0))
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            for (next in connections[curr.v]) {
                val cost = curr.cost + next.cost
                if (costs[next.v].size < k) {
                    costs[next.v].add(cost)
                    pq.offer(Point(next.v, cost))
                } else {
                    if (costs[next.v].peek() > cost) {
                        costs[next.v].poll()
                        costs[next.v].offer(cost)
                        pq.offer(Point(next.v, cost))
                    }
                }
            }
        }

        println(buildString {
            for (i in 1..<costs.size) {
                if (costs[i].size != k) appendLine(-1)
                else appendLine(costs[i].maxOf { number -> number })
            }
        })

    }
}

fun main() {
    BOJ1854().solve()
}