## 백준 5373 큐빙

문제 링크 : [큐빙](https://www.acmicpc.net/problem/5373)

### 문제 조건

- 루빅스 큐브는 3*3*3의 작은 정육면체로 이루어진 삼차원 퍼즐이다.
- 큐브는 각 면을 양방향으로 90도 만큼 돌릴 수 있도록 만들어졌다.
- 루빅스 큐브는 모두 플린 상태에서 시작한다.
- 색상 -> 윗 면 : w, 아랫 면 : y, 앞 면 : r, 뒷 면 : o, 왼쪽 면 : g, 오른쪽 면 : b
- 돌린 방법 -> 윗 면 : U, 아랫 면 : D, 앞 면 : F, 뒷 면 : B, 왼쪽 면 : L, 오른쪽 면 : R
- 돌린 방향 -> 그 면을 바라봤을 때, 시계 방향 : + , 반시계 방향 : -

- 테스트 케이스의 수 최대 100개
- 큐브 돌린 횟수 n
    - 1 <= n <= 1,000

### 접근 방향

- 왼,오,위,앞 -> 전체가 방향에 맞게 회전 , 뒤, 아래 -> 전체가 반대로 회전 (0,0 위치를 왼쪽,위쪽 끝에서 시작해버려서)
- 윗 면 회전 (윗 면은 전체가 방향대로 회전)
    - 시계 방향
        - 뒷 면 (0,0),(0,1),(0,2) -> 오른쪽 면 (0,2),(0,1),(0,0) 위치로
        - 오른쪽 면 (0,0),(0,1),(0,2) -> 앞 면 (0,0),(0,1),(0,2) 위치로
        - 앞 면 (0,0),(0,1),(0,2) -> 왼쪽 면 (0,0),(0,1),(0,2) 위치로
        - 왼쪽 면 (0,0),(0,1),(0,2) -> 뒷 면 (0,2),(0,1),(0,0) 위치로
    - 반시계 방향
        - 뒷 면 (0,0),(0,1),(0,2) -> 왼쪽 면 (0,2),(0,1),(0,0) 위치로
        - 왼쪽 면 (0,0),(0,1),(0,2) -> 앞 면 (0,0),(0,1),(0,2) 위치로
        - 앞 면 (0,0),(0,1),(0,2) -> 오른쪽 면 (0,0),(0,1),(0,2) 위치로
        - 오른쪽 면 (0,0),(0,1),(0,2) -> 뒷 면 (0,2),(0,1),(0,0) 위치로
- 아랫 면 회전 (아랫 면은 전체가 방향대로 회전)
    - 시계 방향 ( 윗면의 반시계 방향 + 행만 2로 변경 )
    - 반시계 방향 ( 윗면의 시계 방향 + 행만 2로 변경 )
- 오른쪽 면 회전 (오른쪽 면은 전체가 방향대로 회전)
    - 시계 방향
        - 윗 면 (0,2),(1,2),(2,2) -> 뒷 면 (2,2),(1,2),(0,2) 위치로
        - 뒷 면 (0,2),(1,2),(2,2) -> 아랫 면 (0,2),(1,2),(2,2) 위치로
        - 아랫 면 (0,2),(1,2),(2,2) -> 앞 면 (2,2),(1,2),(0,2) 위치로
        - 앞 면 (0,2),(1,2),(2,2) -> 윗 면 (0,2),(1,2),(2,2) 위치로
    - 반시계 방향
        - 윗 면 (0,2),(1,2),(2,2) -> 앞 면 (0,2),(1,2),(2,2) 위치로
        - 앞 면 (0,2),(1,2),(2,2) -> 아랫 면 (2,2),(1,2),(0,2) 위치로
        - 아랫 면 (0,2),(1,2),(2,2) -> 뒷 면 (0,2),(1,2),(2,2) 위치로
        - 뒷 면 (0,2),(1,2),(2,2) -> 윗 면 (2,2),(1,2),(0,2) 위치로
- 왼쪽 면 회전 (왼쪽 면은 전체가 방향대로 회전)
    - 시계 방향 ( 오른쪽 면의 반시계 방향 + 열만 0으로 변경 )
    - 반시계 방향 ( 오른쪽 면의 시계 방향 + 열만 0으로 변경 )
- 앞면 회전 (앞면은 전체가 방향대로 회전)
    - 시계 방향
        - 윗 면 (2,0),(2,1),(2,2) -> 오른쪽 면 (0,0),(1,0),(2,0) 위치로
        - 오른쪽 면 (0,0),(1,0),(2,0) -> 아랫 면 (2,2),(2,1),(2,0) 위치로
        - 아랫 면 (2,0),(2,1),(2,2) -> 왼쪽 면 (0,2),(1,2),(2,2) 위치로
        - 왼쪽 면 (0,2),(1,2),(2,2) -> 윗 면 (2,2),(2,1),(2,0) 위치로
    - 반시계 방향
        - 윗 면 (2,0),(2,1),(2,2) -> 왼쪽 면 (2,2),(1,2),(0,2) 위치로
        - 왼쪽 면 (0,2),(1,2),(2,2) -> 아랫 면 (2,0),(2,1),(2,2) 위치로
        - 아랫 면 (2,0),(2,1),(2,2) -> 오른쪽 면 (2,0),(1,0),(0,0) 위치로
        - 오른쪽 면 (0,0),(1,0),(2,0) -> 윗 면 (2,0),(2,1),(2,2) 위치로
- 뒷면 회전 (뒷면은 전체가 방향대로 회전)
    - 시계 방향
        - 윗 면 (0,0),(0,1),(0,2) -> 왼쪽 면 (2,0),(1,0),(0,0) 위치로
        - 왼쪽 면 (0,0),(1,0),(2,0) -> 아랫 면 (0,0),(0,1),(0,2) 위치로
        - 아랫 면 (0,0),(0,1),(0,2) -> 오른쪽 면 (2,2),(1,2),(0,2) 위치로
        - 오른쪽 면 (0,2),(1,2),(2,2) -> 윗 면 (0,0),(0,1),(0,2) 위치로
    - 반시계 방향
        - 윗 면 (0,0),(0,1),(0,2) -> 오른쪽 면 (0,2),(1,2),(2,2) 위치로
        - 오른쪽 면 (0,2),(1,2),(2,2) -> 아랫 면 (0,2),(0,1),(0,0) 위치로
        - 아랫 면 (0,0),(0,1),(0,2) -> 왼쪽 면 (0,0),(1,0),(2,0) 위치로
        - 왼쪽 면 (0,0),(1,0),(2,0) -> 윗 면 (0,2),(0,1),(0,0) 위치로

- 하드코딩으로 로직을 옮기다보니 옮겨 적는 과정에서 실수가 발생해 의도치않게 디버깅을 연습할 수 있어서 좋았다,,