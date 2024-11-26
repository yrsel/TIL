package Algorithm.BruteForce.BOJ15970

import java.io.StreamTokenizer

class BOJ15970 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val mapper = hashMapOf<Int, MutableList<Int>>()

        repeat(toInt()) {
            val pos = toInt()
            val color = toInt()
            val value = mapper[color] ?: mutableListOf()
            value.add(pos)
            mapper[color] = value
        }

        var sum = 0
        mapper.forEach { (_, value) ->
            sum += calculate(value)
        }
        println(sum)
    }

    private fun calculate(pos: MutableList<Int>): Int {
        pos.sort()
        var sum = 0
        for ((idx, value) in pos.withIndex()) {
            when (idx) {
                0 -> {
                    sum += pos[idx + 1] - value
                }

                pos.size - 1 -> {
                    sum += value - pos[idx - 1]
                }

                else -> {
                    val min = Math.min(
                        pos[idx + 1] - value,
                        value - pos[idx - 1]
                    )
                    sum += min
                }
            }
        }
        return sum
    }
}

fun main() {
    BOJ15970().solve()
}