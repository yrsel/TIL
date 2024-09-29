package Algorithm.Graph.BOJ24482

class BOJ24482 {
    private lateinit var visited: IntArray
    private lateinit var map: Array<MutableList<Int>>
    fun run() {
        val (V, E, S) = readln().split(" ").map { it.toInt() }
        visited = IntArray(V) { -1 }
        map = Array(V) { mutableListOf() }

        repeat(E) {
            val (from, to) = readln().split(" ").map { it.toInt() - 1 }
            map[from].add(to)
            map[to].add(from)
        }

        for (i in 0..<V) {
            map[i].sortByDescending { it }
        }

        dfs(S - 1, 0)
        val result = StringBuilder()
        visited.forEach { result.appendLine(it) }
        println(result.toString())
    }

    private fun dfs(v: Int, depth: Int) {
        visited[v] = depth

        for (next in map[v]) {
            if (visited[next] == -1) {
                dfs(next, depth + 1)
            }
        }
    }
}

fun main() {
    BOJ24482().run()
}