package Algorithm.String.BOJ5446

import java.io.BufferedReader

private const val SIZE = 63

private fun Char.ctoi(): Int {
    return when (this) {
        in '0'..'9' -> this - '0'
        in 'a'..'z' -> this - 'a' + 10
        in 'A'..'Z' -> this - 'A' + 36
        else -> 62
    }
}

class BOJ5446 {

    private data class Trie(
        val status: Array<Trie?> = Array(SIZE) { null },
        var isEnd: Boolean = false,
        var availRemove: Boolean = true
    ) {

        fun insert(fileName: String) {
            var curr = this
            fileName.forEach {
                val idx = it.ctoi()
                curr.status[idx] ?: run { curr.status[idx] = Trie() }
                curr = curr.status[idx]!!
            }
            curr.isEnd = true
        }

        fun markUnAvailRemove(fileName: String) {
            var curr = this
            fileName.forEach {
                val idx = it.ctoi()
                val node = curr.status[idx] ?: return
                node.availRemove = false
                curr = node
            }
        }

        fun availOneTimeDelete(): Boolean {
            for (element in this.status) {
                val cur = element ?: continue
                if (!cur.availRemove) return false
            }
            return true
        }

        fun countByDelete(): Int {
            var count = 0
            for (i in status.indices) {
                val node = status[i] ?: continue

                if (node.availRemove) {
                    count++
                } else {
                    if (node.isEnd) count++
                    count += node.countByDelete()
                }
            }
            return count
        }
    }

    fun solve() = with(System.`in`.bufferedReader()) {
        println(buildString {
            val t = nval()
            repeat(t) {
                val trie = Trie()
                val removeFileCount = nval()
                repeat(removeFileCount) {
                    trie.insert(readLine())
                }
                val cantRemoveFileCount = nval()
                repeat(cantRemoveFileCount) {
                    trie.markUnAvailRemove(readLine())
                }

                if (trie.availOneTimeDelete()) {
                    appendLine(1)
                } else {
                    appendLine(trie.countByDelete())
                }
            }
        })
    }

    private fun BufferedReader.nval(): Int {
        return this.readLine().toInt()
    }
}

fun main() {
    BOJ5446().solve()
}