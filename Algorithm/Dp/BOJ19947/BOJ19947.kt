package Algorithm.Dp.BOJ19947

class BOJ19947 {
    fun solve() {
        val (H, Y) = readln().split(" ").map { it.toInt() }
        val arr = IntArray(Y + 1)
        arr[0] = H
        for (i in 1..Y) {
            arr[i] = calculate(arr[i - 1], 1.05)
            if (i >= 3) arr[i] = calculate(arr[i - 3], 1.2).coerceAtLeast(arr[i])
            if (i >= 5) arr[i] = calculate(arr[i - 5], 1.35).coerceAtLeast(arr[i])
        }
        println(arr[Y])
    }

    private fun calculate(money: Int, rate: Double): Int {
        return (money * rate).toInt()
    }
}

fun main() {
    BOJ19947().solve()
}