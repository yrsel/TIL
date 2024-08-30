package Algorithm.Graph.BOJ21939

import java.util.*

class BOJ21939 {

    private data class Problem(val number: Int, val level: Int)

    private val existed = IntArray(100_001)
    private lateinit var ascPq: PriorityQueue<Problem>
    private lateinit var descPq: PriorityQueue<Problem>
    private lateinit var st: StringTokenizer

    fun run() {
        val result = StringBuilder()
        ascPq = PriorityQueue<Problem> { o1, o2 ->
            if (o1.level == o2.level) o1.number.compareTo(o2.number) else o1.level.compareTo(o2.level)
        }
        descPq = PriorityQueue<Problem> { o1, o2 ->
            if (o1.level == o2.level) o2.number.compareTo(o1.number) else o2.level.compareTo(o1.level)
        }
        repeat(readln().toInt()) {
            st = StringTokenizer(readln(), " ")
            val problem = Problem(st.nextToken().toInt(), st.nextToken().toInt())
            ascPq.offer(problem)
            descPq.offer(problem)
            existed[problem.number] = problem.level
        }

        repeat(readln().toInt()) {
            val (command, number) = readln().split(" ", limit = 2)
            handler(result, command, number)
        }
        println(result.toString())
    }

    private fun handler(result: StringBuilder, command: String, number: String) {
        when (command) {
            "recommend" -> {
                val selectPqNumber = number.toInt()
                if (selectPqNumber == 1) recommender(result, descPq)
                else recommender(result, ascPq)
            }

            "add" -> {
                val (P, L) = number.split(" ").map { it.toInt() }
                val problem = Problem(P, L)
                ascPq.offer(problem)
                descPq.offer(problem)
                existed[P] = L
            }

            else -> { // solved
                val problemNumber = number.toInt()
                existed[problemNumber] = 0
            }
        }

    }

    private fun recommender(result: StringBuilder, pq: PriorityQueue<Problem>) {
        while (pq.isNotEmpty()) {
            if (existed[pq.peek().number] != pq.peek().level) {
                pq.poll()
            } else {
                result.appendLine(pq.peek().number)
                break
            }
        }
    }
}

fun main() {
    BOJ21939().run()
}