package Algorithm.Graph.BOJ1539

import java.util.TreeSet

class BOJ1539 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val levels = IntArray(N) { 1 }
        val sorted = TreeSet<Int>()
        var result = 0L
        var root = -1
        repeat(N) {
            val number = readLine().toInt()
            if (!sorted.isEmpty()) {
                val closedHighNumber = sorted.higher(number) ?: root
                val closedLowerNumber = sorted.lower(number) ?: root
                levels[number] = Math.max(levels[closedHighNumber], levels[closedLowerNumber]) + 1
            } else {
                root = number
            }
            sorted.add(number)
            result += levels[number]
        }
        println(result)
    }
}

fun main() {
    BOJ1539().solve()
}