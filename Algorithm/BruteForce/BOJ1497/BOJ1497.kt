package Algorithm.BruteForce.BOJ1497

import java.util.*
import kotlin.math.pow

class BOJ1497 {

    private var N = 0
    private lateinit var arr: LongArray
    private lateinit var selected: BooleanArray
    private var gCnt = 0
    private var result = 11

    fun run() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")

        N = st.nextToken().toInt()
        val M = st.nextToken().toInt()

        arr = LongArray(N)
        repeat(N) { idx ->
            st = StringTokenizer(readLine(), " ")
            st.nextToken()
            st.nextToken().forEachIndexed { i, input ->
                if (input == 'Y') {
                    arr[idx] += 2.0.pow((M - 1 - i).toDouble()).toLong()
                }
            }
        }

        selected = BooleanArray(N)
        search(0, 0)
        if (gCnt == 0) result = -1
        println(result)
    }

    private fun search(idx: Int, cnt: Int) {
        if (idx == N) {
            var flag = 0L
            selected.forEachIndexed { i, cur ->
                if (cur) {
                    flag = flag.or(arr[i])
                }
            }
            val count = flag.toString(2).count { it == '1' }
            if (count >= gCnt) {
                gCnt = count
                result = result.coerceAtMost(cnt)
            }
            return
        }

        selected[idx] = true
        search(idx + 1, cnt + 1)
        selected[idx] = false
        search(idx + 1, cnt)
    }
}

fun main() {
    BOJ1497().run()
}