## SW Expert Academy 5656 [모의 SW 역량테스트] 벽돌 깨기

문제
링크 : [[모의 SW 역량테스트] 벽돌 깨기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo&categoryId=AWXRQm6qfL0DFAUo&categoryType=CODE&problemTitle=%EC%97%AD%EB%9F%89&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

### 문제 조건

- 구슬을 쏘아 벽돌을 깨트리는 게임을 하려고 한다.
- 구슬은 N번만 쏠 수 있고, 벽돌들의 정보는 아래와 같이 W\*H 배열로 주어진다. (0은 빈 공간, 그 외 숫자는 벽돌)
- 규칙

1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
2. 벽돌은 숫자 1 ~ 9 로 표현되며, 구슬이 명중한 벽돌은 상하좌우 (벽돌에 적힌 숫자-1) 칸 만큼 같이 제거
3. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.

- N개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하려고 한다.
- 남은 벽돌의 개수를 구하여라.

- N
    - 1 <= N <= 4
- W
    - 2 <= W <= 12
- H
    - 2 <= H <= 15

### 문제 접근

- N 이 최대 4 , 경우의 수 열이 최대 12 이므로 구슬을 놓는 경우의 수의 최대는 12^4 의 경우의 수만큼 존재
- 완전탐색 진행
- 벽돌을 깨트릴 때는 터트릴 수 있는 범위를 모두 저장하고 없앤다 -> 열 하나씩 맨 아래부터 쌓이도록 다시 쌓아주기