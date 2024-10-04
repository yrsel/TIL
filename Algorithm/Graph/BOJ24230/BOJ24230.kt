package Algorithm.Graph.BOJ24230

class BOJ24230 {
    private data class Point(val idx: Int, var color: Int)

    fun run() {
        val N = readln().toInt()
        val colors = readln().split(" ").map { it.toInt() }.toIntArray()
        val connection = Array(N) { mutableListOf<Int>() }
        repeat(N - 1) {
            val (from, to) = readln().split(" ").map { it.toInt() - 1 }
            connection[from].add(to)
            connection[to].add(from)
        }
        val visited = BooleanArray(N)
        visited[0] = true
        var result = if (colors[0] == 0) 0 else 1
        val q = ArrayDeque<Point>()
        q.addLast(Point(0, colors[0]))
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (next in connection[curr.idx]) {
                if (!visited[next]) {
                    visited[next]=true
                    if (curr.color != colors[next]) {
                        result++
                        q.addLast(Point(next, colors[next]))
                    } else {
                        q.addLast(Point(next, curr.color))
                    }
                }
            }
        }
        println(result)

    }
}

fun main() {
    BOJ24230().run()
}