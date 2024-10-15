package Algorithm.Graph.PG도넛과막대그래프

class Solution {
    private lateinit var connects: MutableMap<Int, MutableList<Int>>

    fun solution(edges: Array<IntArray>): IntArray {
        connects = mutableMapOf()
        val counter = IntArray(1_000_001)
        var root = -1
        edges.forEach {
            counter[it[0]]++
            counter[it[1]]--

            val value = connects[it[0]] ?: mutableListOf()
            value.add(it[1])
            connects[it[0]] = value
        }
        for (i in 0..1_000_000) {
            if (counter[i] >= 2) {
                root = i
                break
            }
        }

        val childNode = connects[root]!!
        val result = IntArray(4)
        result[0] = root

        childNode.forEach { v ->
            result[findShape(v)]++
        }
        return result
    }

    private fun findShape(v: Int): Int {
        val q = ArrayDeque<Int>()
        val dummy = 1_000_001L
        val nodeCounter = hashSetOf(v)
        val edgeCounter = hashSetOf<Long>()
        q.addLast(v)
        var flag = false
        while (q.isNotEmpty()) {
            val from = q.removeFirst()
            if (connects[from] == null) continue
            for (to in connects[from]!!) {
                if (to == v) flag = true
                if (edgeCounter.add(from * dummy + to)) {
                    q.addLast(to)
                    nodeCounter.add(to)
                }
            }
        }

        if (!flag) return 2

        return if (nodeCounter.size == edgeCounter.size) 1
        else 3
    }

}

fun main() {
    val edges = arrayOf(intArrayOf(2, 3), intArrayOf(4, 3), intArrayOf(1, 1), intArrayOf(2, 1))
//    val edges = arrayOf(
//        intArrayOf(4, 11), intArrayOf(1, 12), intArrayOf(8, 3), intArrayOf(12, 7),
//        intArrayOf(4, 2), intArrayOf(7, 11), intArrayOf(4, 8), intArrayOf(9, 6),
//        intArrayOf(10, 11), intArrayOf(6, 10), intArrayOf(3, 5), intArrayOf(11, 1),
//        intArrayOf(5, 3), intArrayOf(11, 9), intArrayOf(3, 8)
//    )
    Solution().solution(edges)
}