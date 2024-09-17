package Algorithm.Implementation.BOJ2903

class BOJ2903 {
    fun run() {
        val n = readln().toInt()
        var l = 2
        repeat(n) {
            l += (l - 1)
        }
        println(l*l)
    }
}

fun main() {
    BOJ2903().run()
}