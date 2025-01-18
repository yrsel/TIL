package Algorithm.BruteForce.BOJ1527

import java.io.StreamTokenizer

class BOJ1527 {
    private val numbers = mutableListOf<Int>()

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() };

        initNumbers("")

        val A = toInt()
        val B = toInt()
        numbers.sort()
        println(numbers.count { it in A..B })
    }

    private fun initNumbers(s: String) {
        if (s.length == 10) {
            return
        }

        if (s.isNotEmpty()) numbers.add(s.toInt())

        initNumbers(s + "4")
        initNumbers(s + "7")
    }
}

fun main() {
    BOJ1527().solve()
}