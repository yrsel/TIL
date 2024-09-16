package Algorithm.String.BOJ9202

import java.io.BufferedReader
import java.io.InputStreamReader

class BOJ9202 {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var size = 300_000 * 8 + 1
    private val nxt = Array(size) { IntArray(26) { -1 } }
    private val ended = BooleanArray(size)
    private var unused = 2

    private val result = StringBuilder()
    private lateinit var map: Array<CharArray>
    private lateinit var hs: HashSet<String>
    private lateinit var visited: Array<BooleanArray>
    private val dir = arrayOf(
        arrayOf(-1, -1), arrayOf(-1, 0), arrayOf(-1, 1),
        arrayOf(0, -1), arrayOf(0, 1),
        arrayOf(1, -1), arrayOf(1, 0), arrayOf(1, 1)
    )

    fun run() {
        val N = br.readLine().toInt()

        setDictionary(N)

        br.readLine()

        val T = br.readLine().toInt()

        for (tc in 1..T) {
            map = Array(4) { CharArray(4) }
            for (i in 0..<4) {
                val s = br.readLine()
                for (j in 0..<4) {
                    map[i][j] = s[j]
                }
            }

            hs = hashSetOf()
            visited = Array(4) { BooleanArray(4) }
            for (i in 0..<4) {
                for (j in 0..<4) {
                    val cIdx = ctoi(map[i][j])
                    if (nxt[1][cIdx] != -1) {
                        visited[i][j] = true
                        dfs(i, j, nxt[1][cIdx], StringBuilder().append(map[i][j]))
                        visited[i][j] = false
                    }
                }
            }

            val list = mutableListOf<String>()
            list.addAll(hs)
            list.sortWith { o1, o2 ->
                if (o1.length == o2.length) o1.compareTo(o2) else o2.length.compareTo(o1.length)
            }

            var score = 0
            var answerStr = ""
            val cnt = list.size

            for (i in 0..<list.size) {
                if (i == 0) answerStr = list[0]
                score += getScore(list[i])
            }

            result.appendLine("$score $answerStr $cnt")
            if (tc != T) br.readLine()
        }
        println(result.toString())
    }

    private fun setDictionary(N: Int) {
        repeat(N) {
            val str = br.readLine()
            var cur = 1
            for (i in str.indices) {
                val idx = ctoi(str[i])
                if (nxt[cur][idx] == -1) {
                    nxt[cur][idx] = unused++
                }
                cur = nxt[cur][idx]
            }
            ended[cur] = true
        }
    }

    private fun dfs(r: Int, c: Int, cur: Int, sb: StringBuilder) {
        for (d in 0..<8) {
            val nr = r + dir[d][0]
            val nc = c + dir[d][1]
            if (isOut(nr, nc) || visited[nr][nc]) continue
            val ncIdx = ctoi(map[nr][nc])
            if (nxt[cur][ncIdx] != -1) {
                visited[nr][nc] = true
                sb.append(map[nr][nc])

                if (ended[nxt[cur][ncIdx]]) hs.add(sb.toString())
                dfs(nr, nc, nxt[cur][ctoi(map[nr][nc])], sb)

                sb.deleteCharAt(sb.length - 1)
                visited[nr][nc] = false
            }
        }
    }

    private fun ctoi(c: Char) = c - 'A'

    private fun getScore(str: String) = when (str.length) {
        8 -> 11
        7 -> 5
        6 -> 3
        5 -> 2
        3, 4 -> 1
        else -> 0
    }

    private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= 4 || c >= 4
}

fun main() {
    BOJ9202().run()
}