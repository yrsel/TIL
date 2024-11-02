package Algorithm.String.BOJ5446

import java.io.StreamTokenizer

class BOJ5446 {

    private data class Node(
        val alphabet: Char,
        val children: MutableList<Node> = mutableListOf(),
        var isEnd: Boolean = false,
        var availRemove: Boolean = true
    ) {

        fun isExist(c: Char): Boolean {
            return this.children.any { it.alphabet == c }
        }

        fun getOrAddChild(c: Char): Node {
            return this.children.firstOrNull { it.alphabet == c }
                ?: Node(c).also { this.children.add(it) }
        }

        fun setEnded() {
            this.isEnd = true
        }
    }

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val numInput = { nextToken(); nval.toInt() }
        val strInput = { nextToken(); sval }
        val sb = StringBuilder()
        val t = numInput()

        repeat(t) {
            val trie = hashMapOf<Char, Node>() // 루트 노드

            val removeFileCount = numInput()

            repeat(removeFileCount) {
                val fileName = strInput()
                var root = trie.putIfAbsent(fileName[0], Node(fileName[0])) ?: trie[fileName[0]]!!
                fileName.forEachIndexed { idx, c ->
                    if (idx != 0) {
                        root = root.getOrAddChild(c)
                    }
                }
                root.setEnded()
            }

            val cantRemoveFileCount = numInput()
            for (i in 0..<cantRemoveFileCount) {
                val fileName = strInput()
                var curr = trie[fileName[0]] ?: continue
                curr.availRemove = false
                fileName.forEachIndexed { index, c ->
                    if (index != 0) {
                        if (curr.isExist(c)) {
                            curr = curr.getOrAddChild(c)
                            curr.availRemove = false
                        } else {
                            return@forEachIndexed
                        }
                    }
                }
            }

            val flag = trie.values.all { it.availRemove }
            var cnt = 0
            if (flag) {
                cnt = 1
            } else {
                for (root in trie) {
                    val q = ArrayDeque<Node>()
                    q.addLast(root.value)
                    while (q.isNotEmpty()) {
                        val curr = q.removeFirst()

                        if (curr.availRemove) {
                            cnt++
                        } else {
                            if (curr.isEnd) cnt++
                            if (curr.children.isNotEmpty()) q.addAll(curr.children)
                        }

                    }
                }
            }
            sb.appendLine(cnt)
        }

        println(sb.toString())
    }

}

fun main() {
    BOJ5446().solve()
}