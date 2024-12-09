package Algorithm.BruteForce.BOJ14391

import java.util.*

class BOJ14391 {
    private var R = 0
    private var C = 0
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>
    private var result = 0

    fun solve() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine(), " ")
        R = st.nextToken().toInt()
        C = st.nextToken().toInt()
        map = Array(R) { readLine().map { it.digitToInt() }.toIntArray() }
        result = map.sumOf { it.sum() }
        visited = Array(R) { BooleanArray(C) }

        search(0, 0)
        println(result)
    }

    private fun search(r: Int, c: Int) {
        if (r == R) {
            result = Math.max(result, calculate())
            return
        }

        visited[r][c] = true
        if (c + 1 == C) search(r + 1, 0)
        else search(r, c + 1)
        visited[r][c] = false
        if (c + 1 == C) search(r + 1, 0)
        else search(r, c + 1)
    }

    private fun calculate(): Int {
        var sum = 0
        for (i in 0..<R) {
            var curNumber = 0
            for (j in 0..<C) {
                if (visited[i][j]) {
                    curNumber = curNumber * 10 + map[i][j]
                } else {
                    sum += curNumber
                    curNumber = 0
                }
            }
            sum += curNumber
        }
        for (i in 0..<C) {
            var curNumber = 0
            for (j in 0..<R) {
                if (!visited[j][i]) {
                    curNumber = curNumber * 10 + map[j][i]
                } else {
                    sum += curNumber
                    curNumber = 0
                }
            }
            sum += curNumber
        }

        return sum
    }


}

fun main() {
    BOJ14391().solve()
}