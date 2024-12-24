package Algorithm.Greedy.BOJ1213

import java.util.*

class BOJ1213 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val map = TreeMap<Char, Int>()
        val origin = readLine().toCharArray()
        origin.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        val size = origin.size
        val oddCount = map.values.count { it % 2 == 1 }
        if (size % 2 == 0 && oddCount != 0 || size % 2 == 1 && oddCount != 1) {
            println(IMPOSSIBLE)
            return
        }

        val result = CharArray(size)
        var idx = 0

        map.forEach { (c, value) ->
            if (value % 2 == 1) result[size / 2] = c
            repeat(value / 2) {
                result[idx] = c
                result[size - 1 - idx++] = c
            }
        }
        println(result.joinToString("") { it.toString() })
    }

    companion object {
        private const val IMPOSSIBLE = "I'm Sorry Hansoo"
    }
}

fun main() {
    BOJ1213().solve()
}