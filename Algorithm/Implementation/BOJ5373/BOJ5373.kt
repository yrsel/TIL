package Algorithm.Implementation.BOJ5373

import java.io.BufferedReader

class BOJ5373 {
    private val mapper = mapOf('U' to 0, 'D' to 1, 'F' to 2, 'B' to 3, 'L' to 4, 'R' to 5)
    fun solve() = with(System.`in`.bufferedReader()) {
        println(buildString {
            repeat(toInt()) {
                val dice = initDice()
                toInt()
                val commands = readLine().split(" ").map { it }
                commands.forEach {
                    val surface = it[0]
                    val dir = it[1]
                    turn(surface, dir, dice)
                }
                appendLine(topStatus(dice[0]))
            }
        })
    }

    // 0 : 위 , 1: 아래, 2: 앞, 3: 뒤, 4: 왼, 5: 오
    private fun turn(surface: Char, dir: Char, dice: Array<Array<CharArray>>) {
        when (surface) {
            'L', 'R' -> leftOrRight(surface, dir, dice)
            'U', 'D' -> topOrBottom(surface, dir, dice)
            'F' -> front(dir, dice)
            else -> back(dir, dice)
        }
    }

    private fun front(dir: Char, dice: Array<Array<CharArray>>) {
        if (dir == '+') turnClockwise(dice[mapper['F']!!]) else turnAntiClockwise(dice[mapper['F']!!])
        val tmp = arrayOf(dice[0][2][0], dice[0][2][1], dice[0][2][2]) // 윗 면
        if (dir == '+') {
            dice[0][2][2] = dice[4][0][2]; dice[0][2][1] = dice[4][1][2]; dice[0][2][0] = dice[4][2][2]
            dice[4][0][2] = dice[1][2][0]; dice[4][1][2] = dice[1][2][1]; dice[4][2][2] = dice[1][2][2]
            dice[1][2][2] = dice[5][0][0]; dice[1][2][1] = dice[5][1][0]; dice[1][2][0] = dice[5][2][0]
            dice[5][0][0] = tmp[0]; dice[5][1][0] = tmp[1]; dice[5][2][0] = tmp[2]
            return
        }
        dice[0][2][0] = dice[5][0][0]; dice[0][2][1] = dice[5][1][0]; dice[0][2][2] = dice[5][2][0]
        dice[5][2][0] = dice[1][2][0]; dice[5][1][0] = dice[1][2][1]; dice[5][0][0] = dice[1][2][2]
        dice[1][2][0] = dice[4][0][2]; dice[1][2][1] = dice[4][1][2]; dice[1][2][2] = dice[4][2][2]
        dice[4][2][2] = tmp[0]; dice[4][1][2] = tmp[1]; dice[4][0][2] = tmp[2]
    }

    private fun back(dir: Char, dice: Array<Array<CharArray>>) {
        if (dir == '-') turnClockwise(dice[mapper['B']!!]) else turnAntiClockwise(dice[mapper['B']!!])
        val tmp = arrayOf(dice[0][0][0], dice[0][0][1], dice[0][0][2]) // 윗 면
        if (dir == '+') {
            dice[0][0][0] = dice[5][0][2]; dice[0][0][1] = dice[5][1][2]; dice[0][0][2] = dice[5][2][2]
            dice[5][2][2] = dice[1][0][0]; dice[5][1][2] = dice[1][0][1]; dice[5][0][2] = dice[1][0][2]
            dice[1][0][0] = dice[4][0][0]; dice[1][0][1] = dice[4][1][0]; dice[1][0][2] = dice[4][2][0]
            dice[4][2][0] = tmp[0]; dice[4][1][0] = tmp[1]; dice[4][0][0] = tmp[2]
            return
        }
        dice[0][0][2] = dice[4][0][0]; dice[0][0][1] = dice[4][1][0]; dice[0][0][0] = dice[4][2][0]
        dice[4][0][0] = dice[1][0][0]; dice[4][1][0] = dice[1][0][1]; dice[4][2][0] = dice[1][0][2]
        dice[1][0][2] = dice[5][0][2]; dice[1][0][1] = dice[5][1][2]; dice[1][0][0] = dice[5][2][2]
        dice[5][0][2] = tmp[0]; dice[5][1][2] = tmp[1]; dice[5][2][2] = tmp[2]
    }

    private fun leftOrRight(surface: Char, dir: Char, dice: Array<Array<CharArray>>) {
        val col = if (surface == 'L') 0 else 2
        if (dir == '+') turnClockwise(dice[mapper[surface]!!]) else turnAntiClockwise(dice[mapper[surface]!!])
        val tmp = arrayOf(dice[0][0][col], dice[0][1][col], dice[0][2][col]) // 윗 면
        if (surface == 'R' && dir == '+' || (surface == 'L' && dir == '-')) {
            dice[0][0][col] = dice[2][0][col]; dice[0][1][col] = dice[2][1][col]; dice[0][2][col] = dice[2][2][col]
            dice[2][2][col] = dice[1][0][col]; dice[2][1][col] = dice[1][1][col]; dice[2][0][col] = dice[1][2][col]
            dice[1][0][col] = dice[3][0][col]; dice[1][1][col] = dice[3][1][col]; dice[1][2][col] = dice[3][2][col]
            dice[3][2][col] = tmp[0]; dice[3][1][col] = tmp[1]; dice[3][0][col] = tmp[2] // 윗 -> 뒷
            return
        }
        dice[0][2][col] = dice[3][0][col]; dice[0][1][col] = dice[3][1][col]; dice[0][0][col] = dice[3][2][col]
        dice[3][0][col] = dice[1][0][col]; dice[3][1][col] = dice[1][1][col]; dice[3][2][col] = dice[1][2][col]
        dice[1][2][col] = dice[2][0][col]; dice[1][1][col] = dice[2][1][col]; dice[1][0][col] = dice[2][2][col]
        dice[2][0][col] = tmp[0]; dice[2][1][col] = tmp[1]; dice[2][2][col] = tmp[2]
    }

    // 0 : 위 , 1: 아래, 2: 앞, 3: 뒤, 4: 왼, 5: 오
    private fun topOrBottom(surface: Char, dir: Char, dice: Array<Array<CharArray>>) {
        val row = if (surface == 'U') 0 else 2
        val tmp = arrayOf(dice[3][row][0], dice[3][row][1], dice[3][row][2]) // 뒷면
        if (surface == 'U' && dir == '+' || surface == 'D' && dir == '-') {
            turnClockwise(dice[mapper[surface]!!])
            dice[3][row][2] = dice[4][row][0]; dice[3][row][1] = dice[4][row][1]; dice[3][row][0] = dice[4][row][2]
            dice[4][row][0] = dice[2][row][0]; dice[4][row][1] = dice[2][row][1]; dice[4][row][2] = dice[2][row][2]
            dice[2][row][0] = dice[5][row][0]; dice[2][row][1] = dice[5][row][1]; dice[2][row][2] = dice[5][row][2]
            dice[5][row][2] = tmp[0]; dice[5][row][1] = tmp[1]; dice[5][row][0] = tmp[2]// 뒷 -> 오
            return
        }
        turnAntiClockwise(dice[mapper[surface]!!])
        dice[3][row][2] = dice[5][row][0]; dice[3][row][1] = dice[5][row][1]; dice[3][row][0] = dice[5][row][2]
        dice[5][row][0] = dice[2][row][0]; dice[5][row][1] = dice[2][row][1]; dice[5][row][2] = dice[2][row][2]
        dice[2][row][0] = dice[4][row][0]; dice[2][row][1] = dice[4][row][1]; dice[2][row][2] = dice[4][row][2]
        dice[4][row][2] = tmp[0]; dice[4][row][1] = tmp[1]; dice[4][row][0] = tmp[2]// 뒷 -> 왼
    }

    private fun turnClockwise(map: Array<CharArray>) {
        val copy = map.copy()
        for (i in 0..<3) {
            for (j in 0..<3) {
                map[i][j] = copy[2 - j][i]
            }
        }
    }

    private fun turnAntiClockwise(map: Array<CharArray>) {
        val copy = map.copy()
        for (i in 0..<3) {
            for (j in 0..<3) {
                map[i][j] = copy[j][2 - i]
            }
        }
    }

    private fun Array<CharArray>.copy(): Array<CharArray> {
        return Array(3) { i ->
            CharArray(3) { j ->
                this[i][j]
            }
        }
    }

    // 0 : 위 , 1: 아래, 2: 앞, 3: 뒤, 4: 왼, 5: 오
    private fun initDice(): Array<Array<CharArray>> {
        return Array(6) { i ->
            Array(3) {
                CharArray(3) {
                    when (i) {
                        0 -> 'w'
                        1 -> 'y'
                        2 -> 'r'
                        3 -> 'o'
                        4 -> 'g'
                        else -> 'b'
                    }
                }
            }
        }
    }

    private fun StringBuilder.topStatus(top: Array<CharArray>): String {
        return top.joinToString("\n") { it.joinToString("") { e -> e.toString() } }
    }

    private fun BufferedReader.toInt(): Int {
        return readLine().toInt()
    }
}

fun main() {
    BOJ5373().solve()
}