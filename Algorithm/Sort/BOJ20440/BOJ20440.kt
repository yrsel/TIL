package Algorithm.Sort.BOJ20440

import java.util.*

class BOJ20440 {

    private data class Times(val time: Int, val isEnd: Boolean)
    private data class TimeResult(var from: Int = -1, var to: Int = -1, var count: Int = 0)

    private lateinit var st: StringTokenizer
    private val times = mutableListOf<Times>()

    fun run() {
        repeat(readln().toInt()) {
            st = StringTokenizer(readln(), " ")
            times.add(Times(st.nextToken().toInt(), false))
            times.add(Times(st.nextToken().toInt(), true))
        }

        times.sortWith { o1, o2 -> if (o1.time == o2.time) o1.isEnd.compareTo(o2.isEnd) else o2.time.compareTo(o1.time) }

        val result = TimeResult()
        var count = 0

        times.forEachIndexed { index, it ->
            if (it.isEnd) {
                count++
                if (result.count < count) {
                    result.to = it.time
                    result.count = count
                }
                if (result.count == count) {
                    if (index != 0 && !(times[index - 1].time == it.time && !times[index - 1].isEnd)) {
                        result.to = it.time
                    }
                }
            } else {
                count--
                if (count + 1 == result.count) {
                    result.from = it.time
                }
            }
        }

        println("${result.count}\n${result.from} ${result.to}")
    }
}

fun main() {
    BOJ20440().run()
}