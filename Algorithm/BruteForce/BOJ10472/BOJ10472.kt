package Algorithm.BruteForce.BOJ10472

class BOJ10472 {
    private val dir =
        arrayOf(intArrayOf(0, 0), intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
    private lateinit var visited: HashMap<String, Int>
    private lateinit var map: Array<BooleanArray>
    private var answer = ""
    private var result: Int = 0
    fun solve() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        println(buildString {
            repeat(n) {
                answer = ""
                result = Integer.MAX_VALUE
                visited = hashMapOf()
                map = Array(3) { BooleanArray(3) }
                repeat(3) { i -> readLine().forEachIndexed { idx, c -> if (c == '*') answer += i * 3 + (idx + 1) } }
                search(0)
                appendLine(result)
            }
        }.trimEnd())
    }

    private fun search(cnt: Int) {
        if (cnt >= result) return
        val key = map.toIdentity()
        if (visited[key] != null && visited[key]!! <= cnt) return
        if (answer == key) {
            result = cnt
            return
        }

        if (cnt != 0) visited[key] = cnt

        for (i in 0..<3) {
            for (j in 0..<3) {
                reverse(i, j)
                search(cnt + 1)
                reverse(i, j)
            }
        }
    }

    private fun reverse(r: Int, c: Int) {
        for (d in 0..<5) {
            val nr = r + dir[d][0]
            val nc = c + dir[d][1]
            if (isOut(nr, nc)) continue
            map[nr][nc] = !map[nr][nc]
        }
    }

    private fun isOut(r: Int, c: Int): Boolean {
        return r < 0 || c < 0 || r >= 3 || c >= 3
    }

    private fun Array<BooleanArray>.toIdentity(): String {
        return buildString {
            for (i in 0..<3) {
                for (j in 0..<3) {
                    if (this@toIdentity[i][j]) append(i * 3 + (j + 1))
                }
            }
        }
    }
}

fun main() {
    BOJ10472().solve()
}