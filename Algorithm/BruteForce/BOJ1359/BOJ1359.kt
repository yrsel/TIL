package Algorithm.BruteForce.BOJ1359

import java.util.*

class BOJ1359 {
    private var N = 0
    private var M = 0
    private var K = 0
    private lateinit var selected: IntArray
    private val candidates = mutableListOf<IntArray>()

    fun solve() {
        val st = StringTokenizer(readln(), " ")
        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        K = st.nextToken().toInt()
        selected = IntArray(M)
        comb(0, 0)
        val numerator = calculateNumerator()
        val denominator = Math.pow(candidates.size.toDouble(), 2.0)
        println(numerator / denominator)
    }

    private fun comb(idx: Int, start: Int) {
        if (idx == M) {
            candidates.add(selected.copyOf())
            return
        }

        for (i in start..<N) {
            selected[idx] = i
            comb(idx + 1, i + 1)
        }
    }

    private fun calculateNumerator(): Int {
        var numerator = 0
        for (i in 0..<candidates.size) {
            for (j in 0..<candidates.size) {
                var cnt = 0
                candidates[i].forEach {
                    if (candidates[j].contains(it)) cnt++
                }
                if (cnt >= K) {
                    numerator++
                }
            }
        }
        return numerator
    }
}

fun main() {
    BOJ1359().solve()
}
