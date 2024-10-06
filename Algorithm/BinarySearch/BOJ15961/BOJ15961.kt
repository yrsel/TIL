package Algorithm.BinarySearch.BOJ15961

import java.io.StreamTokenizer

class BOJ15961 {
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        fun input(): Int {
            nextToken()
            return nval.toInt()
        }

        val N = input()
        val d = input()
        val k = input()
        val c = input()
        val arr = IntArray(N) { input() }

        val counter = IntArray(d + 1)
        var left = 0
        var right = 0
        var curCnt = 0

        while (right != k) {
            if (counter[arr[right]] == 0) curCnt++
            counter[arr[right++]]++
        }
        right--

        var result = 0

        while (left != N) {
            result = if (counter[c] == 0) (curCnt + 1).coerceAtLeast(result)
            else curCnt.coerceAtLeast(result)

            if (--counter[arr[left++]] == 0) curCnt--
            right = (right + 1) % N
            if (++counter[arr[right]] == 1) curCnt++
        }

        println(result)
    }
}

fun main() {
    BOJ15961().run()
}