package Algorithm.String.BOJ14426

class BOJ14426 {

    fun run() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val arr = Array(n) { readln() }

        val trie = setTrie(arr)

        var cnt = 0
        repeat(m) {
            val str = readln()
            if (isSatisfied(trie, str)) ++cnt
        }
        println(cnt)
    }

    private fun setTrie(arr: Array<String>): Array<IntArray> {
        val root = 1
        var unused = 2
        val size = 500 * 10_000 + 1
        val nxt = Array(26) { IntArray(size) { -1 } }
        arr.forEach {
            var cur = root
            for (c in it) {
                val idx = ctoi(c)
                if (nxt[idx][cur] == -1) nxt[idx][cur] = unused++
                cur = nxt[idx][cur]
            }
        }
        return nxt
    }

    private fun isSatisfied(trie: Array<IntArray>, str: String): Boolean {
        var idx = 1
        str.forEach {
            val cur = ctoi(it)
            if (trie[cur][idx] == -1) return false
            idx = trie[cur][idx]
        }
        return true
    }

    private fun ctoi(c: Char): Int {
        return c - 'a'
    }
}

fun main() {
    BOJ14426().run()
}