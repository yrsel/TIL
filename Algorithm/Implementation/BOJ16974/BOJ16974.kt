package Algorithm.Implementation.BOJ16974

import java.util.*

class BOJ16974 {

    private var N = 0
    private var K = 0L
    private var burgerLen = LongArray(51)
    private var pattyLen = LongArray(51)
    private var answer = 0L

    fun run() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine(), " ")
        N = st.nextToken().toInt()
        K = st.nextToken().toLong()
        burgerLen[0] = 1L
        pattyLen[0] = 1L
        initBurgers(0)
        recur(N, K)
        println(answer)
    }

    private fun initBurgers(level: Int) {
        if (level == N) {
            return
        }
        burgerLen[level + 1] += burgerLen[level] * 2 + 3
        pattyLen[level + 1] = pattyLen[level] * 2 + 1

        initBurgers(level + 1)
    }

    private fun recur(level: Int, K: Long) {
        if (K == 0L) return
        if (level == 0) {
            answer++
            return
        }

        val left = burgerLen[level] / 2
        if (left + 1 >= K) {
            if (left == K || left + 1 == K) {
                answer += pattyLen[level - 1]
                if (left + 1 == K) answer++
                return
            } else {
                recur(level - 1, K - 1) // 맨 앞에 B 제거 해주기(K -1 인 이유)
            }
        } else {
            answer += pattyLen[level - 1] + 1
            recur(level - 1, K - (left + 1))
        }
    }
}

fun main() {
    BOJ16974().run()
}

