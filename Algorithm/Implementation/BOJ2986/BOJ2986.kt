package Algorithm.Implementation.BOJ2986

class BOJ2986 {
    fun solve() {
        val n = readln().toInt()
        var div = 2
        val max = Math.sqrt(n.toDouble())
        while (true) {
            if (n % div == 0) break
            if (++div > max) break
        }
        if (div > max) println(n - 1)
        else println(n - (n / div))
    }
}

fun main() {
    BOJ2986().solve()
}