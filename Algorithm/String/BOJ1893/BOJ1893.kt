package Algorithm.String.BOJ1893

class BOJ1893 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        println(buildString {
            repeat(N) {
                val alphabets = readLine().toCharArray()
                val origin = readLine()
                val secretMessage = readLine()
                val result = mutableListOf<Int>()
                val table = origin.getTable()
                var idx = 0
                val mapper = alphabets.associateWith { idx++ }
                for (shiftNumber in alphabets.indices) {
                    if (isSatisfied(alphabets, origin, secretMessage, table, shiftNumber, mapper))
                        result.add(shiftNumber)
                }
                if (result.isEmpty()) appendLine("no solution")
                else if (result.size == 1) appendLine("unique: ${result.first()}")
                else {
                    append("ambiguous:")
                    result.forEach { append(" ${it}") }
                    appendLine()
                }
            }
        })
    }

    private fun String.getTable(): IntArray {
        val size = this.length
        val table = IntArray(size)

        var idx = 0
        for (i in 1..<size) {
            while (idx > 0 && this[idx] != this[i]) {
                idx = table[idx - 1]
            }

            if (this[idx] == this[i]) {
                table[i] = ++idx
            }
        }
        return table
    }

    private fun isSatisfied(
        alphabets: CharArray,
        origin: String,
        secretMessage: String,
        table: IntArray,
        shift: Int,
        mapper: Map<Char, Int>,
    ): Boolean {
        val size = alphabets.size
        var count = 0
        var idx = 0
        secretMessage.forEach {
            while (idx > 0 && alphabets[(mapper[origin[idx]]!! + shift) % size] != it) {
                idx = table[idx - 1]
            }

            if (it == alphabets[(mapper[origin[idx]]!! + shift) % size]) {
                idx++
                if (idx == origin.length) {
                    count++
                    idx = table[idx - 1]
                }
                if (count == 2) return false
            }
        }
        return count == 1
    }
}

fun main() {
    BOJ1893().solve()
}