package Algorithm.String.BOJ5670

class BOJ5670 {

    private class Trie {
        val children: MutableMap<Char, Trie> = mutableMapOf()
        var isLast: Boolean = false

        fun insert(word: String) {
            var node = this

            word.forEachIndexed { index, c ->

                node.children.putIfAbsent(c, Trie())
                node = node.children[c]!!

                if (index == word.length - 1) node.isLast = true
            }
        }

        fun calculate(word: String): Int {
            var node = this
            var cnt = 0
            word.forEachIndexed { index, c ->
                if (index == 0) cnt++
                else if (node.children.size >= 2 || node.isLast) {
                    cnt++
                }
                node = node.children[c]!!
            }
            return cnt
        }
    }

    private lateinit var trie: Trie
    private lateinit var words: ArrayDeque<String>
    private var total = 0

    fun run() = with(System.`in`.bufferedReader()) {

        val result = StringBuilder()

        var input: String?
        while ((readLine().also { input = it }) != null && input!!.isNotBlank()) {
            trie = Trie()
            total = 0
            words = ArrayDeque()
            repeat(input!!.toInt()) {
                words.addLast(readLine())
                trie.insert(words.last())
            }

            while (words.isNotEmpty()) {
                total += trie.calculate(words.removeFirst())
            }

            result.appendLine(String.format("%.2f", total / input!!.toFloat()).trim())
        }

        println(result.toString().trim())
    }
}

fun main() {
    BOJ5670().run()
}