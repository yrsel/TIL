## 백준 2143 두 배열의 합

문제 링크 : [두 배열의 합](https://www.acmicpc.net/problem/2143)

### 문제 조건

- 한 배열 A[1] ~ A[n]에 대해서, 부 배열은 A[i],A[i+1],...,A[j-1],A[j] 을 말한다.
- 부 배열의 합 : A[i] + ... + A[j]
- A의 부 배열의 합에 또다른 배열인 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하기

- 배열의 합을 더해서 구해야 할 값 T
    - -1_000_000_000 <= T <= 1_000_000_000
- A,B의 배열의 크기 n,m
    - 1 <= n,m <= 1_000
- 배열의 원소 크기 x
    - -1_000_000 < x < 1_000_000

- 가능한 경우가 한 가지도 없다면 0 출력

### 문제 접근

- 원소의 크기와 배열의 크기로 보았을 때 int 자료형으로 값 비교 가능
- A,B 구간의 합을 모두 계산 -> 2 * n(n+1)/2 
- A,B 정렬 최대 크기  -> 2 * 500_500 * log(500_500)
- A -> 투포인터로 left부터 현재원소 중복 개수 체크 
  - B -> 이분탐색 정답이 될 수 있는 lower, upper 인덱스 구하기 
  - answer += A 원소 개수 * B 원소 개수 
  - 4% 에서 계속 틀리고 반례를 못찾아서 A,B 구간 합 모두 계산한 배열을 순회하면서 hashMap에 카운팅
    - 둘 중 하나 hashmap 순회하면서 T-현재 키 값이 다른 맵의 키값에 존재하면 value*value 해주기 
    - 정답은 최대 50만 * 50만  -> long 자료형 이어야 한다.

### 다른 사람 코드 

- hashMap으로 입력받은 배열을 2중 for문으로 누적합에 카운팅
- 두번째 입력으로 받은 배열의 누적합을 진행해줄 때 hm[T-현재 누적합 값] 이 존재한다면 result에 value만큼 더해주기
- 훨씬 효율적인 코드 같다.. 왜 아이디어가 안 떠올랐을까...

### 이분탐색 틀린 코드
```kotlin
package Algorithm.BinarySearch.BOJ2143

import java.util.*

class BOJ2143 {
  private var T = 0
  private lateinit var A: IntArray
  private lateinit var B: IntArray
  private var result = 0L
  private lateinit var st: StringTokenizer
  fun run() {
    setInput()

    if (A.size > B.size) {
      search(A, B)
    } else {
      search(B, A)
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
    st = StringTokenizer(readln(), " ")
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

  private fun search(bsArr: IntArray, tpArr: IntArray) { // 이분탐색용, 투포인터용
    var left = 0
    var right = 0

    val size = tpArr.size
    while (right != size) {
      while (tpArr[left] == tpArr[right]) { // right 위치는 N-1 또는 다른 원소의 위치에 도달하면 종료 ( 원소 개수 : right - left )
        right++
        if (right == tpArr.size) break
      }

      val lowerIdx = bsLower(bsArr, T - tpArr[left])
      val upperIdx = bsUpper(bsArr, T - tpArr[left])
      result += (right - left) * (upperIdx - lowerIdx + 1L)
      left = right
    }
  }

  private fun bsLower(bsArr: IntArray, goal: Int): Int {
    var left = 0
    var right = bsArr.size - 1
    while (left < right) {
      val mid = (left + right) / 2
      if (bsArr[mid] >= goal) {
        right = mid - 1
      } else {
        left = mid + 1
      }
    }
    return left
  }

  private fun bsUpper(bsArr: IntArray, goal: Int): Int {
    var left = 0
    var right = bsArr.size - 1
    while (left <= right) {
      val mid = (left + right) / 2
      if (bsArr[mid] > goal) {
        right = mid - 1
      } else {
        left = mid + 1
      }
    }

    return right
  }
}

fun main() {
  BOJ2143().run()
}

```