package Algorithm.String.BOJ14725

import java.util.*

class BOJ14725 {
    private data class Node(val children: TreeMap<String, Node> = TreeMap())

    private val result = StringBuilder()

    fun run() {
        val root = TreeMap<String, Node>()
        var st: StringTokenizer
        repeat(readln().toInt()) {
            st = StringTokenizer(readln(), " ")
            val n = st.nextToken().toInt()
            var cur = root
            repeat(n) {
                val feedInfo = st.nextToken()
                cur[feedInfo] = cur.getOrDefault(feedInfo, Node())
                cur = cur[feedInfo]!!.children
            }
        }

        explore(root, "")

        println(result.toString())
    }

    private fun explore(map: TreeMap<String, Node>, depthInfo: String) {
        if (map.isEmpty()) return
        for (cur in map) {
            result.appendLine("$depthInfo${cur.key}")
            explore(cur.value.children, "$depthInfo--")
        }
    }
}

fun main() {
    BOJ14725().run()
}