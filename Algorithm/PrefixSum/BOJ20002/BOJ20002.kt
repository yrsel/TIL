package Algorithm.PrefixSum.BOJ20002

import java.util.*

class BOJ20002 {
    fun run() {
        val N = readln().toInt()
        val map = Array(N + 1) { IntArray(N + 1) }

        var max = Integer.MIN_VALUE
        for (i in 1..N) {
            val st = StringTokenizer(readln(), " ");
            for (j in 1..N) {
                map[i][j] = st.nextToken().toInt()
                max = map[i][j].coerceAtLeast(max)
                map[i][j] += map[i][j - 1] + map[i - 1][j] - map[i - 1][j - 1]
            }
        }

        var range = 1
        while (range++ < N) {
            for (i in 1..N-range) {
                for (j in 1..N-range) {
                    val comp = map[i+range][j+range] - map[i-1][j+range] - map[i+range][j-1] + map[i-1][j-1]
                    max = max.coerceAtLeast(comp)
                }
            }
        }
        println(max)
    }
}

fun main() {
    BOJ20002().run()
}