package Algorithm.BinarySearch.BOJ20444

class BOJ20444 {
    fun solve() {
        val (n, k) = readln().split(" ")
        val N = n.toLong()
        val K = k.toLong()

        var answer: String? = null

        var left = 0L
        var right = N / 2
        while (left <= right) {
            val mid = (left + right) / 2
            val sum = (N - mid + 1) * (mid + 1)
            if (sum == K) {
                answer = "YES"
                break
            } else if (sum > K) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        println(answer ?: "NO")
    }
}


fun main() {
    BOJ20444().solve()
}