## 백준 17485 진우의 달 여행 (Large)

문제 링크 : [진우의 달 여행 (Large)](https://www.acmicpc.net/problem/17485)

### 문제 조건

- 지구와 우주사이를 N*M 행렬로 나타낼 수 있으며, 각 원소의 값은 우주선이 그 공간을 지날 때 소모되는 연료의 양
- 지구 -> 달로 가는 경우 우주선 이동가능한 방향
    - 왼쪽 아래, 아래, 오른쪽 아래
    - 같은 방향으로 2번 연속 움직일 수 없다.
- 연료를 최대한 아끼며 지구의 어느위치에서든 출발하여 달의 어느 위치든 착륙하는 것이 목표

- 행렬의 크기 N,M
    - 2 <= N,M <= 1_000
- 행렬의 원소값은 100 이하의 자연수

### 문제 접근
- 3차원 배열 dp로 문제 접근
- [3][R+1][C+2] 으로 배열 선언
  - 시작위치 0 인덱스에서 시작 , 범위 밖을 체크하지 않기 위해 C+2로 선언
  - [0][][] : 왼쪽 아래 방향으로 온 경우 체크  
  - [1][][] : 아래 방향으로 온 경우 체크 
  - [2][][] : 오른쪽 아래 방향으로 온 경우 체크  
    