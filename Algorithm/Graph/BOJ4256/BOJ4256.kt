package Algorithm.Graph.BOJ4256

import java.io.StreamTokenizer

class BOJ4256 {

    private lateinit var preOrders: IntArray
    private lateinit var inOrders: IntArray
    private var preOrderIdx = 0
    private val sb = StringBuilder()

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        repeat(input()) {
            val n = input()
            preOrders = IntArray(n) { input() }
            inOrders = IntArray(n) { input() }
            preOrderIdx = 0
            makePostOrders(0, n - 1)
            sb.appendLine()
        }
        println(sb.toString())
    }

    private fun makePostOrders(from: Int, to: Int) {
        if (from > to) return

        val parent = preOrders[preOrderIdx++]
        val standard = inOrders.indexOf(parent)

        makePostOrders(from, standard - 1) // 왼쪽
        makePostOrders(standard + 1, to) // 오른쪽
        sb.append("$parent ") // 왼쪽 오른쪽 다 방문했으면 방문 , 후위 순회
    }


}

fun main() {
    BOJ4256().run()
}
