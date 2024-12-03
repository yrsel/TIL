package Algorithm.Dp.BOJ1949

import java.io.StreamTokenizer

class BOJ1949 {

    private lateinit var scores: IntArray
    private lateinit var connections: Array<MutableList<Int>>
    private lateinit var dp: Array<IntArray>
    private lateinit var visited: BooleanArray
    private var N = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }

        N = toInt()
        scores = IntArray(N) { toInt() }
        visited = BooleanArray(N)
        connections = Array(N) { mutableListOf() }
        repeat(N - 1) {
            val from = toInt() - 1
            val to = toInt() - 1
            connections[from].add(to)
            connections[to].add(from)
        }

        dp = Array(2) { IntArray(N) } // [0][] : 우수마을 선택 X, [1][]: 선택 O
        search(0)
        println(Math.max(dp[0][0], dp[1][0]))
    }

    private fun search(v: Int) {
        visited[v] = true
        dp[1][v] += scores[v]

        for (next in connections[v]) {
            if (visited[next]) continue
            search(next)
            dp[0][v] += Math.max(dp[0][next], dp[1][next])
            dp[1][v] += dp[0][next]
        }
    }
}

fun main() {
    BOJ1949().solve()
}