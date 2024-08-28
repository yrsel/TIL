package Algorithm.BruteForce.BOJ3980

class BOJ3980 {
    private lateinit var infos: Array<IntArray>
    private val SIZE = 11
    private var result = 0
    fun run() {
        val sb = StringBuilder()
        repeat(readln().toInt()) {
            infos = Array(SIZE) {
                readln().split(" ").map { it.toInt() }.toIntArray()
            }

            result = 0
            val visited = BooleanArray(SIZE)
            for (i in 0..<SIZE) {
                if (infos[0][i] != 0) {
                    visited[i] = true
                    search(1, infos[0][i], visited)
                    visited[i] = false
                }
            }
            sb.appendLine(result)
        }
        println(sb.toString())
    }

    private fun search(v: Int, sum: Int, visited: BooleanArray) {
        if (v == SIZE) {
            result = result.coerceAtLeast(sum)
            return
        }

        for (i in 0..<SIZE) {
            if (!visited[i] && infos[v][i] != 0) {
                visited[i] = true
                search(v + 1, sum + infos[v][i], visited)
                visited[i] = false
            }
        }
    }
}

fun main() {
    BOJ3980().run()
}

