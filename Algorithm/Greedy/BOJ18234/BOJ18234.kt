package Algorithm.Greedy.BOJ18234

import java.io.StreamTokenizer
import java.util.*

class BOJ18234 {

    private data class Carrot(val taste: Int, val addThing: Int) : Comparable<Carrot> {
        override fun compareTo(other: Carrot): Int {
            return if (this.addThing == other.addThing) other.taste.compareTo(this.taste)
            else other.addThing.compareTo(this.addThing)
        }
    }

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }

        val N = input()
        var T = input()
        val pq = PriorityQueue<Carrot>()
        repeat(N) {
            pq.add(Carrot(input(), input()))
        }

        var sum = 0L
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            sum += curr.addThing.toLong() * (T - 1) + curr.taste
            --T
        }
        println(sum)
    }
}

fun main() {
    BOJ18234().solve()
}

