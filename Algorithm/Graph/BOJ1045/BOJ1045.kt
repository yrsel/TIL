package Algorithm.Graph.BOJ1045

class BOJ1045 {

    private lateinit var parents: IntArray

    fun run() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val map = Array(n) { BooleanArray(n) }

        for (i in 0..<n) {
            val str = readln()
            for (j in str.indices) {
                map[i][j] = str[j] == 'Y'
            }
        }

        val connects = mutableMapOf<Int, MutableList<Int>>()
        parents = IntArray(n) { it }
        var pick = 0
        loop@ for (i in 0..<n) {
            for (j in 0..<n) {
                if (map[i][j] && union(i, j)) {
                    pick++
                    val value = connects[i] ?: mutableListOf()
                    value.add(j)
                    connects[i] = value
                }
                if (pick == n - 1) break@loop
            }
        }

        if (pick != n - 1) {
            println(-1)
            return
        }

        if (pick != m) {
            loop@ for (i in 0..<n) {
                for (j in i + 1..<n) {
                    if (map[i][j]) {
                        val value = connects[i] ?: mutableListOf()
                        if (!value.contains(j) ) {
                            value.add(j)
                            pick++
                            connects[i] = value
                        }
                    }
                    if (pick == m) break@loop
                }
            }
        }

        if (pick != m) println(-1)
        else {
            val counter = IntArray(n)
            for (elements in connects) {
                elements.value.forEach {
                    counter[elements.key]++
                    counter[it]++
                }
            }
            val result = StringBuilder()
            counter.forEach { result.append("${it} ") }
            println(result.trimEnd().toString())
        }
    }

    private fun find(a: Int): Int {
        if (parents[a] == a) return a
        return find(parents[a]).also { parents[a] = it }
    }

    private fun union(a: Int, b: Int): Boolean {
        var aRoot = find(parents[a])
        var bRoot = find(parents[b])
        if (aRoot == bRoot) return false

        val tmp = aRoot
        if (aRoot > bRoot) {
            aRoot = bRoot
            bRoot = tmp
        }
        parents[bRoot] = aRoot
        return true
    }
}

fun main() {
    BOJ1045().run()
}