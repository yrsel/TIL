package Algorithm.Dp.BOJ12026

class BOJ12026 {
    private val MAX = 987_543_321
    fun solve() {
        val N = readln().toInt()
        val command = readln().toCharArray()
        val distances = IntArray(N) { MAX }
        distances[0] = 0
        for (i in 1..<N) {
            for (j in 0..<i) {
                when (command[i]) {
                    'B' -> {
                        if (command[j] == 'J' && distances[j] != MAX) distances[i] =
                            distances[i].coerceAtMost(distances[j] + (i - j) * (i - j))
                    }

                    'O' -> {
                        if (command[j] == 'B' && distances[j] != MAX) distances[i] =
                            distances[i].coerceAtMost(distances[j] + (i - j) * (i - j))
                    }

                    'J' -> {
                        if (command[j] == 'O' && distances[j] != MAX) distances[i] =
                            distances[i].coerceAtMost(distances[j] + (i - j) * (i - j))
                    }
                }
            }
        }
        
        if (distances[N - 1] == MAX) println(-1)
        else println(distances[N - 1])
    }
}

fun main() {
    BOJ12026().solve()
}