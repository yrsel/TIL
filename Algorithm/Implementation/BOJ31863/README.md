## 백준 31863 내진 설계

문제 링크 : [내진 설계](https://www.acmicpc.net/problem/31863)

### 문제 조건

- 지진이 발생했다. 지진이 발생한 진원지는 N*M 격자 모양의 지역 중 한 곳이다.
- 진원지에서 발생한 지진을 본진, 건물이 무너졌을 때 발생하는 약한 지진을 여진이라고 하자.
- 본진은 진원지를 기준으로 상하좌우 각 방향으로 2칸까지 뻗어가며, 여진은 상하좌우 1칸까지 뻗어나간다.
    - 본진과 여진은 건물에 영향을 준다.
- 내진 설계가 되어 있지 않은 건물은 지진이 도달한 즉시 무너지지만, 내진 설계가 되어 있는 건물은 지진이 2번 도달하면 무너진다.
- 본진과 여진이 뻗어가는 도중 지진 방파제를 만나거나 격자 모양의 지역 밖으로 나가면 더 이상 뻗어나가지 않는다.
- 빠른 재해 복구를 위해 지진의 피해를 확인하고자 한다. 지진으로 인해 무너진 건물의 개수와 무너지지 않은 건물의 개수를 구해보자.

- 세로, 가로 N, M
    - 2 <= N,M <= 1,000
- @: 진원지 ( 1개만 주어진다 )
- .: 일반 도로
- *: 내진 설계가 되어있지 않은 건물
- #: 내진 설계가 되어있는 건물
- |: 방파제

### 접근 방향

- 입력 받을 때, 모든 건물의 개수 체크
- 일반 도로 -> 0, 진원지 위치 저장, 내진 설계 되어있지 않은 건물 -> 1, 내진 설계되어 있는 건물 -> 2, 방파제 -> -1
- 진원지 위치에서 시작하여 -1 이라면 탐색 중지, 0이라면 진행, 0보다 큰 수라면 1 감소 후 여진 발생 여부 체크 