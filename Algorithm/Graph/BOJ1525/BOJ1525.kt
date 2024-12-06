package Algorithm.Graph.BOJ1525

import java.io.StreamTokenizer

class BOJ1525 {
    private data class Info(val r: Int = 0, val c: Int = 0, val map: Array<IntArray> = Array(3) { IntArray(3) })

    private val target = 123_456_780
    private val visited = hashSetOf<Int>()
    private val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        var zeroPos = Info()
        val map = Array(3) { r ->
            IntArray(3) { c ->
                val num = toInt()
                if (num == 0) {
                    zeroPos = Info(r, c)
                }
                num
            }
        }
        zeroPos = zeroPos.copy(map = map)
        println(search(zeroPos))
    }

    private fun search(zeroPos: Info): Int {
        val q = ArrayDeque<Info>()
        q.addLast(zeroPos)
        visited.add(zeroPos.map.toNumbers())
        if (visited.contains(target)) return 0

        var cnt = 0
        while (q.isNotEmpty()) {
            var size = q.size
            cnt++
            while (size-- > 0) {
                val curr = q.removeFirst()
                for (d in 0..<4) {
                    val nr = curr.r + dir[d][0]
                    val nc = curr.c + dir[d][1]
                    if (isOut(nr, nc)) continue
                    curr.map.swap(curr.r, curr.c, nr, nc)
                    if (visited.add(curr.map.toNumbers())) {
                        if (visited.contains(target)) return cnt
                        q.addLast(Info(nr, nc, curr.map.copy()))
                    }
                    curr.map.swap(curr.r, curr.c, nr, nc)
                }
            }
        }
        return -1
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= 3 || c >= 3

    private fun Array<IntArray>.swap(r: Int, c: Int, nr: Int, nc: Int) {
        val tmp = this[r][c]
        this[r][c] = this[nr][nc]
        this[nr][nc] = tmp
    }

    private fun Array<IntArray>.copy(): Array<IntArray> {
        return Array(3) { r ->
            IntArray(3) { c ->
                this[r][c]
            }
        }
    }

    private fun Array<IntArray>.toNumbers(): Int {
        var num = 0
        for (i in 0..<3) {
            for (j in 0..<3) {
                num = num * 10 + this[i][j]
            }
        }
        return num
    }
}

fun main() {
    BOJ1525().solve()
}