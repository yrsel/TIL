package Algorithm.Graph.BOJ10711

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class BOJ10711 {

    private lateinit var st: StringTokenizer

    private lateinit var map: Array<IntArray>
    private lateinit var closedSandCount: Array<IntArray>
    private val dir = arrayOf(
        arrayOf(0, -1), arrayOf(-1, -1), arrayOf(-1, 0), arrayOf(-1, 1), arrayOf(0, 1),
        arrayOf(1, 1), arrayOf(1, 0), arrayOf(1, -1),
    )

    private data class SandPos(val r: Int, val c: Int)

    private val candidateSands = mutableListOf<SandPos>()

    private val removeSands = mutableListOf<SandPos>()

    private var R = 0
    private var C = 0
    private var count = 0

    fun run() = with(BufferedReader(InputStreamReader(System.`in`))) {
        st = StringTokenizer(readLine(), " ")
        R = st.nextToken().toInt()
        C = st.nextToken().toInt()
        map = Array(R) { IntArray(C) { -1 } }
        closedSandCount = Array(R) { IntArray(C) }
        for (i in 0..<R) {
            val curRow = readLine().toCharArray()
            for (j in 0..<C) {
                val cur = curRow[j]
                if (cur.isDigit()) {
                    map[i][j] = cur.digitToInt()
                    candidateSands.add(SandPos(i, j))
                } else {
                    for (d in 0..<8) {
                        val nr = i + dir[d][0]
                        val nc = j + dir[d][1]
                        if (isOut(nr, nc)) continue
                        closedSandCount[nr][nc]++
                    }
                }
            }
        }

        while (wave()) { }

        println(count)
    }

    private fun wave(): Boolean {
        removeSands.clear()

        for (i in 0..<candidateSands.size) {
            val (r, c) = candidateSands[i]
            if (closedSandCount[r][c] >= map[r][c]) {
                removeSands.add(candidateSands[i])
                map[r][c] = -1
            }
        }
        candidateSands.clear()
        if (removeSands.size != 0) count++

        val set = mutableSetOf<Int>()
        for (i in 0..<removeSands.size) {
            val (r, c) = removeSands[i]
            for (d in 0..<8) {
                val nr = r + dir[d][0]
                val nc = c + dir[d][1]
                if (isOut(nr, nc) || map[nr][nc] == -1) continue
                closedSandCount[nr][nc]++
                if (set.add(nr * C + nc)) candidateSands.add(SandPos(nr, nc))
            }
        }

        return candidateSands.isNotEmpty()
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
}

fun main() {
    BOJ10711().run()
}