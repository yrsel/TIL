package Algorithm.BruteForce.BOJ1411

class BOJ1411 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val words = Array(n) { readLine() }
        var cnt = 0
        for (i in 0..<n - 1) {
            for (j in i + 1..<n) {
                if (isSatisfied(words[i], words[j])) cnt++
            }
        }
        println(cnt)
    }

    private fun isSatisfied(A: String, B: String): Boolean {
        val size = A.length
        val visited = BooleanArray(26)
        val mapper = hashMapOf<Char, Char>()
        for (i in 0..<size) {
            if (mapper[A[i]] == null) {
                mapper[A[i]] = B[i]
                if (visited[B[i].code - 97]) return false
                visited[B[i].code - 97] = true
            } else {
                if (mapper[A[i]] != B[i]) return false
            }
        }
        return true
    }
}

fun main() {
    BOJ1411().solve()
}