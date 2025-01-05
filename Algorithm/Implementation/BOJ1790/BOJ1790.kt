package Algorithm.Implementation.BOJ1790

import kotlin.math.pow

class BOJ1790 {

    private val units = intArrayOf(0, 1, 20, 300, 4_000, 50_000, 600_000, 7_000_000, 80_000_000, 900_000_000)

    fun solve() {
        val (n, _k) = readln().split(" ").map { it.toInt() }
        var k = _k
        val unit = n.getUnit()
        var maxLen = 0
        for (i in 1..<unit) {
            maxLen += units[i] * 9 // 1~9 9쌍, 10,20,30,...90 9쌍, 100,200,...,900~ 9쌍,...
        }

        // 현재 단위수의 나머지 ( 1031 이면 ,1000~1031에 대한 자릿수 추가 )
        maxLen += unit * (n - 10.0.pow(unit.toDouble() - 1).toInt() + 1)
        if (maxLen < 0 || maxLen < k) { // int 범위 넘어갈 경우 음수되니 체크
            println(-1)
            return
        }

        loop@
        for (u in 1..9) { // 현재 구간의 자릿수 인덱스
            val curUnit = units[u]
            for (i in 1..9) { // 맨 앞 자리 수
                if (k - curUnit <= 0) { // 찾았을 경우
                    // 남은범위 찾아주기
                    val start = i * 10.0.pow(u.toDouble() - 1).toLong()
                    val front = (k.toLong() / u) - 1
                    val rem = k % u
                    if (rem == 0) println((start + front) % 10)
                    else println((start + front + 1).toString()[rem - 1])
                    break@loop
                } else { // 아직 도달하지 못한 경우
                    k -= curUnit
                }
            }
        }
    }

    private fun Int.getUnit(): Int {
        return when (this) {
            0 -> 0
            in 1..9 -> 1
            in 10..99 -> 2
            in 100..999 -> 3
            in 1_000..9_999 -> 4
            in 10_000..99_999 -> 5
            in 100_000..999_999 -> 6
            in 1_000_000..9_999_999 -> 7
            in 10_000_000..99_999_999 -> 8
            else -> 9
        }
    }
}

fun main() {
    BOJ1790().solve()
}