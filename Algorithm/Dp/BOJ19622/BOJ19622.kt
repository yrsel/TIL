package Algorithm.Dp.BOJ19622

import java.io.StreamTokenizer

class BOJ19622 {

    private data class Meeting(val start: Int = 0, val end: Int = 0, val cnt: Int = 0)

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val meetings = mutableListOf<Meeting>()
        meetings.addAll(listOf(Meeting(), Meeting()))
        repeat(N) { meetings.add(Meeting(toInt(), toInt(), toInt())) }
        meetings.sortWith { o1, o2 -> if (o1.start == o2.start) o1.end.compareTo(o2.end) else o1.start.compareTo(o2.start) }
        val dp = Array(2) { IntArray(N + 2) }
        for (i in 2..<N + 2) {
            val curr = meetings[i]
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1])
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 2]) + curr.cnt
        }
        println(dp.maxOf { it.max() })
    }
}

fun main() {
    BOJ19622().solve()
}