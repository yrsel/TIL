package Algorithm.BruteForce.BOJ21278

class BOJ21278 {
    private val MAX = 987_654_321
    fun run() {
        val (N, M) = readln().split(" ").map { it.toInt() }
        val map = Array(N) { mutableListOf<Int>() }
        repeat(M) {
            val (from, to) = readln().split(" ").map { it.toInt() - 1 }
            map[from].add(to)
            map[to].add(from)
        }
        val dist = Array(N) { IntArray(N) { MAX } }
        for (start in 0..<N) {
            bfs(start, dist, map)
        }

        var a = -1
        var b = -1
        var result = MAX
        for (i in 0..<N - 1) {
            for (j in i + 1..<N) {
                var curSum = 0
                for (k in 0..<N) {
                    if (k == i || k == j) continue
                    curSum += Math.min(dist[i][k], dist[j][k])
                }
                if (result > curSum) {
                    result = curSum
                    a = i
                    b = j
                }
            }
        }

        println("${a + 1} ${b + 1} ${result * 2}")
    }

    private fun bfs(start: Int, dist: Array<IntArray>, map: Array<MutableList<Int>>) {
        val hs = hashSetOf(start)
        val q = ArrayDeque<Pair<Int, Int>>()
        q.addLast(Pair(start, 0))
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            for (next in map[curr.first]) {
                if (hs.add(next)) {
                    dist[start][next] = curr.second + 1
                    q.add(Pair(next, curr.second + 1))
                }
            }
        }
    }
}

fun main() {
    BOJ21278().run()
}