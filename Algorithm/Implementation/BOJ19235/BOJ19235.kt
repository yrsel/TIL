package Algorithm.Implementation.BOJ19235

import java.io.StreamTokenizer
import java.util.*

private const val BOTTOM = 5
private const val EMPTY = 0
private const val ONE_BLOCK = 1
private const val COL_BLOCK = 2
private const val ROW_BLOCK = 3

class BOJ19235 {
    private val blueStatus = Array(6) { IntArray(4) }
    private val greenStatus = Array(6) { IntArray(4) }
    private var scores = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        repeat(toInt()) {
            downBlock(toInt(), toInt(), toInt())
            broken(greenStatus)
            broken(blueStatus)
            removeLine(greenStatus)
            removeLine(blueStatus)
        }

        println(scores)
        println(greenStatus.existCount() + blueStatus.existCount())
    }

    private fun downBlock(t: Int, r: Int, c: Int) {
        when (t) {
            1 -> {
                downOneBlockOrRowBlock(greenStatus, c, ONE_BLOCK)
                downOneBlockOrRowBlock(blueStatus, r, ONE_BLOCK)
            }

            2 -> {
                downColBlock(greenStatus, c)
                downOneBlockOrRowBlock(blueStatus, r, ROW_BLOCK)
            }

            3 -> {
                downOneBlockOrRowBlock(greenStatus, c, ROW_BLOCK)
                downColBlock(blueStatus, r)
            }
        }
    }

    private fun downOneBlockOrRowBlock(status: Array<IntArray>, col: Int, blockNumber: Int) {
        var r = 0
        while (r != BOTTOM) {
            if (status[r + 1][col] != EMPTY) break
            ++r
        }
        status[r][col] = blockNumber
        if (blockNumber == ROW_BLOCK) status[r - 1][col] = blockNumber
    }

    private fun downColBlock(status: Array<IntArray>, col: Int) {
        var r = 0
        while (r != BOTTOM) {
            if (status[r + 1][col] != EMPTY || status[r + 1][col + 1] != EMPTY) break
            ++r
        }
        status[r][col] = COL_BLOCK
        status[r][col + 1] = COL_BLOCK
    }

    private fun broken(status: Array<IntArray>) {
        var isBroken = false
        for (r in 2..5) {
            if (status[r].all { it != EMPTY }) {
                scores++
                isBroken = true
                status[r].fill(EMPTY)
            }
        }
        if (isBroken) {
            downStatus(status)
            broken(status) // 내려간다음 다시 채워진 게 있는 지 다시 한번 체크
        }
    }

    private fun downStatus(status: Array<IntArray>) {
        for (r in BOTTOM - 1 downTo 0) {
            var colFlag = false
            for (c in 0..<4) {
                if (colFlag) {
                    colFlag = false
                    continue
                }
                if (status[r][c] == EMPTY) continue
                var curR = r
                if (status[r][c] == COL_BLOCK) {
                    colFlag = true
                    while (curR != BOTTOM) {
                        if (status[curR + 1][c] != EMPTY || status[curR + 1][c + 1] != EMPTY) break
                        ++curR
                    }
                    if (r != curR) {
                        status[curR][c] = COL_BLOCK
                        status[curR][c + 1] = COL_BLOCK
                        status[r][c] = EMPTY
                        status[r][c + 1] = EMPTY
                    }
                } else {
                    while (curR != BOTTOM) {
                        if (status[curR + 1][c] != EMPTY) break
                        ++curR
                    }
                    if (r != curR) {
                        status[curR][c] = status[r][c]
                        status[r][c] = EMPTY
                    }
                }
            }
        }
    }

    private fun removeLine(status: Array<IntArray>) {
        var removeLine = 0
        if (status[0].any { it != EMPTY }) ++removeLine
        if (status[1].any { it != EMPTY }) ++removeLine

        if (removeLine != 0) {
            val stack = Stack<Int>()
            for (c in 0..<4) {
                for (r in 0..BOTTOM - removeLine) {
                    stack.push(status[r][c])
                    status[r][c] = EMPTY
                }
                var idx = BOTTOM
                while (idx >= 0) {
                    if (stack.isNotEmpty()) status[idx--][c] = stack.pop()
                    else status[idx--][c] = EMPTY
                }
            }
        }
    }

    private fun Array<IntArray>.existCount(): Int {
        var cnt = 0
        for (r in 0..BOTTOM) {
            cnt += this[r].count { it != EMPTY }
        }
        return cnt
    }

}

fun main() {
    BOJ19235().solve()
}