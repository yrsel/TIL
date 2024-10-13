package Algorithm.Sort.BOJ14612

import java.util.*

class BOJ14612 {

    private data class Order(val tableNumber: Int, val time: Int)

    fun run() = with(System.`in`.bufferedReader()) {
        val result = StringBuilder()
        var st = StringTokenizer(readLine(), " ")
        val N = st.nextToken().toInt()
        val M = st.nextToken().toInt()
        val orders = mutableListOf<Order>()

        val sortCondition = kotlin.Comparator<Order> { o1, o2 ->
            if (o1.time == o2.time) o1.tableNumber.compareTo(o2.tableNumber) else o1.time.compareTo(o2.time)
        }

        repeat(N) {
            st = StringTokenizer(readLine(), " ")
            when (st.nextToken()) {
                "order" -> {
                    orders.add(Order(st.nextToken().toInt(), st.nextToken().toInt()))
                }

                "sort" -> {
                    orders.sortWith(sortCondition)
                }

                "complete" -> {
                    val tableNumber = st.nextToken().toInt()
                    val completeOrder = orders.first { it.tableNumber == tableNumber }
                    orders.remove(completeOrder)
                }
            }
            if (orders.isNotEmpty()) orders.forEach { result.append("${it.tableNumber} ") }
            else result.append("sleep")
            result.trim()
            result.appendLine()
        }
        println(result.toString())
    }
}

fun main() {
    BOJ14612().run()
}