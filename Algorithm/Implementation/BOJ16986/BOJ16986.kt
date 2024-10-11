package Algorithm.Implementation.BOJ16986

import java.io.StreamTokenizer

class BOJ16986 {

    private var N = 0
    private var goal = 0
    private lateinit var map: Array<IntArray>
    private lateinit var friends: Array<IntArray>

    private val K = 0
    private val M = 1
    private val J = 2

    private var isSatisfied = false

    private lateinit var turnStatus: IntArray
    private lateinit var winCounter: IntArray
    private lateinit var selected: IntArray

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        N = input(); goal = input()
        map = Array(N) { IntArray(N) { input() } }
        friends = Array(2) { IntArray(20) { input() - 1 } }
        selected = IntArray(10) { -1 }

        perm(0, 0)

        if (isSatisfied) println(1) else println(0)
    }

    private fun perm(idx: Int, flag: Int) {
        if (isSatisfied) return
        if (idx == N) {
            turnStatus = IntArray(3)
            winCounter = IntArray(3)
            play()
            return
        }

        for (i in 0..<N) {
            if (flag.and(1.shl(i)) != 0) continue
            selected[idx] = i
            perm(idx + 1, flag.or(1.shl(i)))
        }
    }

    private fun play(playJ: Boolean = true, playK: Boolean = true) {
        if (turnStatus[J] == N) return
        if (!playJ) {
            if (map[friends[K][turnStatus[K]]][friends[M][turnStatus[M]]] == 2) {
                firstArgWin(K, M)
                if (isGameOver(K)) return
                play()
            } else {
                firstArgWin(M, K)
                if (isGameOver(M)) return
                play(playK = false)
            }
        } else {
            val player = if (!playK) M else K
            if (map[selected[turnStatus[J]]][friends[player][turnStatus[player]]] == 2) {
                firstArgWin(J, player)
                if (isGameOver(J)) {
                    isSatisfied = true
                    return
                }
                if (player == M) play()
                else play(playK = false)
            } else {
                firstArgWin(player, J)
                if (isGameOver(player)) return
                play(playJ = false)
            }
        }
    }

    private fun firstArgWin(playerX: Int, playerY: Int) {
        winCounter[playerX]++

        turnStatus[playerX]++
        turnStatus[playerY]++
    }

    private fun isGameOver(player: Int): Boolean {
        return winCounter[player] == goal
    }

}

fun main() {
    BOJ16986().run()
}