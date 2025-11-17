package Algorithm.Graph.BOJ14675

import java.io.StreamTokenizer

class BOJ11725 {

    lateinit var connections: Array<MutableList<Int>>

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        fun toInt(): Int {
            nextToken()
            return nval.toInt()
        }

        val N = toInt()
        connections = Array(N + 1) { mutableListOf() }

        repeat(N - 1) {
            val from = toInt()
            val to = toInt()
            connections[from].add(to)
            connections[to].add(from)
        }

        val qCount = toInt()

        val result = buildString {
            repeat(qCount) {
                val t = toInt()   // 1: 단절점, 2: 단절선
                val k = toInt()

                if (t == 1 && connections[k].size == 1) {
                    appendLine("no")
                } else {
                    appendLine("yes")
                }
            }
        }

        print(result)
    }
}

fun main() {
    BOJ11725().solve()
}