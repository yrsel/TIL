package Algorithm.BruteForce.BOJ2239

class BOJ2239 {
    private lateinit var map: Array<IntArray>
    private var result = ""
    fun solve() = with(System.`in`.bufferedReader()) {
        map = Array(9) { i ->
            readLine().toCharArray().map { it.digitToInt() }.toIntArray()
        }
        search(0, 0)
        println(result)
    }

    private fun search(r: Int, c: Int) {
        if (result.isNotEmpty()) return
        if (r == 9) {
            result = map.joinToString("\n") { it.joinToString("") { num -> num.toString() } }
            return
        }

        if (c != 9 && map[r][c] != 0) search(r, c + 1)
        else if (c == 9) search(r + 1, 0)
        else {
            for (i in 1..9) {
                if (!isSatisfied(r, c, i)) continue
                map[r][c] = i
                search(r, c + 1)
                map[r][c] = 0
            }
        }
    }

    private fun isSatisfied(r: Int, c: Int, number: Int): Boolean {
        for (i in 0..<9) {
            if (map[r][i] == number || map[i][c] == number) return false
        }
        for (i in 0..<3) {
            for (j in 0..<3) {
                val sectionR = (r / 3) * 3
                val sectionC = (c / 3) * 3
                if (map[sectionR + i][sectionC + j] == number) return false
            }
        }
        return true
    }
}

fun main() {
    BOJ2239().solve()
}