package Algorithm.Graph.BOJ2224

import java.util.*
import kotlin.collections.ArrayDeque

class BOJ2224 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val connections = TreeMap<Char, MutableList<Char>>()
        val memo = TreeMap<Char, MutableList<Char>>()

        var st: StringTokenizer
        repeat(n) {
            st = StringTokenizer(readLine(), " => ")
            val from = st.nextToken()[0]
            val to = st.nextToken()[0]
            val value = if (connections[from] != null) connections[from]!!
            else mutableListOf()
            value.add(to)
            connections[from] = value
        }

        val q = ArrayDeque<Char>()
        connections.forEach { (k, _) ->
            val value = mutableListOf<Char>()
            q.addLast(k)
            while (q.isNotEmpty()) {
                val curr = q.removeFirst()
                if (connections[curr] != null) {
                    for (next in connections[curr]!!) {
                        if (next == curr || value.contains(next)) continue
                        if (memo[next] != null) {
                            value.addAll(memo[next]!!)
                        } else {
                            value.add(next)
                            q.addLast(next)
                        }
                    }
                }
            }
            value.sort()
            memo[k] = value
        }

        var sum = 0
        println(buildString {
            for (entry in memo) {
                for (value in entry.value) {
                    appendLine("${entry.key} => ${value}")
                }
                sum += entry.value.size
            }
            insert(0, "$sum\n")
        })
    }
}

fun main() {
    BOJ2224().solve()
}