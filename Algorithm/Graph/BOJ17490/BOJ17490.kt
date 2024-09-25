package Algorithm.Graph.BOJ17490

import java.util.*

class BOJ17490 {

    private lateinit var parent: IntArray
    private var N = 0
    private var M = 0
    private var K = 0L
    private var groupCounter = hashSetOf<Int>()

    private data class Point(val v: Int, val dist: Int)

    private lateinit var pq: PriorityQueue<Point>

    fun run() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        K = st.nextToken().toLong()

        make()

        st = StringTokenizer(readLine(), " ")
        pq = PriorityQueue { o1, o2 -> o1.dist.compareTo(o2.dist) }
        for (i in 1..N) {
            pq.offer(Point(i, st.nextToken().toInt()))
        }

        var disconnect = false // 1과 N이 끊어졌는지 여부
        repeat(M) {
            st = StringTokenizer(readLine(), " ")
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            if (a == N && b == 1 || a == 1 && b == N) {
                disconnect = true
            } else {
                if (a > b) parent[a] = a else parent[b] = b
            }
        }

        init(disconnect)

        var cost = 0L
        if (groupCounter.size >= 2) {
            while (pq.isNotEmpty()) {
                val curr = pq.poll()
                if (groupCounter.remove(parent[curr.v])) {
                    cost += curr.dist
                    if (cost > K) break
                }
                if (groupCounter.isEmpty()) break
            }
        }
        println(if (cost > K) "NO" else "YES")
    }

    // 몇개의 그룹으로 나뉘는 지 체크
    private fun init(disconnect: Boolean) {

        var idx = N - 1
        if (parent[N] != N && !disconnect) { // N이 N-1과 연결되어 있으면서 1번과도 연결되어 있을 때만 작은 번호로 미리 연결시키기
            parent[N] = 1
            for (i in N - 1 downTo 2) {
                if (parent[i] == i) break
                parent[i] = 1
                idx = i
            }
        }

        for (i in 1..idx) {
            if (parent[i + 1] == i + 1) continue
            union(i, i + 1)
        }

        if (!disconnect) parent[N] = 1 // N번이 N-1과 연결되어 있지않지만 1번과는 연결되어 있다면 parent를 작은 번호로 갱신
        parent.forEachIndexed { i, it -> if (i != 0) groupCounter.add(it) }
    }

    private fun make() {
        parent = IntArray(N + 1) { it - 1 }
        parent[1] = 1
    }

    private fun find(a: Int): Int {
        if (parent[a] == a) return a
        return find(parent[a]).also { parent[a] = it }
    }

    private fun union(a: Int, b: Int) {
        var aRoot = find(a)
        var bRoot = find(b)
        if (aRoot == bRoot) return

        val tmp: Int
        if (aRoot > bRoot) {
            tmp = aRoot
            aRoot = bRoot
            bRoot = tmp
        }
        parent[bRoot] = aRoot
    }
}

fun main() {
    BOJ17490().run()
}