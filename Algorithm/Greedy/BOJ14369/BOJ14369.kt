package Algorithm.Greedy.BOJ14369

import java.io.StreamTokenizer

class BOJ14369 {

    private val uniqueNumbers = mutableMapOf(0 to "ZERO", 2 to "TWO", 4 to "FOUR", 6 to "SIX", 8 to "EIGHT")
    private val uniqueAlphabetMapper = arrayOf('z' to 0, 'w' to 2, 'u' to 4, 'x' to 6, 'g' to 8)
    private val remainAlphabets = arrayOf(1 to "ONE", 3 to "THREE", 5 to "FIVE", 7 to "SEVEN", 9 to "NINE")

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); sval }
        nextToken();
        val T = nval.toInt()

        val sb = StringBuilder()
        for (tc in 1..T) {
            val (remainStr, number) = separateOnlyOneAlphabet(input())
            sb.append("Case #${tc}: ${findResult(remainStr, number)}\n")
        }

        println(sb.toString())
    }

    private fun separateOnlyOneAlphabet(_input: String): Pair<String, String> {
        var input = _input
        var number = ""

        for (i in uniqueAlphabetMapper.indices) {
            val numberStr = uniqueNumbers[uniqueAlphabetMapper[i].second]!!
            while (isContain(input, numberStr)) {
                input = replace(input, numberStr)
                number += uniqueAlphabetMapper[i].second
            }
        }
        return Pair(input, number)
    }

    private fun findResult(_remainStr: String, _number: String): String {
        if (_remainStr.isEmpty()) return _number

        var idx = 0
        var remainStr = _remainStr
        var number = _number
        while (idx != 5) {
            if (isContain(remainStr, remainAlphabets[idx].second)) {
                remainStr = replace(remainStr, remainAlphabets[idx].second)
                number += remainAlphabets[idx].first
                if (remainStr.isEmpty()) break
                continue
            }
            idx++
        }

        return number.toCharArray().sortedArray().joinToString("")
    }

    private fun isContain(input: String, number: String): Boolean {
        number.forEach {
            if (!input.contains(it)) return false
        }
        return true
    }

    private fun replace(_input: String, number: String): String {
        var input = _input
        number.forEach { input = input.replaceFirst(it.toString(), "") }
        return input
    }
}

fun main() {
    BOJ14369().run()
}