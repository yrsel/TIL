package Algorithm.BruteForce.BOJ4096

class BOJ4096 {
    fun run() {
        val result = StringBuilder()
        while (true) {
            val num = readln()
            if (num == "0") break
            var cnt = 0
            val numArr = num.toCharArray()
            while (!isSatisfied(numArr)) {
                numArr.plus()
                cnt++
            }
            result.appendLine(cnt)
        }
        println(result.toString())
    }

    private fun isSatisfied(str: CharArray): Boolean {
        var idx = 0
        val len = str.size - 1
        while (idx <= len - idx) {
            if (str[idx] != str[len - idx]) return false
            idx++
        }
        return true
    }

    private fun CharArray.plus() {
        var idx = this.size - 1
        while (idx >= 0) {
            if (this[idx] == '9') {
                this[idx] = '0'
                --idx
            } else {
                this[idx]++
                return
            }
        }
    }

}

fun main() {
    BOJ4096().run()
}