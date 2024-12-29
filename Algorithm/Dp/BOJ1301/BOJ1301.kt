package Algorithm.Dp.BOJ1301

class BOJ1301 {
    private val dp =
        Array(11) { Array(11) { Array(11) { Array(11) { Array(11) { Array(6) { LongArray(6) { -1L } } } } } } }

    fun solve() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val arr = IntArray(5)
        for (i in 0..<N) arr[i] = readLine().toInt()
        println(search(arr[0], arr[1], arr[2], arr[3], arr[4], 0, 0))
    }

    private fun search(a: Int, b: Int, c: Int, d: Int, e: Int, prev: Int, prevTwoStep: Int): Long {
        if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0) return 1L

        if (dp[a][b][c][d][e][prev][prevTwoStep] != -1L) return dp[a][b][c][d][e][prev][prevTwoStep]

        var result = 0L
        if (a > 0 && prev != 1 && prevTwoStep != 1) {
            result += search(a - 1, b, c, d, e, 1, prev)
        }
        if (b > 0 && prev != 2 && prevTwoStep != 2) {
            result += search(a, b - 1, c, d, e, 2, prev)
        }
        if (c > 0 && prev != 3 && prevTwoStep != 3) {
            result += search(a, b, c - 1, d, e, 3, prev)
        }
        if (d > 0 && prev != 4 && prevTwoStep != 4) {
            result += search(a, b, c, d - 1, e, 4, prev)
        }
        if (e > 0 && prev != 5 && prevTwoStep != 5) {
            result += search(a, b, c, d, e - 1, 5, prev)
        }

        dp[a][b][c][d][e][prev][prevTwoStep] = result
        return result
    }
}

fun main() {
    BOJ1301().solve()
}