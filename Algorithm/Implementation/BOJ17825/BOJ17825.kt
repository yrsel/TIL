package Algorithm.Implementation.BOJ17825

class BOJ17825 {
    private var moves = IntArray(10)
    private val map = arrayOf(
        arrayOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40),
        arrayOf(10, 13, 16, 19, 25, 30, 35, 40),
        arrayOf(20, 22, 24, 25, 30, 35, 40),
        arrayOf(30, 28, 27, 26, 25, 30, 35, 40)
    )
    private lateinit var visited: HashSet<Int>

    private data class Horse(
        var mapIndex: Int = 0,
        var posIndex: Int = 0,
        var isStarted: Boolean = false,
        var isArrived: Boolean = false
    )

    private lateinit var horses: Array<Horse>
    private val selected = IntArray(10)
    private var result = 0

    fun run() {
        moves = readln().split(" ").map { it.toInt() }.toIntArray()
        perm(0)
        println(result)
    }

    private fun perm(idx: Int) {
        if (idx == 10) {
            horses = Array(4) { Horse() }
            visited = hashSetOf() // 25,30,35,40 중복 체크
            var sum = 0
            for (i in 0..9) {
                val horseNumber = selected[i]
                val moveCnt = moves[i]
                val score = getScore(horses[horseNumber], moveCnt)
                if (score == -1) {
                    sum = 0
                    break
                }
                sum += score
            }
            result = result.coerceAtLeast(sum)
            return
        }

        for (i in 0..3) {
            selected[idx] = i
            perm(idx + 1)
        }
    }

    private fun getScore(horse: Horse, moveCnt: Int): Int {
        if (horse.isArrived) return -1 // 모든 경우를 탐색하니, 이미 도착한 것을 움직이려고 할 경우 바로 종료 해도 됨
        if (!horse.isStarted) horse.isStarted = true

        val nextPos = horse.posIndex + moveCnt

        visited.remove(map[horse.mapIndex][horse.posIndex])

        if (isOut(horse.mapIndex, nextPos)) { // 도착지에 도착한 경우
            horse.isArrived = true
            return 0
        }

        if (isExistPos(horse.mapIndex, nextPos)) return -1

        val score = map[horse.mapIndex][nextPos]
        if (horse.mapIndex == 0 && isBlueGameBoard(score)) { // 파란색 방향의 게임판으로 이동
            horse.mapIndex = score / 10
            horse.posIndex = 0
        } else {  // 이미 파란색 방향의 게임판에서 진행중이거나 빨간색 방향으로 계속 진행되는 경우
            horse.posIndex = nextPos
            if (score == 40) visited.add(40)
            else if (score == 25 || score == 30 || score == 35) visited.add(score)
        }
        return score
    }

    private fun isExistPos(mapIndex: Int, pos: Int): Boolean {
        val score = map[mapIndex][pos]
        if (score == 40 && visited.contains(40)) return true
        if (mapIndex != 0 && visited.contains(score)) return true // 25,30,35 중복 위치 체크
        horses.forEach { horse ->
            if (!horse.isArrived && horse.isStarted) {
                if (mapIndex == 0 && isBlueGameBoard(score) &&
                    (horse.mapIndex == (score / 10) && horse.posIndex == 0)
                ) {
                    return true
                } else {
                    if (horse.mapIndex == mapIndex && horse.posIndex == pos) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun isBlueGameBoard(point: Int) = point % 10 == 0 && point != 40   // 파란색 방향 게임판으로 이동해야하는 경우

    private fun isOut(mapIndex: Int, curIndex: Int) = map[mapIndex].size <= curIndex
}


fun main() {
    BOJ17825().run()
}