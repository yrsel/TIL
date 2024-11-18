package Algorithm.Greedy.BOJ1455

class BOJ1455 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val (R, C) = readLine().split(" ").map { it.toInt() }
        val map = Array(R) {
            val str = readLine()
            IntArray(C) { i ->
                str[i] - '0'
            }
        }

        var turnCnt = 0
        val col = IntArray(C)
        for (r in R - 1 downTo 0) {
            for (c in C - 1 downTo 0) {
                if (map[r][c] == 1 && col[c] % 2 == 0
                    || map[r][c] == 0 && col[c] % 2 == 1
                ) {
                    (0..c).forEach { i -> col[i]++ }
                    turnCnt++
                }
            }
        }
        println(turnCnt)
    }
}

fun main() {
    BOJ1455().solve()
}