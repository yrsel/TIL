package Algorithm.Graph.BOJ1765

import java.util.*

class BOJ1765 {
    private var n = 0
    private lateinit var status: IntArray

    fun solve() {
        n = readln().toInt()
        val m = readln().toInt()
        val friendsStatus: Array<MutableList<Int>> = Array(n + 1) { mutableListOf() }
        val enemiesStatus: Array<MutableList<Int>> = Array(n + 1) { mutableListOf() }
        status = make(n + 1)
        var st: StringTokenizer
        repeat(m) {
            st = StringTokenizer(readln(), " ")
            val status = st.nextToken()[0]
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            when (status) {
                'F' -> {
                    friendsStatus[a].add(b)
                    friendsStatus[b].add(a)
                }

                'E' -> {
                    enemiesStatus[a].add(b)
                    enemiesStatus[b].add(a)
                }
            }
        }
        for (i in 1..n) friendsStatus[i].forEach { union(i, it) }

        for (i in 1..n) {
            val candidates = mutableSetOf<Int>()
            enemiesStatus[i].forEach { candidates.addAll(enemiesStatus[it]) }
            candidates.forEach { union(i, it) }
        }

        for (i in 1..n) union(i, status[i])

        println(status.distinct().size - 1)
    }

    private fun make(n: Int): IntArray {
        return IntArray(n) { it }
    }

    private fun find(a: Int): Int {
        return if (status[a] == a) a
        else find(status[a]).also { status[a] = it }
    }

    private fun union(a: Int, b: Int): Boolean {
        var aRoot = find(a)
        var bRoot = find(b)
        if (aRoot == bRoot) return false

        val tmp: Int
        if (bRoot < aRoot) {
            tmp = bRoot
            bRoot = aRoot
            aRoot = tmp
        }

        status[bRoot] = aRoot
        return true
    }

}

fun main() {
    BOJ1765().solve()
}
