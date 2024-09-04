package Algorithm.Graph.BOJ2637

import java.util.*
import kotlin.collections.ArrayDeque

class BOJ2637 {
    private data class Product(val number: Int, var cnt: Int)

    private lateinit var connections: Array<MutableList<Product>>
    private lateinit var count: IntArray

    fun run() {
        val N = readln().toInt()
        val M = readln().toInt()

        val pureProduct = mutableListOf<Int>()
        for (i in 0..<N) {
            pureProduct.add(i)
        }
        connections = Array(N) { mutableListOf() }
        count = IntArray(N)
        val inDegree = IntArray(N)

        var st: StringTokenizer
        repeat(M) {
            st = StringTokenizer(readln(), " ")
            val x = st.nextToken().toInt() - 1
            val y = st.nextToken().toInt() - 1
            val k = st.nextToken().toInt()
            connections[x].add(Product(y, k))
            inDegree[y]++
            pureProduct.remove(x)
        }

        val q = ArrayDeque<Int>()
        q.addLast(N - 1)
        count[N - 1] = 1

        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (next in connections[curr]) {
                count[next.number] += count[curr] * next.cnt
                if (--inDegree[next.number] == 0) q.addLast(next.number)
            }
        }

        val result = StringBuilder()
        pureProduct.forEach {
            result.appendLine("${it+1} ${count[it]}")
        }
        println(result.toString())
    }
}


fun main() {
    BOJ2637().run()
}