package Algorithm.BinarySearch.BOJ2143

import java.io.StreamTokenizer
import java.util.*

class BOJ2143 {
    private var T = 0
    private lateinit var A: IntArray
    private lateinit var B: IntArray
    private var result = 0L
    fun run() {
        setInput()

        val aMap = initMap(A)
        val bMap = initMap(B)

        if (aMap.size < bMap.size) {
            calculate(aMap, bMap)
        } else {
            calculate(bMap, aMap)
        }

        println(result)
    }

    private fun setInput() {
        T = readln().toInt()
        A = initArr(initPrefixArr(readln().toInt())) // 누적합 배열을 구하고 나서 구간 배열 모든 경우의 수 세팅 해주기
        B = initArr(initPrefixArr(readln().toInt()))
    }

    private fun initPrefixArr(size: Int): IntArray { // 누적합 배열 세팅
        val arr = IntArray(size)
        val st = StringTokenizer(readln(), " ")
        for (i in 0..<size) {
            if (i == 0) arr[i] = st.nextToken().toInt()
            else arr[i] = arr[i - 1] + st.nextToken().toInt()
        }
        return arr
    }

    private fun initArr(prefixArr: IntArray): IntArray { // 구간 합 모든 경우의 수 세팅
        val s = prefixArr.size
        val size = s * (s + 1) / 2
        val arr = IntArray(size)
        var idx = 0
        for (i in 0..<s) {
            for (j in i..<s) {
                if (i == 0) arr[idx++] = prefixArr[j]
                else arr[idx++] = prefixArr[j] - prefixArr[i - 1]
            }
        }
        arr.sort()
        return arr
    }

    private fun initMap(arr: IntArray): HashMap<Int, Int> {
        val map = hashMapOf<Int, Int>()
        for (i in 0..<arr.size) {
            map[arr[i]] = map.getOrDefault(arr[i], 0) + 1
        }
        return map
    }

    private fun calculate(map: HashMap<Int, Int>, compMap: HashMap<Int, Int>) {
        for (element in map) {
            val findKey = T - element.key
            if (compMap.containsKey(findKey)) {
                result += element.value.toLong() * compMap[findKey]!!
            }
        }
    }
}

val st = StreamTokenizer(System.`in`.bufferedReader())

fun main() {
    BOJ2143().run()
}
