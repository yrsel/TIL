## 백준 9202 Boggle

문제 링크 : [Boggle](https://www.acmicpc.net/problem/9202)

### 문제 조건

- 주사위로 이루어진 4 * 4 크기의 그리드에서 최대한 많은 단어를 찾는 게임
- 단어는 인접한 글자(가로,세로,대각선)를 이용해서 만들 수 있다.
- 한 주사위는 단어에 한 번만 사용할 수 있다. 단어는 게임 사전에 등재되어 있는 단어만 올바른 단어
- 1글자,2글자: 0점, 3글자,4글자: 1점, 5글자: 2점, 6글자: 3점, 7글자: 5점, 8글자 11점
- 얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 수를 구하는 프로그램을 작성

- 단어 사전에 들어있는 단어의 수 w
  - 1 < w < 300_000
- 단어
  - 최대 8글자, 알파벳 대문자로만 구성
- Boggle 보드(4*4) 개수 b
  - 1 < b < 30 
- 한 Boggle에서 같은 단어를 여러 번 찾은 경우에는 한 번만 찾은 것으로 세기
- 가장 긴 단어가 여러 개인 경우 사전순 앞서는 것을 출력

### 문제 접근
- 사전에 들어있는 단어들 - 트라이로 구현
- dfs로 완탐
- 처음에 헤맸던 부분 -> 한 주사위는 단어에 한 번만 사용할 수 있다. 
  - 4*4 배열에서 중복으로 문자를 사용해서 단어를 만드는 경우가 안되는 걸로 이해해서 dfs로 탐색 진행하던 중에 예시데이터가 안 맞아서 비교해보니 단어를 만들기만 하면 되는거였다.