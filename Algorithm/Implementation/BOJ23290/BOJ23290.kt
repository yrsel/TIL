package Algorithm.Implementation.BOJ23290

import java.util.*

class BOJ23290 {

    private val fishDir = arrayOf(
        arrayOf(0, -1), arrayOf(-1, -1), arrayOf(-1, 0), arrayOf(-1, 1), arrayOf(0, 1), arrayOf(1, 1),
        arrayOf(1, 0), arrayOf(1, -1)
    )
    private val sharkDir =
        arrayOf(arrayOf(-100, -100), arrayOf(-1, 0), arrayOf(0, -1), arrayOf(1, 0), arrayOf(0, 1)) // 0번 인덱스는 더미 데이터

    private data class Fish(var r: Int, var c: Int, val dir: Int = 0, val cnt: Int = 0)

    private val MAX_SIZE = 4
    private val map = Array(MAX_SIZE) { Array(MAX_SIZE) { mutableMapOf<Int, Int>() } }

    private val smells = Array(MAX_SIZE) { IntArray(MAX_SIZE) }
    private val searchDirs = IntArray(64)
    private val copyCandidateFishes = mutableListOf<Fish>()
    private val moveFishes = mutableListOf<Fish>()
    private lateinit var shark: Fish
    private val hs = HashSet<Int>()
    private var S = 0

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= MAX_SIZE || c >= MAX_SIZE

    fun run() {
        initSearchPoints()
        inputs()
        repeat(S) {
            moveFishAndSetCandidateFishes()
            moveShark()
            twoTimeSmellCheck()
            copyFishes()
        }
        println(result())
    }

    private fun initSearchPoints() {
        var idx = 0
        for (i in 1..4) {
            val first = i * 100
            for (j in 1..4) {
                val second = j * 10
                for (k in 1..4) {
                    searchDirs[idx++] = first + second + k
                }
            }
        }
        searchDirs.reverse()
    }

    private fun inputs() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val M = st.nextToken().toInt()
        S = st.nextToken().toInt()
        repeat(M) {
            st = StringTokenizer(readLine(), " ")
            val r = st.nextToken().toInt() - 1
            val c = st.nextToken().toInt() - 1
            val dir = st.nextToken().toInt() - 1
            map[r][c][dir] = map[r][c].getOrDefault(dir, 0) + 1
        }
        st = StringTokenizer(readLine(), " ")
        shark = Fish(st.nextToken().toInt() - 1, st.nextToken().toInt() - 1)
    }

    private fun moveFishAndSetCandidateFishes() {
        moveFishes.clear()
        for (r in 0..<4) {
            for (c in 0..<4) {
                if (map[r][c].values.sum() == 0) continue
                val candidates = map[r][c]
                addCopyCandidateFishes(r, c, candidates) // 복제 마법 물고기들 저장
                for (candidate in candidates) { // 한 칸 이동하는 물고기들 저장
                    if (isMove(r, c, candidate.key, candidate.value)) {  // 이동했을 때만 현재 위치를 제거
                        map[r][c][candidate.key] = 0
                    }
                }
            }
        }

        moveFishes.forEach {
            map[it.r][it.c][it.dir] = map[it.r][it.c].getOrDefault(it.dir, 0) + it.cnt
        } // 이동 가능한 물고기들 이동
    }

    private fun addCopyCandidateFishes(r: Int, c: Int, candidates: MutableMap<Int, Int>) {
        for (candidate in candidates) {
            copyCandidateFishes.add(Fish(r, c, candidate.key, candidate.value))
        }
    }

    private fun isMove(r: Int, c: Int, candidatesDir: Int, cnt: Int): Boolean {
        for (d in 0..<8) {
            val nr = r + fishDir[(candidatesDir - d + 8) % 8][0]
            val nc = c + fishDir[(candidatesDir - d + 8) % 8][1]
            if (isOut(nr, nc) || smells[nr][nc] != 0 || (nr == shark.r && nc == shark.c)) continue
            moveFishes.add(Fish(nr, nc, (candidatesDir - d + 8) % 8, cnt))
            return true
        }
        return false
    }

    private fun moveShark() {
        val moveDirs = findSharkMoveDirs()
        for (d in 0..<3) {
            shark.r += sharkDir[moveDirs[d]][0]
            shark.c += sharkDir[moveDirs[d]][1]
            if (map[shark.r][shark.c].values.sum() != 0) {
                map[shark.r][shark.c].clear() // 물고기 제거
                smells[shark.r][shark.c] = 3 // 물고기 냄새 남기기 ( 다음 작업에서 냄새를 바로 제거하므로 3으로 시작해서 2번 전 연습의 냄새를 0으로 초기화 가능 )
            }
        }
    }

    private fun findSharkMoveDirs(): IntArray {
        var max = 0
        var findDir = -1
        var moveDir: IntArray
        for (dir in searchDirs) {
            moveDir = intArrayOf(dir / 100, (dir / 10) % 10, dir % 10)
            var r = shark.r
            var c = shark.c
            var curCnt = 0
            for (d in 0..<3) {
                val nr = r + sharkDir[moveDir[d]][0]
                val nc = c + sharkDir[moveDir[d]][1]
                if (isOut(nr, nc)) { // 범위 밖으로 나갔다
                    curCnt = -1
                    break
                }
                if (hs.add(nr * 4 + nc)) curCnt += map[nr][nc].values.sum()
                r = nr; c = nc
            }
            hs.clear()
            if (curCnt >= max) {
                max = curCnt
                findDir = dir
            }
        }
        return intArrayOf(findDir / 100, (findDir / 10) % 10, findDir % 10)
    }

    private fun twoTimeSmellCheck() {
        for (i in 0..<4) {
            for (j in 0..<4) {
                if (smells[i][j] != 0) --smells[i][j]
            }
        }
    }

    private fun copyFishes() {
        copyCandidateFishes.forEach {
            map[it.r][it.c][it.dir] = map[it.r][it.c].getOrDefault(it.dir, 0) + it.cnt
        }
        copyCandidateFishes.clear()
    }

    private fun result(): Int {
        var sum = 0
        for (i in 0..<4) {
            for (j in 0..<4) {
                sum += map[i][j].values.sum()
            }
        }
        return sum
    }
}

fun main() {
    BOJ23290().run()
}
