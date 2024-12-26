package Algorithm.String.BOJ1305

class BOJ1305 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val L = readLine().toInt()
        val str = readLine()
        val table = str.makeTable()
        println(L - table.last())
    }

    private fun String.makeTable(): IntArray {
        val table = IntArray(this.length)
        var idx = 0
        for (i in 1..<this.length) {
            while (idx > 0 && this[i] != this[idx]) idx = table[--idx]
            if (this[i] == this[idx]) table[i] = ++idx
        }
        return table
    }
}

fun main() {
    BOJ1305().solve()
}