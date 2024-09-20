package Algorithm.Implementation.BOJ28279

import java.util.*

class BOJ28279 {
    private val result = StringBuilder()
    private val deque = IntArray(1_000_000)
    private var head = 500_000
    private var tail = 500_000
    private var size = 0

    fun run() = with(System.`in`.bufferedReader()) {
        repeat(readLine().toInt()) {
            val st = StringTokenizer(readLine(), " ")
            when (st.nextToken().toInt()) {
                1 -> {
                    deque[--head] = st.nextToken().toInt()
                    if (head == 0) head = 1_000_000
                    ++size
                }

                2 -> {
                    deque[tail++] = st.nextToken().toInt()
                    if (tail == 1_000_000) tail = 0
                    ++size
                }

                3 -> {
                    if (size == 0) result.appendLine(-1)
                    else {
                        --size
                        if (head == 1_000_000) head = 0
                        result.appendLine(deque[head++])
                    }
                }

                4 -> {
                    if (size == 0) result.appendLine(-1)
                    else {
                        --size
                        if (tail == 0) tail = 1_000_000
                        result.appendLine(deque[--tail])
                    }
                }

                5 -> {
                    result.appendLine(size)
                }

                6 -> {
                    if (size == 0) result.appendLine(1)
                    else result.appendLine(0)
                }

                7 -> {
                    if (size == 0) result.appendLine(-1)
                    else result.appendLine(deque[head])
                }

                8 -> {
                    if (size == 0) result.appendLine(-1)
                    else {
                        if (tail == 0) result.appendLine(deque[999_999])
                        else result.appendLine(deque[tail - 1])
                    }
                }
            }
        }
        println(result.toString())
    }
}

fun main() {
    BOJ28279().run()
}