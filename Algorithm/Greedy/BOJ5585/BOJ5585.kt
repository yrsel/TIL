package Algorithm.Greedy.BOJ5585

class BOJ5585 {
    fun solve() {
        var change = 1_000 - readln().toInt()
        val units = intArrayOf(500, 100, 50, 10, 5, 1)

        var cnt = 0
        units.forEach {
            if (change >= it) {
                val unitCount = change / it
                change -= it * unitCount
                cnt += unitCount
            }
        }
        println(cnt)
    }
}

fun main() {
    BOJ5585().solve()
}