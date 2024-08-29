package Algorithm.Implementation.PG가장많이받은선물

class Solution {

    private data class GiftInfo(
        var give: Int = 0, // 선물한 횟수
        var take: Int = 0, // 선물받은 횟수
        val info: HashMap<String, Int> = hashMapOf() // 현재 유저가 친구에게 선물한 횟수 정보
    )

    private val infos = hashMapOf<String, GiftInfo>() // 현재 유저 , 선물에 대한 정보
    private val counter = hashMapOf<String, Int>() // 정답을 위한 자료구조

    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        friends.forEach {
            infos[it] = GiftInfo()
        }

        gifts.forEach {
            val (from, to) = it.split(" ")
            infos[to]!!.take++
            val giveUserInfo = infos[from]!!
            giveUserInfo.give++
            val giveUserInfoMap = giveUserInfo.info
            giveUserInfoMap.customAdd(to)
        }

        for (i in 0..friends.size - 1) { // 프로그래머스에서는 코틀린 버전이 낮아 ..< 문법을 지원하지 않는다.
            val curFriends = friends[i]
            for (j in i + 1..friends.size - 1) {
                val compFriends = friends[j]
                if (infos[curFriends]!!.info[compFriends] != null ||
                    infos[compFriends]!!.info[curFriends] != null
                ) {
                    val cur = infos[curFriends]!!.info[compFriends] ?: 0
                    val comp = infos[compFriends]!!.info[curFriends] ?: 0
                    if (cur > comp) {
                        counter.customAdd(curFriends)
                    } else if (cur < comp) {
                        counter.customAdd(compFriends)
                    } else {
                        findBiggerIncrease(curFriends, compFriends)
                    }
                } else {
                    findBiggerIncrease(curFriends, compFriends)
                }
            }
        }

        var result = 0
        for (user in counter) {
            result = Math.max(result, user.value)
        }
        return result
    }

    private fun HashMap<String, Int>.customAdd(name: String) { // 확장함수로 작성하니 시간복잡도가 많이 떨어졌다.
        this[name] = this.getOrDefault(name, 0) + 1
    }

    private fun findBiggerIncrease(curFriends: String, compFriends: String) {
        val cur = infos[curFriends]!!.give - infos[curFriends]!!.take
        val comp = infos[compFriends]!!.give - infos[compFriends]!!.take
        if (cur > comp) {
            counter.customAdd(curFriends)
        } else if (cur < comp) {
            counter.customAdd(compFriends)
        }
    }
}