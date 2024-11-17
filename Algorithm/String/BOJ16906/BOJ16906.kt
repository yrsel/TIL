package Algorithm.String.BOJ16906

import java.util.*

class BOJ16906 {
    private val max = 1_000_005
    private lateinit var nxt: Array<IntArray>
    private lateinit var isLast: BooleanArray
    private lateinit var visited: BooleanArray
    private var unused = 2
    private var word: String? = null

    fun solve() = with(System.`in`.bufferedReader()) {
        nxt = Array(max) { IntArray(2) { -1 } }
        isLast = BooleanArray(max)
        visited = BooleanArray(max)

        val N = readLine().toInt()
        var flag = false
        println(buildString {
            appendLine(1)
            val st = StringTokenizer(readLine(), " ")
            repeat(N) {
                val len = st.nextToken().toInt()
                if (!flag) {
                    word = null
                    mark(1, 0, StringBuilder(), len)
                    word?.let { appendLine(word) } ?: run { flag = true }
                }
            }
            if (flag) {
                clear()
                append(-1)
            }
        })
    }

    private fun mark(pos: Int, alphabet: Int, str: StringBuilder, len: Int) {
        if (word != null || isLast[pos]) return // 답을 구했다 || 접두사가 된다
        if (nxt[pos][alphabet] == -1) { // 아직 방문하지 않았던 문자열
            nxt[pos][alphabet] = unused++
        }
        val nextPos = nxt[pos][alphabet] // 위치 조정

        if (str.length == len) {  // 길이에 도달했다면
            if (isLast[nextPos] || visited[nextPos]) return // 접두사가 되거나 지나간 경로(긴 문자열의 접두사가 되는 경우)
            isLast[nextPos] = true
            word = str.toString()
            return
        }
        visited[nextPos] = true

        mark(nextPos, 0, str.append(0), len)
        str.deleteCharAt(str.lastIndex)
        mark(nextPos, 1, str.append(1), len)
        str.deleteCharAt(str.lastIndex)
    }
}

fun main() {
    BOJ16906().solve()
}