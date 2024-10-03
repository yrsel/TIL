package Algorithm.Greedy.BOJ17615

import java.lang.Math.min

class BOJ17615 {
    fun run() {
        readln()
        val arr = readln().toCharArray()
        var result = 987_654_321
        result = min(result, counter(arr, findIdx(arr, 'B'), 'B'))
        result = min(result, counter(arr, findIdx(arr, 'R'), 'R'))
        arr.reverse() // 함수 재활용하려고 오른쪽을 판별 할 때 뒤집어서 왼쪽으로 체크
        result = min(result, counter(arr, findIdx(arr, 'B'), 'B'))
        result = min(result, counter(arr, findIdx(arr, 'R'), 'R'))
        println(result)
    }

    private fun findIdx(arr: CharArray, color: Char): Int {
        var idx = 0
        while (idx != arr.size && arr[idx] == color) ++idx
        return idx
    }

    private fun counter(arr: CharArray, startIdx: Int, findColor: Char): Int {
        var count = 0
        for (i in startIdx..<arr.size) {
            if (arr[i] == findColor) count++
        }
        return count
    }

}

fun main() {
    BOJ17615().run()
}