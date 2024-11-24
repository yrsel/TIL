package Algorithm.Greedy.BOJ2138

class BOJ2138 {
    private var N = 0
    fun solve() = with(System.`in`.bufferedReader()) {
        N = readLine().toInt()
        val origin = readLine().toCharArray()
        val statusTwo = readLine().toCharArray()

        var min = Integer.MAX_VALUE
        min = Math.min(min, origin.copyOf().comp(statusTwo, false))
        min = Math.min(min, origin.comp(statusTwo, true))
        if (min == Integer.MAX_VALUE) min = -1
        println(min)
    }

    private fun CharArray.comp(comp: CharArray, flag: Boolean): Int {
        var cnt = 0
        var idx = 1
        if (flag) {
            this[0] = this[0].convert()
            this[1] = this[1].convert()
            ++cnt
        }

        while (idx != N - 1) {
            if (this[idx - 1] != comp[idx - 1]) {
                this[idx - 1] = this[idx - 1].convert()
                this[idx] = this[idx].convert()
                this[idx + 1] = this[idx + 1].convert()
                ++cnt
            }
            ++idx
        }

        if (this[idx - 1] != comp[idx - 1] || this[idx] != comp[idx]) {
            this[idx - 1] = this[idx - 1].convert()
            this[idx] = this[idx].convert()
            ++cnt
        }

        return if (this.contentEquals(comp)) cnt
        else Integer.MAX_VALUE
    }

    private fun Char.convert() = if (this == '0') '1' else '0'
}

fun main() {
    BOJ2138().solve()
}