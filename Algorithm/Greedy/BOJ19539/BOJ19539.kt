package Algorithm.Greedy.BOJ19539

import java.io.StreamTokenizer

class BOJ19539 {
    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        var oneTotalCount = 0
        var twoTotalCount = 0
        repeat(input()) {
            val num = input()
            oneTotalCount += num % 2
            twoTotalCount += num / 2
        }

        if (oneTotalCount > twoTotalCount) {
            println("NO")
        } else {
            twoTotalCount -= oneTotalCount
            if (twoTotalCount % 3 == 0) println("YES")
            else println("NO")
        }
    }
}

fun main() {
    BOJ19539().run()
}