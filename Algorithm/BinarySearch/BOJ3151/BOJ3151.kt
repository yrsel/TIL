package Algorithm.BinarySearch.BOJ3151

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class BOJ3151 {

    fun run() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val arr = IntArray(n)
        val st = StringTokenizer(readLine(), " ")
        for (i in 0..<n) {
            arr[i] = st.nextToken().toInt()
        }

        arr.sort()

        var count = 0L
        for (i in 0..<n - 2) {
            if (arr[i] > 0) break
            val cur = arr[i]
            var left = i + 1
            var right = n - 1
            while (left < right) {
                val sum = arr[left] + arr[right] + cur
                if (sum == 0) {
                    if (arr[left] != arr[right]) {
                        var lCnt = 1
                        while (arr[left + 1] == arr[left]) {
                            ++left
                            ++lCnt
                        }
                        var rCnt = 1
                        while (arr[right - 1] == arr[right]) {
                            --right
                            ++rCnt
                        }
                        count += lCnt * rCnt
                    } else {
                        count += (right + 1 - left) * (right - left) / 2
                        break
                    }
                    ++left
                } else if (sum > 0) {
                    --right
                } else {
                    ++left
                }
            }
        }

        println(count)

    }
}

fun main() {
    BOJ3151().run()
}