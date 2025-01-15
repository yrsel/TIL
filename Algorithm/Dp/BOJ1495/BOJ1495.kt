package Algorithm.Dp.BOJ1495

import java.io.StreamTokenizer

class BOJ1495 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        val N = toInt()
        val S = toInt()
        val M = toInt()

        val candidates = mutableListOf<Int>()
        repeat(N) { i ->
            val num = toInt()
            val curr = mutableSetOf<Int>()
            if (candidates.isEmpty()) {
                if (i == 0) {
                    if (S + num <= M) candidates.add(S + num)
                    if (S - num >= 0) candidates.add(S - num)
                }
            } else {
                candidates.forEach {
                    if (it + num <= M) curr.add(it + num)
                    if (it - num >= 0) curr.add(it - num)
                }
                candidates.clear()
                candidates.addAll(curr)
            }
        }

        if (candidates.isEmpty()) println(-1)
        else println(candidates.max())
    }
}

fun main() {
    BOJ1495().solve()
}