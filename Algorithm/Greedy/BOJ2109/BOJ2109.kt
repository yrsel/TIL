package Algorithm.Greedy.BOJ2109

import java.io.StreamTokenizer
import java.util.*

class BOJ2109 {
    private data class Condition(val cost: Int, val dDay: Int)

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        val pq = PriorityQueue<Condition> { o1, o2 ->
            if (o1.cost == o2.cost) o2.dDay.compareTo(o1.dDay) else o2.cost.compareTo(o1.cost)
        }
        repeat(input()) {
            pq.offer(Condition(input(), input()))
        }
        val dayCost = IntArray(10_001)
        while (!pq.isEmpty()) {
            val (cost, _day) = pq.poll()
            var insertDay = _day
            while (insertDay != 0 && dayCost[insertDay] != 0) insertDay--
            dayCost[insertDay] = cost
        }
        println(dayCost.sum() - dayCost[0])
    }
}

fun main() {
    BOJ2109().run()
}