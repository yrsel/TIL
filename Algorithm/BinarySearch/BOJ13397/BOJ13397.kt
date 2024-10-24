package Algorithm.BinarySearch.BOJ13397

import java.io.StreamTokenizer

class BOJ13397 {
    private var n = 0
    private var m = 0
    private var result = 0
    private lateinit var scores: MutableList<Int>
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        n = input()
        m = input()
        scores = MutableList(n) { input() }

        result = scores.max()
        bs(0, scores.max())
        println(result)
    }

    private fun bs(from: Int, to: Int) {
        if (from > to) return
        val mid = (from + to) / 2

        if (isSatisfied(mid)) {
            bs(from, mid - 1)
            result = Math.min(result,mid)
        } else bs(mid + 1, to)
    }

    private fun isSatisfied(len: Int): Boolean {
        var min = 987_654_321
        var max = -1
        var cnt = 0
        scores.forEach {
            min = Math.min(min, it)
            max = Math.max(max, it)
            if (max - min > len) {
                cnt++
                min = it
                max = it
            }
        }
        return cnt < m
    }

}

fun main() {
    BOJ13397().run()
}