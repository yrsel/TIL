## 백준 10164 격자상의 경로

문제 링크 : [격자상의 경로](https://www.acmicpc.net/problem/10164)

### 문제 조건

- 행의 수가 N이고 열의 수가 M인 격자의 각 칸에 1부터 N\*M까지의 번호가 첫 행부터 시작하여 차례로 부여되어 있다.
- 격자의 어떤 칸은 O 표시가 되어 있다. (단, 1번 칸과 N*M번 칸은 O표시가 되어 있지 않다., O 표시는 최대 1개이다. 즉, O 표시가 없을 수도 있다.)
- 조건 1 : 로봇은 한 번에 오른쪽에 인접한 칸 또는 아래에 인접한 칸으로만 이동할 수 있다.
- 조건 2 : 격자에 O로 표시된 칸이 있는 경우엔 로봇은 그 칸을 반드시 지나가야 한다.

- N, M
    - 1 <= N, M <= 15
- O 표시된 칸의 번호를 나타내는 정수 K
    - K = 0 || 1 < K < N\*M / K=0 이라면 O 표시된 칸이 없음을 의미한다.
- N, M이 동시에 1인 경우는 없다.

### 문제 접근

- 좌표 r -> 숫자/C(열)
- 좌표 c -> 숫자%C(열)
- 만약 O 표시가 존재한다면 O의 r,c보다 작은 r,c에서 O 표시에 도달할 수 있는 경우의 수를 구한다.
- 구한 모든 경우의 수에서 R,C에 도달할 수 있는 경우의 수를 구한다.
- 오른쪽 또는 아래로만 이동 가능하므로 현재 위치에 도달할 수 있는 경우의수는 (r-1,c) 와 (r,c-1) 의 이동 경우의수를 더한 값이다.