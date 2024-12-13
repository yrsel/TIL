package Algorithm.String.BOJ1254

class BOJ1254 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val S = readLine()
        var result = 0
        for (i in S.indices) {
            if (S.substring(i).isSatisfied()) {
                result = i
                break
            }
        }
        result += S.length
        println(result)
    }

    private fun String.isSatisfied(): Boolean {
        var left = 0
        var right = this.length - 1
        while (left <= right) {
            if (this[left++] != this[right--]) return false
        }
        return true
    }
}

fun main() {
    BOJ1254().solve()
}