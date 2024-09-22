package Algorithm.Greedy.BOJ7570

class BOJ7570 {
    fun run() {
        val N = readln().toInt()
        val arr = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
        val dp = IntArray(arr.size) { -1 }

        var maxContinueCnt = 1
        arr.forEach {
            if (it != 0 && dp[it - 1] != -1) { // 1번이 아니면서, 이미 자기 앞 번호가 나왔다면 연속가능한 최대 개수 기록
                dp[it] = dp[it - 1] + 1
                maxContinueCnt = dp[it].coerceAtLeast(maxContinueCnt)
            } else { // 1번이거나, 자기 바로 앞 번호가 아직 안나왔다면 자기 자신이 최대 연속 수
                dp[it] = 1
            }
        }
        println(N - maxContinueCnt)
    }
}

fun main() {
    BOJ7570().run()
}