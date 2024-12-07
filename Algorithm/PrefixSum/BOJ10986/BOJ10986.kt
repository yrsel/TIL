package Algorithm.PrefixSum.BOJ10986

import java.io.StreamTokenizer

class BOJ10986 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val M = toInt()
        val arr = IntArray(N) { toInt() }
        val prefixSum = LongArray(N)
        for (index in 0..<N) {
            prefixSum[index] = if (index == 0) arr[index].toLong()
            else prefixSum[index - 1] + arr[index]
        }
        val divCntArr = IntArray(M)
        prefixSum.forEach { divCntArr[(it % M).toInt()]++ }
        var result = 0L
        divCntArr.forEachIndexed { index, cnt ->
            if (cnt != 0) {
                if (index == 0) result += cnt
                result += (cnt.toLong()) * (cnt - 1) / 2 // cnt 가 오버플로우 발생할 수 있어 곱하기 전에 형변환 해주거나 LongArray 선언 필요
            }
        }
        println(result)
    }
}

fun main() {
    BOJ10986().solve()
}