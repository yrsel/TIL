package Algorithm.PrefixSum.BOJ14465

import java.io.StreamTokenizer

class BOJ14465 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val K = toInt()
        val B = toInt()
        val status = IntArray(N + 1)
        repeat(B) { status[toInt()]++ }
        for (i in 1..N) {
            status[i] += status[i - 1]
        }

        var min = 100_000
        for (plus in 0..N - K) {
            min = Math.min(min, status[K + plus] - status[0 + plus])
        }

        println(min)
    }
}

fun main() {
    BOJ14465().solve()
}