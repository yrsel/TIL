package Algorithm.Implementation.BOJ20665

import java.util.StringTokenizer

class BOJ20665 {

    private data class Schedule(private val _from: String, private val _to: String) : Comparable<Schedule> {
        val from = Time(_from.substring(0, 2).toInt(), _from.substring(2).toInt())
        val to = Time(_to.substring(0, 2).toInt(), _to.substring(2).toInt())
        val useTime = from.calculate(to)

        override fun compareTo(other: Schedule): Int {
            return if (this.from == other.from) useTime.compareTo(other.useTime)
            else this.from.compareTo(other.from)
        }
    }

    private data class Time(val hour: Int, val minute: Int) : Comparable<Time> {

        fun isExpired(compareTime: Time): Boolean {
            if (hour < compareTime.hour) return true
            if (hour == compareTime.hour && minute <= compareTime.minute) return true
            return false
        }

        fun calculate(toTime: Time): Int {
            var time = 0
            if (toTime.minute >= minute) {
                time += toTime.minute - minute
                time += (toTime.hour - hour) * 60
            } else {
                time += toTime.minute + 60 - minute
                time += (toTime.hour - 1 - hour) * 60
            }
            return time
        }

        override fun compareTo(other: Time): Int {
            return if (hour == other.hour) minute.compareTo(other.minute)
            else hour.compareTo(other.hour)
        }
    }

    fun solve() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val N = st.nextToken().toInt()
        val T = st.nextToken().toInt()
        val P = st.nextToken().toInt()
        val schedules = mutableListOf<Schedule>()
        repeat(T) {
            st = StringTokenizer(readLine(), " ")
            schedules.add(Schedule(st.nextToken(), st.nextToken()))
        }
        schedules.sort()

        var status = Array<Time?>(N + 1) { null }
        val curStatusIdx = mutableListOf<Int>()
        var result = 720
        var pos = 1
        schedules.forEach { comp ->
            status = status.mapIndexed { index: Int, time: Time? -> // 현재 시간 기준으로 존재하는 자리만 남겨두기
                if (time != null && time.isExpired(comp.from)) {
                    curStatusIdx.remove(index)
                    null
                } else {
                    time
                }
            }.toTypedArray()

            if (curStatusIdx.isEmpty()) { // 모든 좌석 이용가능 -> 1번 자리
                pos = 1
            } else {
                curStatusIdx.sort()
                if (curStatusIdx.size == 1) { // 좌석 중 먼 거리를 선택 (작은 번호 우선선택이므로 1번 좌석에 등호 포함 )
                    pos = if (curStatusIdx.first() - 1 >= N - curStatusIdx.first()) 1
                    else N
                } else {
                    var cur = curStatusIdx[0] // 가장 앞 인덱스 좌석
                    var maxDistance = curStatusIdx[0] - 1 // 가장 앞 인덱스 좌석

                    curStatusIdx.forEachIndexed { index, it -> // 현재 존재하는 좌석의 인덱스 번호를 기반으로 가장 먼 거리 체크
                        if (index != 0) {
                            if ((it - cur) / 2 > maxDistance) {
                                maxDistance = (it - cur) / 2
                                pos = cur + (it - cur) / 2
                            }
                            cur = it
                        }
                    }

                    if (N - curStatusIdx.last() > maxDistance) { // 마지막 인덱스가 N번이 아닐수도 있으니 체크
                        pos = N
                    }
                }
            }

            curStatusIdx.add(pos) // 갱신
            status[pos] = comp.to // 갱신
            if (pos == P) result -= comp.useTime //  갱신
            pos = 1 // 필요없으나 혹시 모르니 초기화 진행
        }
        println(result)
    }
}

fun main() {
    BOJ20665().solve()
}