package Algorithm.Graph.BOJ23326

import java.util.*

class BOJ23326 {

    private val indexes = TreeSet<Int>()
    private var curPos = 0
    private var N = 0
    private lateinit var st: StringTokenizer
    private val result = StringBuilder()

    fun run() {
        val (_N, Q) = readln().split(" ").map { it.toInt() }
        N = _N
        st = StringTokenizer(readln(), " ")
        for (i in 0..<N) {
            if (st.nextToken().toInt() == 1) {
                indexes.add(i)
            }
        }

        repeat(Q) {
            commandHandle(readln())
        }

        println(result.toString())
    }

    private fun commandHandle(command: String) {
        when (command[0]) {
            '1' -> {
                val (_, pos) = command.split(" ").map { it.toInt() }
                if (!indexes.add(pos - 1)) {
                    indexes.remove(pos - 1)
                }
            }

            '2' -> {
                val (_, moveCnt) = command.split(" ").map { it.toInt() }
                curPos = (curPos + moveCnt) % N
            }

            else -> { // 3
                if (indexes.isEmpty()) {
                    result.appendLine(-1)
                    return
                }

                val num = indexes.ceiling(curPos)
                if (num == null) {
                    result.appendLine(N - curPos + indexes.first())
                } else {
                    result.appendLine(num - curPos)
                }
            }

        }
    }
}

fun main() {
    BOJ23326().run()
}