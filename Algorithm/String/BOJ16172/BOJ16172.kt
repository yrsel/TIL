package Algorithm.String.BOJ16172

class BOJ16172 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val S = readLine()
        val K = readLine()
        println(getAns(S, K))
    }

    private fun getAns(S: String, K: String): Int {
        val table = getStatus(K)
        var idx = 0
        for (i in S.indices) {
            if (S[i] in '0'..'9') continue

            while (idx > 0 && S[i] != K[idx]) {
                idx = table[idx - 1]
            }
            if (S[i] == K[idx]) {
                if (idx == K.length - 1) {
                    return 1
                } else {
                    idx++
                }
            }
        }
        return 0
    }

    private fun getStatus(k: String): IntArray {
        val table = IntArray(k.length)
        var idx = 0
        for (i in 1..<k.length) {
            while (idx > 0 && k[i] != k[idx]) {
                idx = table[idx - 1]
            }
            if (k[i] == k[idx]) {
                table[i] = ++idx
            }
        }
        return table
    }
}

fun main() {
    BOJ16172().solve()
}