package Algorithm.Sort.BOJ23757

import java.io.StreamTokenizer
import java.util.*

class BOJ23757 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val n = toInt()
        val m = toInt()
        val pq = PriorityQueue<Int> { o1, o2 -> o2.compareTo(o1) }
        repeat(n) { pq.add(toInt()) }
        var flag = false
        repeat(m) {
            val num = toInt()
            if (!flag) {
                if (pq.isEmpty()) {
                    flag = true
                } else {
                    val remain = pq.poll() - num
                    if (remain < 0) flag = true
                    else if (remain != 0) {
                        pq.offer(remain)
                    }
                }
            }
        }
        println(if (flag) 0 else 1)
    }
}

fun main() {
    BOJ23757().solve()
}