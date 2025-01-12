package Algorithm.BinarySearch.BOJ20442

class BOJ20442 {
    fun solve() = with(System.`in`.bufferedReader()) {
        val input = readLine()

        if (input.all { it == 'K' }) println(0)
        else {
            var rCount = input.count { it == 'R' }
            var max = rCount

            var leftKCount = 0
            var left = 0
            var rightKCount = 0
            var right = input.length - 1
            while (left <= right) {
                if (leftKCount <= rightKCount) {
                    if (input[left++] == 'K') {
                        leftKCount++
                    } else {
                        max = Math.max(max, Math.min(rightKCount, leftKCount) * 2 + rCount)
                        if (--rCount == 0) break
                    }
                } else {
                    if (input[right--] == 'K') {
                        rightKCount++
                    } else {
                        max = Math.max(max, Math.min(rightKCount, leftKCount) * 2 + rCount)
                        if (--rCount == 0) break
                    }
                }
            }
            println(max)
        }
    }
}

fun main() {
    BOJ20442().solve()
}