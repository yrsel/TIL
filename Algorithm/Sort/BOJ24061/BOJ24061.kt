package Algorithm.Sort.BOJ24061

import java.util.*

class BOJ24061 {
    private var N = 0
    private var K = 0
    private lateinit var arr: IntArray
    private lateinit var sorted: IntArray
    private var cnt = 0
    private var result: IntArray? = null
    fun solve() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        N = st.nextToken().toInt()
        K = st.nextToken().toInt()
        st = StringTokenizer(readLine(), " ")
        arr = IntArray(N) { st.nextToken().toInt() }
        sorted = IntArray(N)

        mergeSort(0, N - 1)
        println(result?.joinToString(" ") { it.toString() } ?: -1)
    }

    private fun mergeSort(left: Int, right: Int) {
        if (left >= right) return

        val mid = (left + right) / 2
        mergeSort(left, mid)
        mergeSort(mid + 1, right)
        merge(left, mid, right)
    }

    private fun merge(left: Int, mid: Int, right: Int) {
        var L = left
        var R = mid + 1
        var idx = left

        while (L <= mid && R <= right) {
            if (arr[L] < arr[R]) {
                sorted[idx++] = arr[L++]
                if (++cnt == K) {
                    copy(left, idx - 1)
                    result = arr.copyOf()
                }

            } else {
                sorted[idx++] = arr[R++]
                if (++cnt == K) {
                    copy(left, idx - 1)
                    result = arr.copyOf()
                }
            }
        }

        while (L <= mid) {
            sorted[idx++] = arr[L++]
            if (++cnt == K) {
                copy(left, idx - 1)
                result = arr.copyOf()
            }
        }
        while (R <= right) {
            sorted[idx++] = arr[R++]
            if (++cnt == K) {
                copy(left, idx - 1)
                result = arr.copyOf()
            }
        }

        copy(left, right)
    }

    private fun copy(from: Int, to: Int) {
        for (i in from..to) {
            arr[i] = sorted[i]
        }
    }

}

fun main() {
    BOJ24061().solve()
}