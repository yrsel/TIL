package Algorithm.BruteForce.BOJ15779

import java.io.StreamTokenizer

class BOJ15779 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val n = toInt()
        val arr = IntArray(n) { toInt() }

        var max = 2
        var curCnt = 2
        for (i in 0..<n - 2) {
            if (isSatisfied(arr[i], arr[i + 1], arr[i + 2])) {
                curCnt++
            } else {
                max = max.coerceAtLeast(curCnt)
                curCnt = 2
            }
        }
        println(max.coerceAtLeast(curCnt))
    }

    private fun isSatisfied(a: Int, b: Int, c: Int): Boolean {
        return !(b in a..c || b in c..a)
    }

    private fun StreamTokenizer.toInt(): Int {
        nextToken(); return nval.toInt()
    }
}

fun main() {
    BOJ15779().solve()
}