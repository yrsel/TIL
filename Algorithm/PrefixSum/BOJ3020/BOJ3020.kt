package Algorithm.PrefixSum.BOJ3020

import java.io.StreamTokenizer

class BOJ3020 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val H = toInt()
        val top = IntArray(H + 1)
        val bottom = IntArray(H + 1)
        repeat(N) { i ->
            val num = toInt()
            if (i % 2 == 0) { // bottom
                bottom[num]++
            } else { // top
                top[H - num]++
            }
        }
        for (i in 1..H) {
            top[i] += top[i - 1]
        }
        for (i in H - 1 downTo 0) {
            bottom[i] += bottom[i + 1]
        }

        var min = Int.MAX_VALUE
        var cnt = 0
        for (i in 0..<H) {
            val sum = top[i] + bottom[i + 1]
            if (min >= sum) {
                if (min == sum) cnt++
                else {
                    min = sum
                    cnt = 1
                }
            }
        }

        println("$min $cnt")
    }
}

fun main() {
    BOJ3020().solve()
}