package Algorithm.Graph.BOJ2263

class BOJ2263 {
    private val result = StringBuilder()
    private lateinit var inOrder: IntArray
    private lateinit var inOrderIdx: IntArray
    private lateinit var postOrder: IntArray
    fun run() {
        val n = readln().toInt()
        inOrder = readln().split(" ").map { it.toInt() }.toIntArray()
        postOrder = readln().split(" ").map { it.toInt() }.toIntArray()
        inOrderIdx = IntArray(n + 1)
        for (i in 0..<n) {
            inOrderIdx[inOrder[i]] = i
        }

        search(0, n - 1, 0, n - 1)
        println(result)
    }

    private fun search(inOrderLeft: Int, inOrderRight: Int, postOrderLeft: Int, postOrderRight: Int) {
        if (inOrderLeft <= inOrderRight && postOrderLeft <= postOrderRight) {
            val findNumber = postOrder[postOrderRight]
            result.append("$findNumber ")

            val inOrderFindNumberIdx = inOrderIdx[findNumber]
            val searchLength = inOrderFindNumberIdx - inOrderLeft
            search(
                inOrderLeft, inOrderFindNumberIdx - 1,
                postOrderLeft, postOrderLeft + searchLength - 1
            ) // left
            search(
                inOrderFindNumberIdx + 1, inOrderRight,
                postOrderLeft + searchLength, postOrderRight - 1
            ) // right
        }
    }
}

fun main() {
    BOJ2263().run()
}