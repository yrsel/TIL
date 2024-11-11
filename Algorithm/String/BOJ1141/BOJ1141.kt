package Algorithm.String.BOJ1141

class BOJ1141 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val root = 1
        var unused = 2
        val MX = 50 * 50 + 5
        val nxt = Array(MX) { IntArray(26) { -1 } }

        val n = readLine().toInt()
        repeat(n) {
            val str = readLine()
            var curr = root
            str.forEach {
                val idx = it.toIndex()
                if (nxt[curr][idx] == -1) {
                    nxt[curr][idx] = unused++
                }
                curr = nxt[curr][idx]
            }
        }

        var result = 0
        for (i in 0..<26) {
            if (nxt[root][i] == -1) continue
            result += search(nxt[root][i], nxt)
        }

        println(result)
    }

    private fun search(idx: Int, nxt: Array<IntArray>): Int {
        val q = ArrayDeque<Int>()
        q.addLast(idx)

        var cnt = 0
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            val next = nxt[curr].filter { it != -1 }
            if (next.isEmpty()) cnt++
            else {
                next.forEach { q.addLast(it) }
            }
        }

        return cnt
    }

    private fun Char.toIndex(): Int {
        return this - 'a'
    }
}

fun main() {
    BOJ1141().solve()
}