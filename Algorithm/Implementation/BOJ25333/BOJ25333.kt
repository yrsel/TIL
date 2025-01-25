package Algorithm.Implementation.BOJ25333

import java.io.StreamTokenizer

class BOJ25333 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken();nval.toInt() }
        val T = toInt()
        println(buildString {
            repeat(T) {
                val right = toInt()
                val left = toInt()
                val maxPosition = toInt()
                val gcd = if (right < left) gcd(left, right)
                else gcd(right, left)
                appendLine(maxPosition / gcd)
            }
        }.trimEnd())
    }

    private fun gcd(a: Int, b: Int): Int {
        if (a % b == 0) return b
        return gcd(b, a % b)
    }
}

fun main() {
    BOJ25333().solve()
}