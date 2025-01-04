package Algorithm.Graph.BOJ29721

import java.io.StreamTokenizer

class BOJ29721 {
    private val dir = arrayOf(intArrayOf(0, 2), intArrayOf(0, -2), intArrayOf(2, 0), intArrayOf(-2, 0))
    private var N = 0
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        N = toInt()
        val K = toInt()

        var cnt = 0
        val hs = hashSetOf<Long>()
        repeat(K) {
            val r = toInt() - 1
            val c = toInt() - 1

            if (!hs.add(r.toLong() * N + c)) cnt--

            for (d in 0..<4) {
                val nr = r + dir[d][0]
                val nc = c + dir[d][1]
                if (isOut(nr, nc)) continue
                if (hs.add(nr.toLong() * N + nc)) cnt++
            }
        }

        println(cnt)
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= N || c >= N
}

fun main() {
    BOJ29721().solve()
}