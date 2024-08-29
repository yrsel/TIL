package Algorithm.BinarySearch.BOJ16401

class BOJ16401 {
    fun run() {
        val (M, _) = readln().split(" ").map { it.toInt() }
        val arr = readln().split(" ").map { it.toInt() }.toIntArray()
        val sum = arr.sum()
        if (sum < M) {
            println(0)
            return
        }

        var left = 1
        var right = 1_000_000_000
        while (left <= right) {
            val mid = (left + right) / 2

            if (isSatisfied(mid, M, arr)) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        println(left - 1)
    }

    private fun isSatisfied(len: Int, M: Int, arr: IntArray): Boolean {
        var cnt = 0
        for (i in arr.size - 1 downTo 0) {
            cnt += arr[i] / len
            if (cnt >= M) return true
        }
        return false
    }
}

fun main() {
    BOJ16401().run()
}