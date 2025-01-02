package Algorithm.BruteForce.BOJ1639

class BOJ1639 {
    fun solve() {
        val arr = readln().toCharArray().map { it.digitToInt() }
        val size = arr.size
        val prefixSum = IntArray(size + 1)
        for (i in 1..size) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1]
        }

        var result = 0
        loop@
        for (range in size / 2 downTo 1) {
            for (start in 0..size) {
                if (range * 2 + start > size) break
                if (prefixSum[range + start] - prefixSum[start] == prefixSum[range * 2 + start] - prefixSum[range + start]) {
                    result = range * 2
                    break@loop
                }
            }
        }
        println(result)
    }
}

fun main() {
    BOJ1639().solve()
}