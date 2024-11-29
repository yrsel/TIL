package Algorithm.Graph.BOJ9489

import java.util.*

class BOJ9489 {
    fun solve() = with(System.`in`.bufferedReader()) {
        println(buildString {
            while (true) {
                val (n, k) = readLine().split(" ").map { it.toInt() }
                val cache = IntArray(n)
                val parents = IntArray(n)
                val levels = IntArray(n)
                if (n == 0 && k == 0) break
                var prevNodeNumber = -1
                var parentNumber = -1
                var levelIdx = -1
                val st = StringTokenizer(readLine(), " ")
                repeat(n) { cacheIdx ->
                    val number = st.nextToken().toInt()
                    cache[cacheIdx] = number

                    if (prevNodeNumber == -1) { // 루트 노드
                        prevNodeNumber = number
                        parents[cacheIdx] = parentNumber
                        levels[0] = 0
                    } else {
                        if (prevNodeNumber + 1 != number) { // 다음 부모노드를 찾아야 한다
                            levels[cacheIdx] = levels[++levelIdx] + 1
                            parentNumber = cache[levelIdx]
                            prevNodeNumber = number
                            parents[cacheIdx] = parentNumber
                        } else { // 현재 부모 노드를 그대로 사용
                            levels[cacheIdx] = levels[cacheIdx - 1]
                            parents[cacheIdx] = parentNumber
                            prevNodeNumber = number
                        }
                    }
                }

                val kCacheIdx = cache.indexOf(k)
                val parent = parents[kCacheIdx]
                if (parent == -1) appendLine(0)
                else {
                    val parentOfParent = parents[cache.indexOf(parent)]
                    if (parentOfParent == -1) appendLine(0)
                    else {
                        var cnt = 0
                        val from = levels.indexOf(levels[kCacheIdx])
                        val to = levels.indexOfLast { it == levels[kCacheIdx] }
                        for (i in from..to) {
                            if (parents[i] != parent && parents[cache.indexOf(parents[i])] == parentOfParent) {
                                cnt++
                            }
                        }
                        appendLine(cnt)
                    }
                }
            }
        })
    }
}

fun main() {
    BOJ9489().solve()
}