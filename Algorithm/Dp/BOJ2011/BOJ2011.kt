package Algorithm.Dp.BOJ2011

class BOJ2011 {

    private val MOD = 1_000_000

    fun run() {
        val arr = readln().toCharArray().map { it.digitToInt() }.toIntArray()
        val dp = IntArray(arr.size + 1)

        if (isZero(arr)) return

        dp[0] = 1
        dp[1] = 1
        for (i in 2..arr.size) {
            if (arr[i - 1] != 0) {
                dp[i] = dp[i - 1]
            }

            val curLastCandidate = arr[i - 2] * 10 + arr[i - 1] // 앞의 수와의 결합
            if (curLastCandidate in 10..26) {
                dp[i] = (dp[i] % MOD + dp[i - 2] % MOD) % MOD
            }
        }

        println(dp[arr.size] % MOD)
    }

    private fun isZero(arr: IntArray): Boolean {
        val size = arr.size
        if (arr[0] == 0) { // 첫번째 원소가 0 인경우
            println(0)
            return true
        }
        if (size >= 2) { // 마지막 원소가 0이면서 마지막 두 수가 26보다 크면 알파벳을 완성시킬 수 없다.
            if ((arr[size - 2] != 1 && arr[size - 2] != 2) && arr[size - 1] == 0) {
                println(0)
                return true
            }
        }
        return false
    }
}

fun main() {
    BOJ2011().run()
}