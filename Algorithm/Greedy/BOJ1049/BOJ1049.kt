package Algorithm.Greedy.BOJ1049

import java.io.StreamTokenizer

class BOJ1049 {
    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val toInt = { nextToken(); nval.toInt() }
        val N = toInt()
        val M = toInt()

        var packageMinPrice = 987_654_321
        var oneMinPrice = 987_654_321
        repeat(M) {
            packageMinPrice = Math.min(packageMinPrice, toInt())
            oneMinPrice = Math.min(oneMinPrice, toInt())
        }

        if (packageMinPrice >= oneMinPrice * 6) {
            println(oneMinPrice * N)
        } else {
            val buyPackageCount = N / 6
            println(
                Math.min(
                    packageMinPrice * (buyPackageCount + 1),
                    packageMinPrice * buyPackageCount + oneMinPrice * (N - buyPackageCount * 6)
                )
            )
        }
    }
}

fun main() {
    BOJ1049().solve()
}