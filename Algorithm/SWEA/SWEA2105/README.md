## SW Expert Academy 2105 [모의 SW 역량테스트] 디저트 카페

문제
링크 : [[모의 SW 역량테스트] 디저트 카페](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu&categoryId=AV5VwAr6APYDFAWu&categoryType=CODE&problemTitle=%EC%97%AD%EB%9F%89&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

### 문제 조건

- 시간: 50개 테스트케이스 합쳐서 JAVA의 경우 3초
- 메모리: 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내

- 한 변의 길이가 N인 정사각형 모양을 가진 지역에 디저트 카페가 있다.
- 한 칸(카페)에는 디저트 카페에 파는 디저트 종류의 번호 적혀 있다.
- 카페들 사이에는 대각선 방향으로 이동할 수 있는 길이 존재한다.
- 대각선 방향으로 움직이고 사각형을 그리며 출발한 카페로 다시 돌아와야 한다.
- 카페 투어 중 같은 숫자의 디저트를 팔고 있는 카페가 존재하면 안된다.
- 하나의 정점만 선택하고 종료하는 것도 안된다.
- 왔던 길을 다시 돌아가는 것도 안된다.

- 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력하는 프로그램 작성
- 만약, 디저트를 먹을 수 없는 경우 -1 출력

- 카페가 존재할 수 있는 지역의 한 변의 길이 N
    - 4 <= N <= 20
- 디저트 종류의 번호
    - 1 <= X <= 100

### 문제 접근

- 가능한 경우를 판단하여 완전 탐색 진행
- 현재 정점 기준으로 7시방향, 5시방향, 1시방향, 11시방향 을 dfs로 탐색
- set 자료구조로 map의 중복된 숫자 찾기
- 시작지점으로 다시 돌아오면서 hs의 크기가 최소로 사각형을 만들 수 있는 4보다 크면 정답과 비교 후 갱신
