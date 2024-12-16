package Algorithm.String.BOJ27396

import java.util.StringTokenizer

class BOJ27396 {
    fun solve() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val mapper = hashMapOf<Char, Char>()
        val origin = st.nextToken()
        val alphabets = origin.toCharArray()
        for (i in 'A'..'Z') {
            mapper[i] = i
        }
        for (i in 'a'..'z') {
            mapper[i] = i
        }

        val T = st.nextToken().toInt()
        println(buildString {
            for (i in 0..<T) {
                st = StringTokenizer(readLine(), " ")
                val command = st.nextToken().toInt()
                when (command) {
                    1 -> {
                        val from = st.nextToken()[0]
                        val to = st.nextToken()[0]
                        val elements = mapper.filterValues { it == from }
                        elements.forEach { mapper[it.key] = to }
                    }

                    else -> {
                        alphabets.forEach { append(mapper[it]) }
                        appendLine()
                    }
                }
            }
        })
    }
}

fun main() {
    BOJ27396().solve()
}