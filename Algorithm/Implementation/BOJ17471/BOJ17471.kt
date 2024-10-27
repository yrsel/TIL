package Algorithm.Implementation.BOJ17471

import java.io.StreamTokenizer
import kotlin.math.abs

class BOJ17471 {
    private var n = 0
    private lateinit var populations: IntArray
    private lateinit var connections: Array<MutableList<Int>>
    private var result = 0
    private var totalPopulations = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        n = input()
        populations = IntArray(n) { input() }
        connections = Array(n) { mutableListOf() }
        repeat(n) { idx ->
            val edgesCnt = input()
            repeat(edgesCnt) {
                connections[idx].add(input() - 1)
            }
        }
        totalPopulations = populations.sum()
        result = totalPopulations
        var visited = BooleanArray(n)
        val groupCnt = getGroupCount(visited, false)
        when (groupCnt) {
            1 -> {
                visited = BooleanArray(n)
                search(0, visited)
                println(result)
            }

            2 -> {
                visited = BooleanArray(n)
                bfs(visited, 0, false)
                println(groupDiff(visited))
            }

            else -> println(-1)
        }

    }

    private fun search(idx: Int, visited: BooleanArray) {
        if (idx == n) {
            val curVisited = visited.copyOf()
            if (getGroupCount(curVisited, true) != 1) return // true 그룹이 1개여야 한다
            if (getGroupCount(curVisited, false) == 1) { // false 그룹이 1개여야 한다
                result = Math.min(result, groupDiff(curVisited))
            }
            return
        }

        visited[idx] = true
        search(idx + 1, visited)
        visited[idx] = false
        search(idx + 1, visited)
    }

    private fun getGroupCount(originVisited: BooleanArray, flag: Boolean): Int {
        val copyVisited = originVisited.copyOf()
        var cnt = 0
        for (i in 0..<n) {
            if (copyVisited[i] == flag) {
                bfs(copyVisited, i, flag)
                cnt++
            }
            if (cnt == 3) break
        }
        return cnt
    }

    private fun bfs(visited: BooleanArray, v: Int, flag: Boolean) {
        visited[v] = true
        val q = ArrayDeque<Int>()
        q.addLast(v)
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (next in connections[curr]) {
                if (visited[next] != flag) continue
                visited[next] = !flag
                q.addLast(next)
            }
        }
    }

    private fun groupDiff(visited: BooleanArray): Int {
        val groupA = populations.filterIndexed { idx, _ -> visited[idx] }.sum()
        val groupB = totalPopulations - groupA
        return abs(groupA - groupB)
    }
}

fun main() {
    BOJ17471().solve()
}