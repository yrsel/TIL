package Algorithm.Graph.BOJ1068

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StreamTokenizer

class BOJ1068 {
    private lateinit var parents: IntArray
    private lateinit var tree: Array<MutableList<Int>>
    private var root = -1
    private var removeNode = -1

    fun run() = StreamTokenizer(BufferedReader(InputStreamReader(System.`in`))).run {
        val toInt = { nextToken(); nval.toInt() }
        parents = IntArray(toInt())
        tree = Array(parents.size) { mutableListOf() }

        repeat(parents.size) { i ->
            parents[i] = toInt()
            if (parents[i] == -1) root = i
            else tree[parents[i]].add(i)
        }

        removeNode = toInt()

        println(countRemainingLeaves())
    }

    private fun countRemainingLeaves(): Int {
        if (root == removeNode) return 0

        val q = ArrayDeque<Int>()
        q.add(root)
        var leafCount = 0

        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            var hasChild = false

            for (child in tree[curr]) {
                if (child == removeNode) continue
                q.add(child)
                hasChild = true
            }

            if (!hasChild) leafCount++
        }

        return leafCount
    }
}


fun main() {
    BOJ1068().run()
}