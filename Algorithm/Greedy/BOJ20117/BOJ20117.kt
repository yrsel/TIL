package Algorithm.Greedy.BOJ20117

import java.io.StreamTokenizer

class BOJ20117 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        val arr = IntArray(input()) { input() }
        arr.sort()
        var left = 0
        var right = arr.size - 1
        var result = 0
        while (arr[left] != arr[right] && left < right) {
            result += arr[right] * 2
            left++
            right--
        }
        if (left < right) {
            result += (right - left + 1) * arr[left]
        } else if (left == right) {
            result += arr[left]
        }
        println(result)
    }
}

fun main() {
    BOJ20117().solve()
}