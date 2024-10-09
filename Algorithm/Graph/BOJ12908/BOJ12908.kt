package Algorithm.Graph.BOJ12908

import java.io.StreamTokenizer
import kotlin.math.abs

class BOJ12908 {
    private data class Point(val r: Int, val c: Int)

    private lateinit var points: Array<Point>

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        points = Array(8) { Point(input(), input()) }
        val times = Array(8) { LongArray(8) {Long.MAX_VALUE } }
        times[0][1] = getTimes(0, 1)
        times[1][0] = times[0][1]

        for (i in 2..<8 step 2) {
            var time = getTimes(i, i + 1)
            if (time > 10) time = 10
            times[i][i + 1] = time
            times[i + 1][i] = time
        }

        for (i in 0..<8) {
            for (j in 0..<8) {
                times[i][j] = times[i][j].coerceAtMost(getTimes(i, j))
            }
        }

        for (k in 0..<8) {
            for (i in 0..<8) {
                for (j in 0..<8) {
                    times[i][j] = times[i][j].coerceAtMost(times[i][k] + times[k][j])
                }
            }
        }

        println(times[0][1])
    }

    private fun getTimes(from: Int, to: Int): Long {
        return abs(points[from].r - points[to].r).toLong() + abs(points[from].c - points[to].c)
    }
}

fun main() {
    BOJ12908().run()
}