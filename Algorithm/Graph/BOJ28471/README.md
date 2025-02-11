## 백준 28471 W키가 빠진 성원이

문제 링크 : [W키가 빠진 성원이](https://www.acmicpc.net/problem/28471)

### 문제 조건

- 성원이는 게임을 너무 열심히 한 나머지 키보드의 W키가 빠져버리게 되었다.
- Q,W,E,A,D,Z,X,C 키를 이용해 8 방향으로 캐릭터를 움직일 수 있었다.
- W키를 제외한 상태인 7방향의 키 조작을 통해 목적지에 도달하는 것이 목표이다.
- 게임 시작하기 전에 빈 공간 중 어느 지점에 캐릭터를 둘 지 결정할 수 있다.
    - 단, 벽이나 목적지 위에는 캐릭터를 둘 수 없다.
    - 목적지는 단 한 개만 존재한다.
- 성원이가 목적지에 도달할 수 있는 시작 지점의 개수를 구하자

- 1 <= N <= 2,000

### 문제 접근

- F 도착 위치에서 도착할 수 있는 모든 칸으로 이동
- 도착할때는 X로 내려오지만 도착위치에서 출발할때는 W로 이동하면서 탐색
    - W키가 빠진 게 아닌 X키가 빠진 것으로 탐색