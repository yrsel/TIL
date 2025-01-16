package Algorithm.Implementation.BOJ2799

class BOJ2799 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val R = n * 4 + (n + 1)
        val C = m * 4 + (m + 1)
        val map = Array(R) { readLine().map { it == '*' }.toBooleanArray() }
        val result = IntArray(5)
        var cnt: Int
        var curR: Int
        for (r in 1..<R step 5) {
            for (c in 1..<C step 5) {
                curR = r
                cnt = 0
                while (map[curR++][c]) cnt++
                result[cnt]++
            }
        }
        println(result.joinToString(" ") { it.toString() })
    }
}

fun main() {
    BOJ2799().solve()
}