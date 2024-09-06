package Algorithm.Greedy.BOJ13019

class BOJ13019 {
    fun run(): Int {
        val str = readln().toCharArray()
        val comp = readln().toCharArray()

        val counter = mutableListOf<Char>()
        str.forEach { counter.add(it) }
        for (c in comp) {
            if (!counter.remove(c)) {
                return -1
            }
        }

        var cnt = 0
        var idx = str.size - 1
        for (i in str.size - 1 downTo 0) {
            if (str[i] == comp[idx]) {
                --idx
            } else {
                cnt++
            }
        }

        return cnt
    }
}

fun main() {
    println(BOJ13019().run())
}