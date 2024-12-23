package Algorithm.BruteForce.BOJ1487

import java.io.StreamTokenizer

class BOJ1487 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val n = toInt()
        val arr = Array(n) { Pair(toInt(), toInt()) }
        var maxGain = 0
        var result = 987_654_321

        for (i in arr.indices) {
            val curCost = arr[i].first
            var sum = 0
            for (j in arr.indices) {
                if (arr[j].first < curCost) continue
                val bill = curCost - arr[j].second
                if (bill <= 0) continue
                sum += bill
            }
            if (maxGain <= sum) {
                if (sum == maxGain) result = Math.min(result, curCost)
                else result = curCost
                maxGain = sum
            }
        }
        if (maxGain == 0) result = 0
        println(result)
    }
}

fun main() {
    BOJ1487().solve()
}