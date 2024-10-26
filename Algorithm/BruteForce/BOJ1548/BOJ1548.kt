package Algorithm.BruteForce.BOJ1548

import java.io.StreamTokenizer

class BOJ1548 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        val arr = IntArray(input()) { input() }
        var result = 0
        if (arr.size <= 2) result = arr.size
        else {
            arr.sort()
            for (i in 0..<arr.size - 2) {
                for (j in i + 1..<arr.size - 1) {
                    var cnt = 2
                    for (k in j + 1..<arr.size) {
                        if (isSatisfied(arr[i], arr[j], arr[k])) cnt++
                        else break
                    }
                    result = Math.max(result, cnt)
                }
            }
        }
        println(result)
    }

    private fun isSatisfied(a: Int, b: Int, c: Int) = a + b > c && b + c > a && c + a > b
}

fun main() {
    BOJ1548().solve()
}
