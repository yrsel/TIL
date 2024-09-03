package Algorithm.Dp.BOJ2533

class BOJ2533 {

    private lateinit var connection: Array<MutableList<Int>>
    private lateinit var dp: Array<IntArray>
    private lateinit var visited: BooleanArray
    private var N = 0

    fun run() {
        N = readln().toInt()
        connection = Array(N) { mutableListOf() }
        repeat(N - 1) {
            val (from, to) = readln().split(" ").map { it.toInt() - 1 }
            connection[from].add(to)
            connection[to].add(from)
        }

        dp = Array(2) { IntArray(N) } // 시작 정점의 얼리어답터 유무 [0][],[1][]
        visited = BooleanArray(N)
        dfs(0)
        println(dp[0][0].coerceAtMost(dp[1][0]))
    }

    private fun dfs(v: Int) {
        visited[v] = true
        dp[0][v] = 0
        dp[1][v] = 1

        for (child in connection[v]) {
            if (visited[child]) continue
            dfs(child)
            dp[0][v] += dp[1][child]
            dp[1][v] += dp[0][child].coerceAtMost(dp[1][child])
        }
    }
}

fun main() {
    BOJ2533().run()
}
