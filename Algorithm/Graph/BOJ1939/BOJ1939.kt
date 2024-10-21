package Algorithm.Graph.BOJ1939

import java.io.StreamTokenizer
import java.util.*

class BOJ1939 {

    private data class Point(val from: Int, val to: Int, val weight: Int)

    private lateinit var parents: IntArray

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        val N = input()
        val pq = PriorityQueue<Point> { o1, o2 -> o2.weight.compareTo(o1.weight) }
        repeat(input()) {
            val from = input()
            val to = input()
            val w = input()
            pq.offer(Point(from, to, w))
        }

        parents = IntArray(N + 1) { it }
        val start = input()
        val des = input()
        var result = -1
        while (pq.isNotEmpty()) {
            val (from, to, w) = pq.poll()
            union(from, to)
            union(des, parents[des]) // 현재 나의 집합과 나의 루트 집합이 일치한지 체크
            if (parents[start] == parents[des]) {
                result = w
                break
            }
        }
        println(result)
    }

    private fun find(a: Int): Int {
        if (parents[a] == a) return a
        return find(parents[a]).also { parents[a] = it }
    }

    private fun union(a: Int, b: Int) {
        var aRoot = find(a)
        var bRoot = find(b)
        if (aRoot == bRoot) return
        val tmp = aRoot
        if (aRoot > bRoot) {
            aRoot = bRoot
            bRoot = tmp
        }
        parents[bRoot] = aRoot
        union(bRoot, b) // 나의 루트 집합이 변경되었으므로 나의 루트도 갱신해주기
    }
}

fun main() {
    BOJ1939().run()
}