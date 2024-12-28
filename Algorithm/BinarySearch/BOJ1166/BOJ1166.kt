package Algorithm.BinarySearch.BOJ1166

class BOJ1166 {
    fun solve() {
        val (N, L, W, H) = readln().split(" ").map { it.toInt() }

        var left = 0.0
        var right = Math.min(Math.min(L, W), H).toDouble()
        while (left < right) {
            val mid = (left + right) / 2
            if (mid.isSatisfied(L, W, H, N)) {
                if (left == mid) break
                left = mid
            } else {
                if (right == mid) break
                right = mid
            }
        }
        println(left)
    }

    private fun Double.isSatisfied(L: Int, W: Int, H: Int, goalCount: Int): Boolean {
        return goalCount <= (L / this).toLong() * (W / this).toLong() * (H / this).toLong()
    }
}

fun main() {
    BOJ1166().solve()
}