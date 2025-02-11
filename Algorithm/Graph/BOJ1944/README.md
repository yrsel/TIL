## 백준 1944 복제 로봇

문제 링크 : [복제 로봇](https://www.acmicpc.net/problem/1944)

### 문제 조건

- 복제 로봇을 이용하여 미로에 흩어진 열쇠들을 모두 찾기
- 열쇠가 있는 곳들과 로봇이 출발하는 위치에 로봇이 복제할 수 있는 장치를 장착해두었다.
- N*N 정사각형 미로와 M개의 흩어진 열쇠의 위치, 로봇의 시작 위치가 주어져 있을 때, 모든 열쇠를 찾으면서 로봇이 움직이는 횟수의 합을 최소로 하는 프로그램 작성
- 로봇은 상하좌우로 이동가능, 로봇이 열쇠에 있는 위치에 도달했을 때 열쇠를 찾은 것으로 한다.
- 하나의 칸에 동시에 여러 개의 로봇이 위치할 수 있으며, 로봇이 한 번 지나간 자리라도 다른 로봇 또는 자기 자신이 다시 지나갈 수 있다.
- 복제에는 시간이 들지 않으며, 로봇이 움직이는 횟수의 합은 분열된 로봇 각각이 움직인 횟수의 총 합

- 미로의 크기 N
  - 4 <= N <= 50
- 열쇠의 개수 M
  - 1 <= M <= 250
- 미로는 1과 0, 그리고 S와 K로 구성
  - 1: 벽, 0: 이동가능, S: 로봇 시작위치, K: 열쇠 위치
  - S는 1개, K는 M개가 주어지고, S와 K에서만 복제를 할 수 있다.
- 모든 로봇이 움직인 횟수의 총 합을 출력, 모든 열쇠를 찾는 것이 불가능하면 -1 출력 

### 문제 접근
- 로봇의 시작위치와 모든 열쇠와의 최소 거리 저장
  - 하나의 열쇠에서 다른 모든 열쇠로 가는 최소 거리 저장
  - 50*50 (최대 지도 크기) * 251(최대 열쇠개수 + 로봇) = 충분한 시간복잡도
  - Point(시작지점, 도착지점, 거리) 를 오름차순으로 정렬한 pq 에 구한 모든 위치 정보들을 저장
- pq에서 원소를 하나씩 꺼내며 크루스칼 알고리즘을 적용해 이동가능한 최소의 거리를 찾는다.
  - pq가 다 비워졌는데 M개의 간선을 선택하지 못했다면 -1 출력 ( [M개의 열쇠 + 로봇] 연결하기 위해 M개의 간선이 필요 )