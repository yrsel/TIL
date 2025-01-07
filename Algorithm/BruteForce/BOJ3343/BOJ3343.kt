package Algorithm.BruteForce.BOJ3343

import kotlin.math.ceil

class BOJ3343 {
    fun solve() = with(System.`in`.bufferedReader()) {
        var (n, a, b, c, d) = readLine().trim().split(" ").map { it.toLong() }
        // 가성비 체크
        if (c * b > d * a) {
            var tmp = a; a = c; c = tmp
            tmp = b; b = d; d = tmp
        }

        var result = Long.MAX_VALUE
        for (i in 0..<a) {
            var comp = ceil((n - i * c).toDouble() / a).toLong()
            var flag = false
            if (comp < 0) {
                comp = 0
                flag = true
            }
            result = Math.min(result, comp * b + i * d)
            if (flag) break
        }
        println(result)
    }
}

fun main() {
    BOJ3343().solve()
}