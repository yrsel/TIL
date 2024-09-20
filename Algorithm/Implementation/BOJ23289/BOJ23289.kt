package Algorithm.Implementation.BOJ23289

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

class BOJ23289 {
    private lateinit var map: Array<Array<IntArray>>

    private data class Machine(val r: Int, val c: Int, val dir: Int, val points: MutableList<Point> = mutableListOf())
    private data class Point(val r: Int, val c: Int, val point: Int)

    private val machines = mutableListOf<Machine>() // 온풍기들
    private val searchPos = mutableListOf<Point>() // 정답을 위해 비교할 칸들

    private val spreadDir = arrayOf(
        arrayOf(arrayOf(-1, 1), arrayOf(0, 1), arrayOf(1, 1)),
        arrayOf(arrayOf(-1, -1), arrayOf(0, -1), arrayOf(1, -1)),
        arrayOf(arrayOf(-1, -1), arrayOf(-1, 0), arrayOf(-1, 1)),
        arrayOf(arrayOf(1, -1), arrayOf(1, 0), arrayOf(1, 1))
    ) // 오,왼,위,아래

    private var R = 0
    private var C = 0

    fun run() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        R = st.nextToken().toInt()
        C = st.nextToken().toInt()
        val K = st.nextToken().toInt()

        map = Array(3) { Array(R + 2) { IntArray(C + 2) } }

        for (i in 1..R) {
            st = StringTokenizer(readLine(), " ")
            for (j in 1..C) {
                val number = st.nextToken().toInt()
                if (number != 0) {
                    if (number == 5) {
                        searchPos.add(Point(i, j, K))
                    } else {
                        machines.add(Machine(i, j, number - 1))
                    }
                }
            }
        }

        repeat(readLine().toInt()) {
            st = StringTokenizer(readLine(), " ")
            val r = st.nextToken().toInt()
            val c = st.nextToken().toInt()
            val wallPos = st.nextToken().toInt() + 1
            map[wallPos][r][c] = -1
        }

        setAvailMachineSpread() // 머신이 퍼지는 범위 미리 계산
        var result = 0
        while (result != 101) {
            if (result != 0) wind() // 머신이 퍼지는 범위 처음 체크하면서 표시 result == 0 일때는 온풍기 작동 하지 않아도 된다.
            separate()
            minusBoundary()
            result++
            if (isSatisfied()) break
        }

        println(result)
    }

    private fun setAvailMachineSpread() {
        machines.forEach {
            val q = ArrayDeque<Point>()
            val startPoint = Point(it.r + spreadDir[it.dir][1][0], it.c + spreadDir[it.dir][1][1], 5)
            map[0][startPoint.r][startPoint.c] += 5
            it.points.add(startPoint)
            q.addLast(startPoint)

            val hs = hashSetOf(startPoint.r * C + startPoint.c)
            while (q.isNotEmpty()) {
                var size = q.size
                while (size-- > 0) {
                    val curr = q.removeFirst()
                    for (d in 0..<3) {
                        val nr = curr.r + spreadDir[it.dir][d][0]
                        val nc = curr.c + spreadDir[it.dir][d][1]
                        if (isWall(it.dir, curr.r, curr.c, d) || isOut(nr, nc) || !hs.add(nr * C + nc)) continue
                        val next = Point(nr, nc, curr.point - 1)
                        map[0][nr][nc] += next.point
                        if (curr.point != 2) { // curr point == 2 일때는 마지막으로 퍼질 수 있는 크기
                            q.addLast(next)
                        }
                        it.points.add(next)
                    }
                }
            }
        }
    }

    private fun wind() {
        machines.forEach {
            it.points.forEach { element ->
                map[0][element.r][element.c] += element.point
            }
        }
    }

    private fun separate() {
        val increaseMap = Array(R + 1) { IntArray(C + 1) }
        val q = ArrayDeque<Point>()

        for (i in 1..R) {
            for (j in 1..C) {
                if (map[0][i][j] > 0) q.addLast(Point(i, j, map[0][i][j]))
            }
        }

        val hs = hashSetOf<Int>()
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            hs.add(curr.r * C + curr.c)
            for (d in 0..<4) {
                val nr = curr.r + spreadDir[d][1][0]
                val nc = curr.c + spreadDir[d][1][1]

                if (isOut(nr, nc) || !isAvailSeparate(d, curr.r, curr.c) || hs.contains(nr * C + nc)) continue

                val diff = abs(map[0][nr][nc] - map[0][curr.r][curr.c]) / 4
                if (diff <= 0) continue

                if (map[0][nr][nc] > map[0][curr.r][curr.c]) {
                    increaseMap[nr][nc] -= diff
                    increaseMap[curr.r][curr.c] += diff
                } else {
                    increaseMap[nr][nc] += diff
                    increaseMap[curr.r][curr.c] -= diff
                }
            }
        }

        for (r in 1..R) {
            for (c in 1..C) {
                if (increaseMap[r][c] != 0) map[0][r][c] += increaseMap[r][c]
            }
        }
    }

    private fun minusBoundary() {
        for (r in 1..R) {
            if (map[0][r][1] != 0) --map[0][r][1]
            if (map[0][r][C] != 0) --map[0][r][C]
        }

        for (c in 2..<C) {
            if (map[0][1][c] != 0) --map[0][1][c]
            if (map[0][R][c] != 0) --map[0][R][c]
        }
    }

    // 오,왼,위,아래
    private fun isAvailSeparate(d: Int, r: Int, c: Int): Boolean {
        return when (d) {
            0 -> map[2][r][c] != -1
            1 -> map[2][r][c - 1] != -1
            2 -> map[1][r][c] != -1
            3 -> map[1][r + 1][c] != -1
            else -> true
        }
    }

    private fun isSatisfied(): Boolean {
        searchPos.forEach {
            if (map[0][it.r][it.c] < it.point) return false
        }
        return true
    }

    private fun isOut(r: Int, c: Int) = r <= 0 || c <= 0 || r > R || c > C

    // [1][][] : r,c 위에 벽 , [2][][] : r,c 오른쪽에 벽
    private fun isWall(dir: Int, r: Int, c: Int, d: Int): Boolean {
        return when (dir) {
            0 -> { // 오
                when (d) {
                    0 -> {
                        if (map[1][r][c] == -1 || map[2][r - 1][c] == -1) return true
                    }

                    1 -> {
                        if (map[2][r][c] == -1) return true
                    }

                    2 -> {
                        if (map[1][r + 1][c] == -1 || map[2][r + 1][c] == -1) return true
                    }
                }
                false
            }

            1 -> { // 왼
                when (d) {
                    0 -> {
                        if (map[1][r][c] == -1 || map[2][r - 1][c - 1] == -1) return true
                    }

                    1 -> {
                        if (map[2][r][c - 1] == -1) return true
                    }

                    2 -> {
                        if (map[1][r + 1][c] == -1 || map[2][r + 1][c - 1] == -1) return true
                    }
                }
                false
            }

            2 -> { // 위
                when (d) {
                    0 -> {
                        if (map[1][r][c - 1] == -1 || map[2][r][c - 1] == -1) return true
                    }

                    1 -> {
                        if (map[1][r][c] == -1) return true
                    }

                    2 -> {
                        if (map[1][r][c + 1] == -1 || map[2][r][c] == -1) return true
                    }
                }
                false
            }

            else -> { // 아래
                when (d) {
                    0 -> {
                        if (map[1][r + 1][c - 1] == -1 || map[2][r][c - 1] == -1) return true
                    }

                    1 -> {
                        if (map[1][r + 1][c] == -1) return true
                    }

                    2 -> {
                        if (map[1][r + 1][c + 1] == -1 || map[2][r][c] == -1) return true
                    }
                }
                false
            }
        }
    }
}

fun main() {
    BOJ23289().run()
}