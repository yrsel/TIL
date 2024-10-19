package Algorithm.Dp.BOJ2491

import java.io.StreamTokenizer

class BOJ2491 {
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        val N = input()
        val arr = IntArray(N) { input() }
        var asc = 1
        var desc = 1
        var result = 1
        for (i in 1..<N) {
            if (arr[i] > arr[i - 1]) {
                asc++; desc = 1
            } else if (arr[i] < arr[i - 1]) {
                asc = 1; desc++
            } else {
                asc++; desc++
            }
            val cur = asc.coerceAtLeast(desc)
            result = result.coerceAtLeast(cur)
        }
        println(result)
    }
}

fun main() {
    BOJ2491().run()
}