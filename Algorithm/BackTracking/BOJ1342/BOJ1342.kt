package Algorithm.BackTracking.BOJ1342

class BOJ1342 {

    private lateinit var arr: CharArray
    private val counter = IntArray(26)
    private var cnt = 0

    fun solve() {
        arr = readln().toCharArray()
        arr.forEach { counter[it.code - 97]++ }

        perm(0, "")

        println(cnt)
    }

    private fun perm(idx: Int, comp: String) {
        if (idx == arr.size) {
            cnt++
            return
        }

        for (i in 0..25) {
            if (counter[i] == 0) continue
            if (comp.isNotEmpty() && comp.last() == (i + 97).toChar()) continue
            counter[i]--
            perm(idx + 1, comp + (i + 97).toChar())
            counter[i]++
        }
    }

}

fun main() {
    BOJ1342().solve()
}