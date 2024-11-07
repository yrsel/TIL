package Algorithm.Graph.BOJ13168

import java.io.BufferedReader
import java.util.*

class BOJ13168 {
    private var N = 0
    private lateinit var st: StringTokenizer
    private lateinit var mapper: HashMap<String, Int>
    private lateinit var channel: MutableList<String>
    private lateinit var withTicketMap: Array<IntArray>
    private lateinit var map: Array<IntArray>

    fun solve() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine(), " ")
        N = st.nextToken().toInt()
        val ticketCost = st.nextToken().toInt()

        initMapper(N)
        initChannel(readLine().toInt())
        initMaps(readLine().toInt())
        initMinCost()

        println(compareCost(ticketCost))
        close()
    }

    private fun BufferedReader.initMapper(N: Int) {
        mapper = hashMapOf()
        st = StringTokenizer(readLine(), " ")
        repeat(N) { i -> mapper[st.nextToken()] = i }
    }


    private fun BufferedReader.initChannel(M: Int) {
        channel = mutableListOf()
        st = StringTokenizer(readLine(), " ")
        repeat(M) { channel.add(st.nextToken()) }
    }

    private fun BufferedReader.initMaps(K: Int) {
        withTicketMap = Array(N) { IntArray(N) { 987_654_321 } }
        map = Array(N) { IntArray(N) { 987_654_321 } }

        repeat(K) {
            st = StringTokenizer(readLine(), " ")
            val vehicle = st.nextToken()
            val from = mapper[st.nextToken()]!!
            val to = mapper[st.nextToken()]!!
            val cost = st.nextToken().toInt() * 2
            map[from][to] = map[from][to].coerceAtMost(cost)
            map[to][from] = map[from][to]
            withTicketMap[from][to] = withTicketMap[from][to].coerceAtMost(getDiscountCost(vehicle, cost))
            withTicketMap[to][from] = withTicketMap[from][to]
        }
    }

    private fun getDiscountCost(vehicle: String, cost: Int): Int {
        return when (vehicle) {
            "Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun" -> 0
            "S-Train", "V-Train" -> cost / 2
            else -> cost
        }
    }

    private fun initMinCost() {
        for (k in 0..<N) { // 경
            for (i in 0..<N) { // 출
                if (i == k) continue
                for (j in 0..<N) { // 도
                    if (j == k || j == i) continue
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j]
                    }
                    if (withTicketMap[i][j] > withTicketMap[i][k] + withTicketMap[k][j]) {
                        withTicketMap[i][j] = withTicketMap[i][k] + withTicketMap[k][j]
                    }
                }
            }
        }
    }

    private fun compareCost(ticketCost: Int): String {
        var withTicketCost = ticketCost * 2
        var noTicketCost = 0
        for (i in 1..<channel.size) {
            val from = mapper[channel[i - 1]]!!
            val to = mapper[channel[i]]!!
            noTicketCost += map[from][to]
            withTicketCost += withTicketMap[from][to]
        }
        if (noTicketCost <= withTicketCost) return "No"
        return "Yes"
    }
}

fun main() {
    BOJ13168().solve()
}