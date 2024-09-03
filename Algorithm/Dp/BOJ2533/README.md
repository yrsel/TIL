## 백준 2533 사회망 서비스(SNS) 

문제 링크 : [사회망 서비스(SNS)](https://www.acmicpc.net/problem/2533)

### 문제 조건

- 사람 - 정점, 친구 여부 - 간선
- 새로운 아이디어를 먼저 받아들인 사람 : 얼리어답터
  - 얼리어답터가 아닌 사람은 자신의 모든 친구들이 얼리어답터 일 때만 아이디어를 받아들인다.
- 필요한 최소 얼리 어답터의 수를 구하기

- 정점의 개수 N
  - 2 <= N <= 1_000_000, 각 정점은 1부터 N까지

### 문제 접근

- 트리라는 조건이 있기에 N개의 정점일 때, N-1개의 간선이 존재
- 과거에 단순히 트리를 순회하면서 조건을 충족하는 경우의 최적을 구하려고 했다가 해결을 못하고 DP로 해결할 수 있다는 키워드만 있었다.
- 한참 후에 문제 해결을 위해 다시 도전
- 하나의 정점을 기준으로 얼리 어답터 일때와 아닐때를 기준으로 트리의 리프까지 dfs로 순회하여 최솟값 찾기
- 리프노드가 아닌 경우 
  - 만약 현재 노드가 얼리 어답터이면 자식 노드가 얼리 어답터일때 또는 얼리 어답터가 아닐 때 중 최솟값을 더하기
  - 만약 현재 노드가 얼리 어답터가 아니라면 자식 노드가 얼리 어답터일 때의 경우의 수를 더해주기


### 문제 상황
```kotlin
  package Algorithm.Dp.BOJ2533
  
  class BOJ2533 {
  
    private lateinit var connection: Array<MutableList<Int>>
    private lateinit var dp: Array<IntArray>
    private var N = 0
  
    fun run() {
      N = readln().toInt()
      connection = Array(N) { mutableListOf() }
      repeat(N - 1) {
        val (from, to) = readln().split(" ").map { it.toInt() - 1 }
        connection[from].add(to)
        connection[to].add(from)
      }
  
      dp = Array(2) { IntArray(N) } // 시작 정점의 얼리어답터 유무 [0][],[1][]
      dfs(0, 1)
      println(Math.min(dp[0][0], dp[1][0]))
    }
  
    private fun dfs(v: Int, flag: Int) {
      dp[0][v] = 0
      dp[1][v] = 1
  
      for (child in connection[v]) {
        if (flag.and(1 shl child) != 0) continue
        dfs(child, flag.or(1 shl child))
        dp[0][v] += dp[1][child]
        dp[1][v] += dp[0][child].coerceAtMost(dp[1][child])
      }
    }
  }
  
  fun main() {
    BOJ2533().run()
  }

```
- flag 변수로 쉬프트 연산을 통해 이미 방문했던 노드라면 continue 아니라면 리프노드까지 탐색
  - 틀렸습니다 발생.. 게시판 반례도 다 통과하고 어떤 반례가 있을지 고민해봤는데 해결이 안됐다.
  - flag 변수를 활용하는 대신 visited 전역변수로 탐색 하려는 노드가 parent 노드가 아닌 경우에만 탐색하도록 진행해서 정답을 받았다.
  