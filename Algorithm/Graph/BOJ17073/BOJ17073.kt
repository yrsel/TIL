package Algorithm.Graph.BOJ17073

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque

class BOJ17073 {

    private lateinit var connects: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private lateinit var st: StringTokenizer
    private var leafCount = 0

    fun run() = with(BufferedReader(InputStreamReader(System.`in`))) {
        st = StringTokenizer(readLine(), " ")
        val N = st.nextToken().toInt()
        val W = st.nextToken().toDouble()
        connects = Array(N) { mutableListOf() }
        visited = BooleanArray(N)

        repeat(N - 1) {
            st = StringTokenizer(readLine(), " ")
            val from = st.nextToken().toInt() - 1
            val to = st.nextToken().toInt() - 1
            connects[from].add(to)
            connects[to].add(from)
        }

        search()

        println( String.format("%.10f",W/leafCount))
    }

    private fun search() {
        val q = ArrayDeque<Int>()
        visited[0] = true
        q.addLast(0)

        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            var flag = false
            for (next in connects[curr]) {
                if (visited[next]) continue
                visited[next] = true
                q.addLast(next)
                flag = true
            }
            if (!flag) leafCount++
        }
    }
}


fun main() {
    BOJ17073().run()
}