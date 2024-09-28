package Algorithm.Graph.BOJ14562

import java.util.*
import kotlin.collections.ArrayDeque

class BOJ14562 {
    private data class Point(val s: Int, val t: Int, var cnt: Int)

    fun run() {
        val result = StringBuilder()
        repeat(readln().toInt()) {
            val st = StringTokenizer(readln(), " ")
            val S = st.nextToken().toInt()
            val T = st.nextToken().toInt()
            val q = ArrayDeque<Point>()
            var min = 987_654_321
            q.add(Point(S, T, 0))
            while (q.isNotEmpty()) {
                val curr = q.removeFirst()
                if (min <= curr.cnt) continue
                if (curr.s == curr.t) {
                    min = curr.cnt.coerceAtMost(min)
                    continue
                }
                if (curr.s * 2 <= curr.t + 3) q.addLast(Point(curr.s * 2, curr.t + 3, curr.cnt + 1))
                if (curr.s + 1 <= curr.t) q.addLast(Point(curr.s + 1, curr.t, curr.cnt + 1))
            }
            result.appendLine(min)
        }
        println(result.toString())
    }
}

fun main() {
    BOJ14562().run()
}