package Algorithm.Implementation.BOJ6549

import java.io.StreamTokenizer
import java.util.*

class BOJ6549 {
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        val sb = StringBuilder()

        while (true) {
            val size = input()
            if (size == 0) break
            val arr = IntArray(size) { input() }
            sb.appendLine(calculate(arr))
        }
        println(sb.toString())
    }

    private fun calculate(arr: IntArray): Long {
        val stack = Stack<Int>()
        var result = 0L
        val size = arr.size

        arr.forEachIndexed { index, h ->
            while (stack.isNotEmpty() && arr[stack.peek()] >= h) {
                val height = arr[stack.pop()].toLong()

                val width = if (stack.isEmpty()) index
                else index - stack.peek() - 1

                result = result.coerceAtLeast(height * width)
            }
            stack.push(index)
        }

        while (stack.isNotEmpty()) {
            val height = arr[stack.pop()].toLong()

            val width = if (stack.isEmpty()) size
            else size - stack.peek() - 1

            result = result.coerceAtLeast(height * width)
        }

        return result
    }
}

fun main() {
    BOJ6549().run()
}