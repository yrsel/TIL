package Algorithm.Implementation.BOJ23291

import java.io.StreamTokenizer
import kotlin.math.abs

class BOJ23291 {

    private lateinit var map: MutableList<MutableList<Int>>
    private var n = 0
    private var k = 0

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        n = input(); k = input()
        map = mutableListOf()
        val inputData = mutableListOf<Int>()
        repeat(n) { inputData.add(input()) }
        map.add(inputData)

        var result = 0
        while (true) {
            if (isSatisfied()) break // 8번 과정
            ++result

            increaseBySearchMin() // 1
            upperByLeft() // 2
            stackLoop(map.size, map[0].size) // 3
            searchAndCalculate() // 4
            setOneLine() // 5
            divide() // 6
            searchAndCalculate() // 7
            setOneLine() // 다시 일렬로
        }

        println(result)
    }

    private fun increaseBySearchMin() {
        val min = map[0].min()
        for (i in 0..<map[0].size) {
            if (map[0][i] == min) map[0][i]++
        }
    }

    private fun upperByLeft() {
        val leftElement = map[0].removeAt(0)
        val copy = map[0].toList()
        map[0] = mutableListOf(leftElement)
        map.add(copy.toMutableList())
    }

    private fun stackLoop(h: Int, w: Int) {
        if (h > map[h - 1].size - w) return

        val turned = mutableListOf<MutableList<Int>>()
        val bottom = map[h - 1].subList(w, map[h - 1].size)

        for (c in 0..<w) {
            val elements = mutableListOf<Int>()
            for (r in h - 1 downTo 0) {
                elements.add(map[r][c])
            }
            turned.add(elements)
        }
        turned.add(bottom)

        map = turned.toMutableList()

        stackLoop(map.size, map[0].size)
    }

    private fun searchAndCalculate() {
        val q = ArrayDeque<Pair<Int, Int>>()
        val memo = mutableMapOf<Int, Int>()
        val maxW = map[map.size - 1].size
        q.addLast(Pair(0, 0))
        val visited = mutableSetOf<Int>()
        while (q.isNotEmpty()) {
            val (r, c) = q.removeFirst()
            if (!visited.add(r * maxW + c)) continue
            if (map[r].size != c + 1) {
                calculateAndMemo(r, c, r, c + 1, memo, maxW)
                q.addFirst(Pair(r, c + 1))
            }
            if (r + 1 != map.size) {
                calculateAndMemo(r, c, r + 1, c, memo, maxW)
                q.addLast(Pair(r + 1, c))
            }
        }

        for (elements in memo) {
            map[elements.key / maxW][elements.key % maxW] += elements.value
        }
    }

    private fun calculateAndMemo(r1: Int, c1: Int, r2: Int, c2: Int, memo: MutableMap<Int, Int>, maxW: Int) {
        val diff = map[r1][c1] - map[r2][c2]
        val point = abs(diff) / 5
        val firstIdx = r1 * maxW + c1
        val secondIdx = r2 * maxW + c2
        if (diff > 0) {
            memo[firstIdx] = memo.getOrDefault(firstIdx, 0) - point
            memo[secondIdx] = memo.getOrDefault(secondIdx, 0) + point
        } else {
            memo[firstIdx] = memo.getOrDefault(firstIdx, 0) + point
            memo[secondIdx] = memo.getOrDefault(secondIdx, 0) - point
        }
    }

    private fun setOneLine() {
        val oneLine = mutableListOf<MutableList<Int>>()
        val maxHIdx = map.size - 1
        val maxW = map[maxHIdx].size

        val oneLineup = mutableListOf<Int>()
        for (c in 0..<maxW) {
            for (r in maxHIdx downTo 0) {
                if (map[r].size <= c) break
                oneLineup.add(map[r][c])
            }
        }
        oneLine.add(oneLineup)
        map = oneLine
    }

    private fun divide() {
        val half = map[0].size / 2
        val halfAndHalf = half / 2

        val top = map[0].subList(half, half + halfAndHalf).reversed().toMutableList()
        val second = map[0].subList(half - halfAndHalf, half).toMutableList()
        val third = map[0].subList(0, half - halfAndHalf).reversed().toMutableList()
        val bottom = map[0].subList(half + halfAndHalf, map[0].size).toMutableList()

        map = mutableListOf(top, second, third, bottom)
    }

    private fun isSatisfied(): Boolean {
        return map[0].max() - map[0].min() <= k
    }
}

fun main() {
    BOJ23291().run()
}
