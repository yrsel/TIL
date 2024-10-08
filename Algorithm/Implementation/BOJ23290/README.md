## 백준 23290 마법사 상어와 복제

문제 링크 : [마법사 상어와 복제](https://www.acmicpc.net/problem/23290)

### 문제 조건

- 마법사 상어는 복제 마법을 배웠고, 4*4 크기의 격자에서 연습하려고 한다.
- (r,c)는 r행 c열을 의미
- 격자의 가장 왼쪽 윗 칸은 (1,1)이고, 가장 오른쪽 아랫 칸은 (4,4)이다.
- 격자에는 물고기 M마리가 있다.
    - 각 물고기는 격자의 칸 하나에 들어가 있으며, 이동 방향을 가지고 있다.
    - 이동 방향은 8방향 중 하나이다.(상하좌우,대각선)
- 마법사 상어도 연습을 위해 격자 한 칸에 들어가있다.
- 둘 이상의 물고기가 같은 칸에 있을 수도 있으며, 마법사 상어와 물고기가 같은 칸에 있을 수도 있다.
- 상어의 마법 연습 한 번은 다음과 같은 작업이 순차적으로 이루어진다.
    - 상어가 모든 물고기에게 복제 마법을 시전한다. 복제 마법은 시간이 걸리기 때문에, 5번 과정에서 물고기가 복제되어 칸에 나타난다.
    - 모든 물고기가 한 칸 이동한다.
        - 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동 불가
        - 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전
            - 만약 이동할 수 있는 칸이 없다면 이동하지 않는다, 그 외의 경우 그 칸으로 이동한다.
    - 상어가 연속해서 3칸 이동한다. 상어는 현재 칸에서 상하좌우로 이동 가능
        - 연속해서 이동하는 칸 중 격자의 범위를 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다.
        - 연속해서 이동하는 중에 상어가 물고기가 있는 칸으로 이동하게 된다면, 그 칸에 있는 모든 물고기는 격자에서 제외한다.
        - 제외되는 모든 물고기는 물고기 냄새를 남긴다.
        - 가능한 이동 방법 중에서 제외되는 물고기의 수가 가장 많은 방법으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용
            - 사전 순 앞서는 방법 찾기
                - 방향을 정수로 변환 -> 상:1, 좌:2, 하:3, 우:4
                - 수를 이어 붙여 정수로 하나 만든다.
                - a < b 를 만족하면 A가 B보다 사전 순으로 앞선 것이다.
    - 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
    - 1 에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖게 된다.
- 격자에 있는 모든 물고기의 위치, 방향 정보와 상어의 위치, 그리고 연습 횟수 S가 주어진다.
- S번의 연습을 모두 마쳤을 때, 격자에 있는 물고기의 수를 구해보자.

- 물고기의 수 M
    - 1 <= M <= 10
- 격자 위에 있는 물고기의 수는 항상 1_000_000 이하인 입력만 주어진다.
- 상어가 마법을 연습한 횟수 S
    - 1 <= S <= 100
- 물고기의 위치 x,y
    - 1 <= x,y <= 4
- 물고기의 방향 d
    - 1 <= d <= 8
- 두 물고기 또는 물고기와 상어가 같은 칸에 있을 수도 있다.

### 접근 방향

- fish dir -> 0 ~ 7 (왼,왼대각위,위,오대각위,오,오대각아래,아래,왼대각아래)
- shark dir -> 0 ~ 3 (상,좌,하,우)
- 물고기의 정보를 저장할 자료구조 - Fish(val r:Int, val c:Int, val dir:Int)
- map -> Array<Array<MutableMap<Int,Int>>> 형태로 하나의 격자에 여러 개의 물고기가 존재할 수 있게 정보를 저장 (방향정보와 동일한 방향인 물고기 수)
- map을 순회하면서 모든 물고기가 한 칸 이동하기 전에 복제마법을 시전할 물고기들 정보 list에 add
- 물고기 냄새에 대한 정보 int[4][4] 로 관리
- 모든 물고기 현재 방향부터 45도 회전하며 이동가능한 위치 찾고 있다면 이동
- 상어 물고기는 3칸 이동 경우의 수 모두 탐색하며 가장 많은 물고기를 없애는 방법 찾기
    - 3칸 이동하는 경우의 수를 미리 구해놓고 내림차순으로 정렬하고 가장 많거나 같은 방법으로 물고기를 없애는 방향을 저장 후, 64가지 방법을 모두 탐색해보고 이동
- 물고기 냄새에 대한 정보 0보다 크면 -1 해주기
- 물고기를 제거하는 곳에만 냄새를 남겨야 한다. <- 이 부분을 상어 지나간 곳에 모두 남겨서 시간을 허비했다.
- 복제 마법을 시전할 대상 물고기들을 생성