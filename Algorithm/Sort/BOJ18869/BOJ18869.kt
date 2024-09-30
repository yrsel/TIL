package Algorithm.Sort.BOJ18869

import java.util.*

class BOJ18869 {
    private data class Point(val num: Int, val idx: Int, var same: Boolean = false)

    fun run() {
        val (M, N) = readln().split(" ").map { it.toInt() }
        val map = Array(M) { mutableListOf<Point>() }
        val pq = PriorityQueue<Point> { o1, o2 ->
            if (o1.num == o2.num) o1.idx.compareTo(o2.idx) else o1.num.compareTo(o2.num)
        }
        repeat(M) { r ->
            val st = StringTokenizer(readln(), " ")
            repeat(N) { c ->
                pq.add(Point(st.nextToken().toInt(), c))
            }
            while (pq.isNotEmpty()) {
                val point = pq.poll()
                if (map[r].isNotEmpty()) {
                    if (map[r].last().num == point.num) {
                        map[r].last().same = true
                        point.same = true
                    }
                }
                map[r].add(point)
            }
        }

        var result = 0
        for (i in 0..<M - 1) {
            for (j in i + 1..<M) {
                if (isEqual(map[i], map[j])) result++
            }
        }
        println(result)
    }

    private fun isEqual(a: MutableList<Point>, b: MutableList<Point>): Boolean {
        for (i in a.indices) {
            if (a[i].idx != b[i].idx) return false
            if (a[i].same && !b[i].same || (!a[i].same && b[i].same)) return false
        }
        return true
    }

}

fun main() {
    BOJ18869().run()
}
