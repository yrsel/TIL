package Algorithm.Graph.BOJ19542

import java.io.StreamTokenizer

class BOJ19542 {

    private lateinit var connections: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private var N = 0
    private var S = 0
    private var D = 0
    private var result = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        N = toInt()
        S = toInt()
        D = toInt()
        connections = Array(N + 1) { mutableListOf() }
        visited = BooleanArray(N + 1)

        repeat(N - 1) {
            val from = toInt()
            val to = toInt()
            connections[from].add(to)
            connections[to].add(from)
        }

        visited[S] = true
        search(S)
        if (result <= 1) println(0)
        else println((result - 1) * 2)
    }

    private fun search(v: Int): Int {
        var dist = 0
        for (next in connections[v]) {
            if (visited[next]) continue
            visited[next] = true
            dist = Math.max(dist, search(next))
        }

        if (dist >= D) result++
        return dist + 1
    }

}

fun main() {
    BOJ19542().solve()
}

//13 1 1
//1 2
//1 3
//2 4
//2 5
//3 6
//3 7
//3 8
//6 9
//9 10
//10 13
//9 11
//7 12

// -> 12