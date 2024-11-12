package Algorithm.Greedy.BOJ1474

class BOJ1474 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val arr = Array(N) { readLine() }
        val len = arr.sumOf { it.length }
        val commonUnderlineScore = (M - len) / (N - 1)
        val underlineScores = Array(N - 1) { (M - len) / (N - 1) }
        var additional = M - (len + (commonUnderlineScore * (N - 1)))

        for (i in 1..<N) {
            if (additional == 0) break
            if (arr[i][0] in 'a'..'z') {
                underlineScores[i - 1]++
                additional--
            }
        }
        for (i in N - 1 downTo 1) {
            if (additional == 0) break
            if (arr[i][0] in 'A'..'Z') {
                underlineScores[i - 1]++
                additional--
            }
        }
        println(buildString {
            for (i in 0..<N) {
                append(arr[i])
                if (i != N - 1) append(underlineScores[i].toUnderline())
            }
        })
    }

    private fun Int.toUnderline(): String {
        return buildString {
            repeat(this@toUnderline) { append('_') }
        }
    }
}

fun main() {
    BOJ1474().solve()
}