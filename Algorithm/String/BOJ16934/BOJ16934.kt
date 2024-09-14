package Algorithm.String.BOJ16934

class BOJ16934 {
    fun run() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val result = StringBuilder()
        val counter = hashMapOf<String, Int>()

        val root = 1
        var unused = 2
        val SIZE = 1_000_001
        val nxt = Array(SIZE) { IntArray(26) { -1 } }

        repeat(N) {
            val str = readLine()
            counter[str] = counter.getOrDefault(str, 0) + 1
            if (counter[str] != 1) {
                result.appendLine(str + counter[str])
            } else {
                var flag = false
                var cur = root
                for (i in str.indices) {
                    val idx = ctoi(str[i])
                    if (nxt[cur][idx] == -1) {
                        nxt[cur][idx] = unused++
                        if (!flag) {
                            flag = true
                            result.appendLine(str.substring(0, i+1))
                        }
                    }
                    cur = nxt[cur][idx]
                }
                if(!flag) result.appendLine(str)
            }
        }
        println(result.toString())
    }

    private fun ctoi(c: Char): Int {
        return c - 'a'
    }
}

fun main() {
    BOJ16934().run()
}
