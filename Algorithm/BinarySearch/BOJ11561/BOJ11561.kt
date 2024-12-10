class BOJ11561 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val T = readLine().toInt()
        println(buildString {
            repeat(T) {
                val N = readLine().toULong()
                var left = 1UL
                var right = N
                while (left <= right) {
                    val mid = (left + right) / 2UL
                    val count = mid * (1UL + mid) / 2UL
                    if (count <= N) {
                        left = mid + 1UL
                    } else {
                        right = mid - 1UL
                    }
                }
                appendLine(right)
            }
        })
    }
}

fun main() {
    BOJ11561().solve()
}
