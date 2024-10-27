## 백준 1025 제곱수 찾기

문제 링크 : [제곱수 찾기](https://www.acmicpc.net/problem/1025)

### 문제 조건

- N행 M열의 표 A가 있고, 표의 각 칸에는 숫자가 하나씩 적혀있다.
- 연두는 서로 다른 1개 이상의 칸을 선택하려고 하는데, 행의 번호가 선택한 순서대로 등차수열을 이루고 있어야 하고, 열의 번호도 선택한 순서대로 등차수열을 이루고 있어야 한다.
- 이렇게 선택한 칸에 적힌 수를 순서대로 이어붙이면 정수를 하나 만들 수 있다.
- 연두가 만들 수 있는 정수 중에서 가장 큰 완전 제곱수를 구해보자.
- 완전 제곱수란 어떤 정수를 제곱한 수이다.

- 행,열의 크기 (N,M)
    - 1 <= 행,열의 크기 <= 9
- 표에 적힌 숫자는 0보다 크거나 같고, 9보다 작거나 같다.
- 완전 제곱수를 만들 수 없는 경우에는 -1 출력한다.

### 문제 접근

- 4중 for문으로 모든 경우를 탐색
    - 바깥 for문 2개 시작하는 r,c의 위치
    - 안쪽 for문 2개 r에 더하거나 뺄 숫자 , c에 더하거나 뺄 숫자

### 매달린 코드 
```kotlin
      package Algorithm.BruteForce.BOJ1025
      
      import java.lang.Math.max
      import java.lang.Math.sqrt
      
      class BOJ1025 {
      
        private lateinit var map: Array<IntArray>
        private var R = 0
        private var C = 0
        private var result = -1
      
        fun solve() = with(System.`in`.bufferedReader()) {
          val (n, m) = readLine().split(" ").map { it.toInt() }
      
          R = n; C = m
          map = Array(R) { IntArray(C) }
          for (i in 0..<R) {
            val str = readLine()
            for (j in 0..<C) {
              map[i][j] = str[j].digitToInt()
              if (map[i][j] == 9 || map[i][j] == 4 || map[i][j] == 1) result = max(result, map[i][j])
            }
          }
      
          for (r in 0..<R) {
            for (c in 0..<C) {
              for (rPoint in 0..<R) {
                for (cPoint in 0..<C) {
                  if (rPoint == 0 && cPoint == 0) continue
                  if (!isOut(r + rPoint, c + cPoint)) search(r, c, rPoint, cPoint, map[r][c].toString(), true)
                  if (!isOut(r + rPoint, c - cPoint)) search(r, c, rPoint, cPoint, map[r][c].toString(), false)
                }
              }
            }
          }
          println(result)
        }
      
        private fun search(r: Int, c: Int, rPoint: Int, cPoint: Int, number: String, sign: Boolean) {
          val nr = r + rPoint
          val nc = if (sign) c + cPoint else c - cPoint
      
          val curNumber = number.toInt()
          if (isSatisfied(curNumber)) result = max(result, curNumber)
      
          val reversedNumber = number.reversed().toInt()
          if (isSatisfied(reversedNumber)) result = max(result, reversedNumber)
      
          if (isOut(nr, nc)) return
      
          search(nr, nc, rPoint, cPoint, number + map[nr][nc], sign)
        }
      
        private fun isSatisfied(number: Int): Boolean {
          val sqrtNumber = sqrt(number.toDouble()).toInt()
          return sqrtNumber * sqrtNumber == number
        }
      
        private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= R || c >= C
      }
      
      fun main() {
        BOJ1025().solve()
      }

```

- 이상없다고 생각하고 맞왜틀 반복하면서 하나씩 수정해보면서 98퍼센트에서 계속 틀렸다.. 
- 틀린것에는 이유가 있으니까 고집부리지 말고 구현한 코드를 놓아주고 다른 방향으로 접근하는 판단을 다시 한 번 느꼈다.
- 접근 방향 바꿔서 해결한 다음 다시 위 코드를 보니, 가장 윗쪽 행부터 탐색을 시작하며 작은 열부터 탐색을 진행했다.
  - 위에서 아래로 내려가는 숫자들은 물론, reversed 하여 아래에서 위로 올라오는 경우까지 처리했다고 생각했었는데 아래에서부터 올라오는 숫자들 일부를 탐색하지 못했고
    공차에 따라 위에서 아래로 내려오는 코드가 아래에서 위로 올라가는 경우에만 만족하는 경우 전부를 만족시키지 못했다.
- 모든 정점이 방향 전부를 탐색하게 변경해도 실패 ...... 후
