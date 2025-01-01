package Algorithm.Implementation.BOJ4999

class BOJ4999 {
    fun solve() {
        val s1 = readln()
        val s2 = readln()
        if (s1.count { it == 'a' } >= s2.count { it == 'a' }) {
            println("go")
        } else {
            println("no")
        }
    }
}

fun main() {
    BOJ4999().solve()
}