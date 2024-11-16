package Algorithm.Graph.BOJ2042

import java.io.BufferedReader
import java.util.*
import kotlin.math.ceil
import kotlin.math.ln

class BOJ2042 {

    private class Tree(val n: Int, val arr: LongArray) {
        private val segmentTree: LongArray

        init {
            val level = ceil(ln(n.toDouble()) / ln(2.0)).toInt()
            val size = 1 shl (level + 1)
            segmentTree = LongArray(size)

            setTree(1, 1, n)
        }

        private fun setTree(node: Int, start: Int, end: Int): Long {
            if (start == end) {
                segmentTree[node] = arr[start]
                return segmentTree[node]
            }

            val mid = (start + end) / 2
            segmentTree[node] = setTree(node * 2, start, mid) + setTree(node * 2 + 1, mid + 1, end)
            return segmentTree[node]
        }

        fun select(node: Int = 1, start: Int = 1, end: Int = n, from: Int, to: Int): Long {
            if (from > end || start > to) return 0L

            if (from <= start && end <= to) {
                return segmentTree[node]
            }

            val mid = (start + end) / 2
            return select(node * 2, start, mid, from, to) + select(node * 2 + 1, mid + 1, end, from, to)
        }

        fun update(node: Int = 1, start: Int = 1, end: Int = n, idx: Int, diff: Long) {
            if (idx < start || end < idx) return

            segmentTree[node] += diff

            if (start != end) {
                val mid = (start + end) / 2
                update(node * 2, start, mid, idx, diff)
                update(node * 2 + 1, mid + 1, end, idx, diff)
            }
        }
    }

    fun solve() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val N = st.toInt()
        val loop = st.toInt() + st.toInt()
        val arr = LongArray(N + 1) {
            if (it != 0) toLong()
            else 0L
        }

        val tree = Tree(N, arr)

        println(buildString {
            repeat(loop) {
                st = StringTokenizer(readLine(), " ")
                val command = st.toInt()
                val from = st.toInt()
                when (command) {
                    1 -> {
                        val diff = st.toLong() - tree.arr[from]
                        tree.arr[from] += diff
                        tree.update(idx = from, diff = diff)
                    }

                    else -> appendLine(tree.select(from = from, to = st.toInt()))
                }
            }
        }.trimEnd())

    }

    private fun BufferedReader.toLong(): Long {
        return readLine().toLong()
    }

    private fun StringTokenizer.toInt(): Int {
        return nextToken().toInt()
    }

    private fun StringTokenizer.toLong(): Long {
        return nextToken().toLong()
    }

}

fun main() {
    BOJ2042().solve()
}