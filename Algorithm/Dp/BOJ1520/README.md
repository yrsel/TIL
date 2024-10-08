## 백준 1520 내리막길

문제 링크 : [내리막길](https://www.acmicpc.net/problem/1520)

### 문제 조건

- 직사각형 모양의 지도가 존재 ( 칸마다 한 지점의 높이가 쓰여 있다 )
  - 상하좌우 이웃한 곳으로 이동 가능
- 제일 왼쪽 위칸에서 시작해서 제일 오른쪽 아래칸으로 이동하려고 한다.
- 이동은 항상 높은 지점에서 더 낮은 지점으로만 이동 가능
- 위 조건을 지키는 경로의 개수를 구하는 프로그램 작성

- 세로의 크기 M, 가로의 크기 N 
  - 1 <= M,N <= 500
- 각 지점의 높이 H
  - 1 <= H <= 10_000
- 모든 입력에 대해서 이동 가능한 경로의 수 H는 10억 이하의 음이 아닌 정수이다.

### 문제 접근
- 상하좌우로 이동할 수 있는 모든 경우를 찾아보자.
- 이동할 수 있는 위치에서 가장 높이가 높은 지점을 우선 순위 큐에서 꺼내서 이동 가능한 위치의 경우의 수를 증가시켜주기
- 이미 큐에 한번 넣은 자리라면 다시 큐에 넣지 않고 이동가능한 경우의 수만 더해서 진행 