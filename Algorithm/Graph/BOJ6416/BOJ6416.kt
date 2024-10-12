package Algorithm.Graph.BOJ6416

import java.io.StreamTokenizer

class BOJ6416 {

    private lateinit var rootFinder: MutableMap<Int, Boolean>
    private lateinit var connection: MutableMap<Int, MutableList<Int>>
    private lateinit var visited: HashSet<Int>
    private var flag = true

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }
        val result = StringBuilder()
        var tc = 1

        loop@ while (true) {
            flag = true
            rootFinder = mutableMapOf()
            connection = mutableMapOf()
            visited = hashSetOf()

            while (true) {
                val from = input()
                val to = input()
                if (from == -1 && to == -1) break@loop
                if (from == 0 && to == 0) break

                val value = connection[from] ?: mutableListOf()
                if (value.contains(to)) { // 중복된 입력 주어졌을 경우 (문제에 명확히 안나와 있어서 예외 체크 진행)
                    flag = false
                }
                value.add(to)

                connection[from] = value

                rootFinder[from] = rootFinder.putIfAbsent(from, true) ?: true
                rootFinder[to] = false
            }

            if (rootFinder.keys.filter { rootFinder[it]!! }.size != 1) { // 루트가 여러개 존재
                flag = false
            } else {
                val root = rootFinder.keys.first { rootFinder[it]!! }
                visited.add(root)
                val q = ArrayDeque<Int>()
                q.addLast(root)
                while (q.isNotEmpty()) {
                    val curr = q.removeFirst()
                    connection[curr]?.isEmpty() ?: continue

                    for (child in connection[curr]!!) {
                        if (visited.add(child)) {
                            q.addLast(child)
                        } else {
                            flag = false
                        }
                    }
                    if (!flag) break
                }
            }

            var sum = 0 // 간선의 개수
            connection.keys.forEach { sum += connection[it]!!.size }
            if (visited.size != sum + 1) flag = false // 간선의 개수 +1 과 노드의 개수가 일치하는지 판단
            if (connection.isEmpty()) flag = true // 노드가 존재하지 않는 경우

            if (flag) result.appendLine(String.format("Case $tc is a tree."))
            else result.appendLine(String.format("Case $tc is not a tree."))
            tc++
        }
        println(result.toString())
    }
}

fun main() {
    BOJ6416().run()
}