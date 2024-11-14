package Algorithm.BinarySearch.BOJ2792

import java.io.StreamTokenizer

class BOJ2792 {

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }

        val N = input()
        val M = input()
        val arr = IntArray(M) { input() }
        val max = arr.max()

        println(findMin(arr, max, N))
    }

    private fun findMin(arr: IntArray, max: Int, peopleCnt: Int): Int {
        var left = 1
        var right = max
        while (left <= right) {
            val mid = (left + right) / 2
            var cnt = 0
            arr.forEach {
                cnt += if (it % mid == 0) it / mid
                else (it / mid) + 1
            }
            if (cnt > peopleCnt) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

}

fun main() {
    BOJ2792().solve()
}