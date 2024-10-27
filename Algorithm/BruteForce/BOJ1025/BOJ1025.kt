package Algorithm.BruteForce.BOJ1025

import java.lang.Math.max

class BOJ1025 {

    private lateinit var map: Array<IntArray>
    private var R = 0
    private var C = 0
    private var result = -1

    fun solve() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        R = n; C = m
        map = Array(R) { IntArray(C) }
        for (i in 0..<R) {
            val str = readLine()
            for (j in 0..<C) {
                map[i][j] = str[j].digitToInt()
                if (map[i][j] == 9 || map[i][j] == 4 || map[i][j] == 1) result = max(result, map[i][j])
            }
        }

        for (r in 0..<R) {
            for (c in 0..<C) {
                (-R..<R).forEach { rPoint ->
                    (-C..<C).forEach { cPoint ->
                        if (rPoint != 0 || cPoint != 0) {
                            buildString {
                                var curR = r
                                var curC = c
                                while (!isOut(curR, curC)) {
                                    append(map[curR][curC])
                                    if (isSatisfied(this.toString())) {
                                        result = result.coerceAtLeast(this.toString().toInt())
                                    }
                                    curR += rPoint
                                    curC += cPoint
                                }
                            }
                        }
                    }
                }
            }
        }
        println(result)
    }

    private fun isSatisfied(number: String): Boolean {
        val compNumber = number.toInt()
        return kotlin.math.sqrt(compNumber.toDouble()) % 1 == 0.0
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ1025().solve()
}