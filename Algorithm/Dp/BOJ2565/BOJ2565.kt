package Algorithm.Dp.BOJ2565

import java.io.StreamTokenizer

class BOJ2565 {
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        fun input(): Int {
            nextToken()
            return nval.toInt()
        }

        val list = mutableListOf<Pair<Int, Int>>()
        val n = input()
        repeat(n) {
            list.add(Pair(input(), input()))
        }
        list.sortBy { it.first }

        val memo = IntArray(n) { 1 }
        for (i in 0..<n) {
            for (j in 0..i) {
                if (list[i].second > list[j].second && memo[i] < memo[j] + 1)
                    memo[i] = memo[j] + 1
            }
        }

        println(n - memo.max())
    }
}

fun main() {
    BOJ2565().run()
}