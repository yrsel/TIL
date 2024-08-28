package Algorithm.BinarySearch.BOJ2295

class BOJ2295 {
    private val existCheck = mutableSetOf<Int>()
    fun run() {
        val N = readln().toInt()
        val arr = IntArray(N)
        for (i in 0..<N) {
            arr[i] = readln().toInt()
        }
        for (i in 0..<N) {
            for (j in i..<N) {
                existCheck.add(arr[i] + arr[j])
            }
        }

        arr.sort()

        var result = 0
        loop@ for (i in N - 1 downTo 0) { // 총합을 나타내는 arr[k]
            for (j in 0..i) { //  arr[z]
                if (existCheck.contains(arr[i] - arr[j])) {  // arr[k] - arr[z] 가 set 자료구조(두 수의 합)에 존재하는 지 판단
                    result = arr[i]
                    break@loop
                }
            }
        }

        println(result)
    }

}

fun main() {
    BOJ2295().run()
}