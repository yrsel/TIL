package Algorithm.BruteForce.BOJ16937

import java.io.StreamTokenizer

class BOJ16937 {

    private var H = 0
    private var W = 0
    private var result = 0
    private lateinit var stickers: Array<IntArray>

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        H = input()
        W = input()
        stickers = Array(input()) { IntArray(2) { input() } }

        find(false)
        find(true)
        println(result)
    }

    private fun find(turned: Boolean) {
        var r: Int
        var c: Int
        for (i in 0..<stickers.size - 1) {
            if (turned) {
                r = stickers[i][1]; c = stickers[i][0]
            } else {
                r = stickers[i][0]; c = stickers[i][1]
            }
            if (r > H || c > W) continue
            for (j in i + 1..<stickers.size) {
                var compR = stickers[j][0]
                var compC = stickers[j][1]
                if (isAttach(r, c, compR, compC)) {
                    result = Math.max(result, r * c + compR * compC)
                }
                // 회전
                compR = stickers[j][1]
                compC = stickers[j][0]
                if (isAttach(r, c, compR, compC)) {
                    result = Math.max(result, r * c + compR * compC)
                }
            }
        }
    }

    private fun isAttach(attachedR: Int, attachedC: Int, candidateR: Int, candidateC: Int): Boolean {
        return (H - attachedR >= candidateR && W >= candidateC) || (H >= candidateR && W - attachedC >= candidateC)
    }
}

fun main() {
    BOJ16937().run()
}