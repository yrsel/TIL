package Algorithm.Implementation.BOJ17072

import java.io.StreamTokenizer

class BOJ17072 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        val N = input()
        val M = input()
        println(buildString {
            repeat(N) {
                repeat(M) {
                    append(toAsciiArt(toIntensity(input(), input(), input())))
                }
                appendLine()
            }
        }.trimEnd())
    }

    private fun toAsciiArt(intensity: Int): Char {
        return when (intensity) {
            in 0..<510_000 -> '#'
            in 510_000..<1_020_000 -> 'o'
            in 1_020_000..<1_530_000 -> '+'
            in 1_530_000..<2_040_000 -> '-'
            else -> '.'
        }
    }

    private fun toIntensity(r: Int, g: Int, b: Int): Int {
        return 2126 * r + 7152 * g + 722 * b
    }
}

fun main() {
    BOJ17072().solve()
}