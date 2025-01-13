package Algorithm.DataStructure.BOJ21944

import java.util.*

class BOJ21944 {
    private data class Info(val problem: Int = 0, val level: Int, val category: Int = 0) : Comparable<Info> {
        override fun compareTo(other: Info): Int {
            if (this.level == other.level) return this.problem.compareTo(other.problem)
            else return this.level.compareTo(other.level)
        }
    }

    private val sb = StringBuilder()
    private lateinit var cache: HashMap<Int, Info>
    private lateinit var categories: HashMap<Int, TreeSet<Info>>
    private lateinit var nonClassifications: TreeSet<Info>

    fun solve() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        cache = hashMapOf() // 문제번호, 정보
        categories = hashMapOf() // 카테고리, 정보
        nonClassifications = TreeSet<Info>()
        var st: StringTokenizer
        repeat(n) {
            st = StringTokenizer(readLine(), " ")
            val p = st.nextToken().toInt()
            val l = st.nextToken().toInt()
            val c = st.nextToken().toInt()
            add(p, l, c)
        }

        val q = readLine().toInt()
        repeat(q) {
            st = StringTokenizer(readLine(), " ")
            when (st.nextToken()) {
                "recommend" -> {
                    val category = st.nextToken().toInt()
                    val command = st.nextToken().toInt()
                    val infos = if (command == 1) categories[category]!!.last()
                    else categories[category]!!.first()
                    sb.appendLine(infos.problem)
                }

                "recommend2" -> {
                    val command = st.nextToken().toInt()
                    val infos = if (command == 1) nonClassifications.last()
                    else nonClassifications.first()
                    sb.appendLine(infos.problem)
                }

                "recommend3" -> {
                    val command = st.nextToken().toInt()
                    val standard = st.nextToken().toInt()
                    val infos = if (command == 1) nonClassifications.ceiling(Info(level = standard))
                    else nonClassifications.floor(Info(level = standard))

                    sb.appendLine(infos?.problem ?: -1)
                }

                "add" -> {
                    val p = st.nextToken().toInt()
                    val l = st.nextToken().toInt()
                    val c = st.nextToken().toInt()
                    add(p, l, c)
                }

                "solved" -> {
                    val p = st.nextToken().toInt()
                    val info = cache[p]!!
                    cache.remove(p)
                    categories[info.category]!!.remove(info)
                    nonClassifications.remove(info)
                }
            }
        }
        println(sb.toString())
    }

    private fun add(p: Int, l: Int, c: Int) {
        val info = Info(p, l, c)
        cache[p] = info
        val value = if (categories[c] == null) TreeSet<Info>() else categories[c]
        value!!.add(info)
        categories[c] = value
        nonClassifications.add(info)
    }

}

fun main() {
    BOJ21944().solve()
}