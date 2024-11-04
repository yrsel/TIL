package Algorithm.PrefixSum.BOJ10427

import java.io.StreamTokenizer

class BOJ10427 {

    private var size = 0

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken(); nval.toInt() }

        println(buildString {
            repeat(input()) {
                val arr = IntArray(input()) { input() }
                size = arr.size
                arr.sort()

                val diffInfos = Array(size) { IntArray(size) }
                for (r in arr.indices) {
                    for (c in arr.indices) {
                        if (r + 1 + c >= size) break
                        diffInfos[r][c] = arr[r + 1 + c] - arr[c]
                    }
                }

                val memo = Array(size) { LongArray(size) { Long.MAX_VALUE } }

                var sum = 0L
                for (r in 0..<size - 1) {
                    var curMin = Long.MAX_VALUE
                    for (c in memo.indices) {
                        if (r + 1 + c >= size) break
                        if (r == 0) {
                            memo[0][c] = diffInfos[0][c].toLong()
                        } else {
                            memo[r][c] = diffInfos[r][c] + memo[r - 1][c + 1]
                        }
                        curMin = curMin.coerceAtMost(memo[r][c])
                    }
                    sum += curMin
                }
                appendLine(sum)
            }
        })
    }
}

fun main() {
    BOJ10427().solve()
}