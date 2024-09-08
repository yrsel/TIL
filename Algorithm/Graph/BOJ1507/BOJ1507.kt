package Algorithm.Graph.BOJ1507

class BOJ1507 {

    private val DUMMY = 987_654_321

    fun run(): Int {
        val n = readln().toInt()
        val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val dp = Array(n) { IntArray(n) }
        for (i in 0..<n) {
            for (j in 0..<n) {
                dp[i][j] = map[i][j]
            }
        }

        for (k in 0..<n) {
            for (i in 0..<n) {
                if (i == k) continue
                for (j in 0..<n) {
                    if (i == j || k == j) continue
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        return -1
                    }
                    if (map[i][j] == map[i][k] + map[k][j]) dp[i][j] = DUMMY
                }
            }
        }

        var result = 0
        for (i in 0..<n) {
            for (j in i + 1..<n) {
                if (dp[i][j] == DUMMY) continue
                result += dp[i][j]
            }
        }

        return result
    }
}

fun main() {
    println(BOJ1507().run())
}