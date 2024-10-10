package Algorithm.BinarySearch.BOJ2118

import java.io.StreamTokenizer

class BOJ2118 {
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        val arr = IntArray(input()) { input() }

        var result = -1
        val maxLen = arr.sum()
        var left = 0
        var right = 0
        var cur = arr[right]

        while (right < arr.size - 1) {
            val len = cur.coerceAtMost(maxLen - cur)
            result = len.coerceAtLeast(result)

            if (cur == len) { // 아직까지는 left부터 right까지의 길이가 최소의 값
                cur += arr[++right]
            } else {
                cur -= arr[left++]
            }

            if (right < left) break
        }

        println(result)
    }
}

fun main() {
    BOJ2118().run()
}