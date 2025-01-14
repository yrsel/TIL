package Algorithm.BinarySearch.BOJ1484

class BOJ1484 {
    fun solve() {
        val g = readln().toInt()
        val result = StringBuilder()
        var left = 1L
        var right = 2L
        while (left != right) {
            val diff = right * right - left * left
            if (diff <= g) {
                if (diff == g.toLong()) result.appendLine(right)
                right++
            } else if (diff > g) {
                left++
            }
        }
        if (result.isEmpty()) result.appendLine(-1)
        println(result.toString())
    }
}

fun main() {
    BOJ1484().solve()
}