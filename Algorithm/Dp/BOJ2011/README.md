## 백준 2011 암호코드

문제 링크 : [암호코드](https://www.acmicpc.net/problem/2011)

### 문제 조건

- A -> 1 , B-> 2 , ... Z -> 26 으로 치환
- 영어를 숫자로 변경했을 때, 경우의 수가 여럿 존재
- 몇개의 경우의 수가 존재하는 지 구하기

- 입력: 5,000자리 이하의 숫자로 된 암호
- 출력: 나올 수 있는 가짓수, 정답이 매우 클 수 있으니 1_000_000 으로 나눈 나머지 출력
- 암호를 해석할 수 없는 경우 0 출력

### 문제 접근

- 문제 점화식 찾기
- 직접 맨 앞자리부터 하나하나 늘려보면서 찾기
- dp[] -> 0인덱스부터 length-1 인덱스까지 순회하면서 값을 메모해놓을 배열
- arr[] -> 입력으로 주어진 값
    - dp[n] = dp[n-1] (이전 경우의 수들에 현재원소만 추가하는 경우의 수) + 이전경우의 수에서 마지막원소가 두 자리 수가 아니면서 (마지막으로 추가된 원소(i번째 원소에
      해당한다면,arr[i-1]) * 10+현재원소)의 값이 10보다 크거나 같으면서 26보다 작거나 같은 경우의 수
    - 이전 경우의 수들 중 마지막원소가 두자리 수라면, 한 자리를 더 추가하면 세자리수가 되므로 -> 1~26에 해당하는 알파벳이 될 수 없다.
    - 이전 경우의 수들 중 마지막원소 * 10 + 현재 원소가 26보다 크다면 가장 큰 알파벳인 Z를 넘어서기에 추가할 수 없다.
    - 10보다 작을 경우 앞의 원소가 0인 경우이니 0은 알파벳에 해당하지 않으므로 제외

### 에러 코드 

- 모든 게시판 반례, 생각나는 반례들 적용해봐도 정상 출력되는데 40%에서 계속 틀렸습니다 나오는데 반례를 못찾겠다...

```kotlin
package Algorithm.Dp.BOJ2011

class BOJ2011 {

    private val MOD = 1_000_000

    fun run() {
        val arr = readln().toCharArray().map { it.digitToInt() }.toIntArray()
        val dp = IntArray(arr.size)

        if (isZero(arr)) return

        dp[0] = 1
        for (i in 1..<arr.size) {
            val curLastCandidate = arr[i - 1] * 10 + arr[i] // 앞의 수와의 결합
            if (i == 1) {
                if (arr[i] != 0 && curLastCandidate <= 26) { // 현재 숫자가 0이 아니면서 26보다 작으면 2가지 경우 가능
                    dp[1] = 2
                } else { // 현재 숫자가 0이거나 26보다 큰 경우
                    dp[1] = 1
                }
            } else {
                if (arr[i] == 0) { // 현재숫자가 0일 경우
                    if (arr[i - 1] in 1..2) {
                        dp[i] = dp[i - 2] // 두 단계 전에 curLastCandidate 원소를 붙이는 경우
                    } else { // 불가능한 경우
                        println(0)
                        return
                    }
                } else { // 현재 숫자가 0이 아닌 경우
                    dp[i] += dp[i - 1]   // 이전 경우의 수에서 현재 수 마지막으로 추가할 수 있는 경우의 수
                    if (curLastCandidate in 10..26) { // 마지막 수를 더해 알파벳으로 추가할 수 있다면
                        dp[i] = (dp[i] % MOD + dp[i - 2] % MOD) % MOD
                    }
                }
            }
        }

        println(dp[arr.size - 1] % MOD)
    }

    private fun isZero(arr: IntArray): Boolean {
        val size = arr.size
        if (arr[0] == 0) { // 첫번째 원소가 0 인경우
            println(0)
            return true
        }
        if (size >= 2) { // 마지막 원소가 0이면서 마지막 두 수가 26보다 크면 알파벳을 완성시킬 수 없다.
            if ((arr[size - 2] != 1 && arr[size - 2] != 2) && arr[size - 1] == 0) {
                println(0)
                return true
            }
        }
        return false
    }
}

fun main() {
    BOJ2011().run()
}

```