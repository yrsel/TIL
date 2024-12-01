package Algorithm.BruteForce.BOJ30105

import java.io.StreamTokenizer

class BOJ30105 {

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }

        val N = toInt()
        val numbers = hashSetOf<Int>()
        val arr = IntArray(N) {
            val number = toInt()
            numbers.add(number)
            number
        }
        val candidates = hashSetOf<Int>()

        for (i in 1..N / 2) {
            candidates.add(arr[i] - arr[0])
        }

        val result = mutableListOf<Int>()
        candidates.forEach {
            var flag = false
            for (i in 0..<N) {
                if (numbers.contains(arr[i] + it) || numbers.contains(arr[i] - it)) continue
                flag = true
                break
            }
            if (!flag) result.add(it)
        }

        result.sort()
        println(
            buildString {
                appendLine(result.size)
                result.forEach { append("${it} ") }
            }.trim()
        )
    }

}

fun main() {
    BOJ30105().solve()
}