package Algorithm.String.BOJ7432

import java.util.*

class BOJ7432 {

    private data class Node(
        val name: String = "",
        val children: PriorityQueue<Node> = PriorityQueue { o1, o2 -> o1.name.compareTo(o2.name) }
    )

    private val result = StringBuilder()

    fun run() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val roots = mutableListOf<Node>()

        repeat(N) {
            val paths = readLine().split("\\")
            var cur = Node()
            for (i in paths.indices) {
                if (i == 0) {
                    if (roots.none { it.name == paths[0] }) {
                        roots.add(Node(paths[0]))
                    }
                    cur = roots.first { it.name == paths[0] }
                } else {
                    if (cur.children.none { it.name == paths[i] }) {
                        cur.children.add(Node(paths[i]))
                    }
                    cur = cur.children.first { it.name == paths[i] }
                }
            }
        }

        roots.sortedWith { o1, o2 -> o1.name.compareTo(o2.name) }.forEach {
            search(it, "")
        }
        println(result.toString())
    }

    private fun search(cur: Node, depth: String) {
        result.appendLine("$depth${cur.name}")
        while (cur.children.isNotEmpty()) {
            search(cur.children.poll(), "$depth ")
        }
    }
}

fun main() {
    BOJ7432().run()
}