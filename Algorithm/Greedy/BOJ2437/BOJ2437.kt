package Algorithm.Greedy.BOJ2437

import java.io.StreamTokenizer

class BOJ2437 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        val N = toInt()
        val arr = IntArray(N) { toInt() }
        arr.sort()
        var comp = 1
        for (i in 0..<N) {
            if (comp < arr[i]) break
            comp += arr[i]
        }
        println(comp)
    }
}

fun main() {
    BOJ2437().solve()
}