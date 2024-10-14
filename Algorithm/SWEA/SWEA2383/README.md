## SW Expert Academy 2383 [모의 SW 역량테스트] 점심 식사시간

문제
링크 : [[모의 SW 역량테스트] 점심 식사시간](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl&categoryId=AV5-BEE6AK0DFAVl&categoryType=CODE&problemTitle=%EC%97%AD%EB%9F%89&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

### 문제 조건

- N*N 정사각형 모양의 방에 사람들이 앉아 있다.
- 점심을 먹기 위해 아래 층으로 내려가야 하는데, 밥을 빨리 먹기 위해 최대한 빠른 시간 내에 내려가야 한다.
- 방 안의 사람 : P, 계단 입구 : S
- 이동 완료 시간은 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
- 계단 입구까지 이동 시간
    - 현재 위치에서 계단의 입구까지 이동하는데 걸리는 시간 계산
        - 이동 시간(분) = |PR - SR| + |PC - SC|
- 계단을 내려가는 시간
    - 계단 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간
    - 계단 입구에 도착하면, 1분 후 아래칸으로 내려갈 수 있다.
    - 계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있다.
    - 이미 계단을 3명이 내려가고 있는 경우, 그 중 한 명이 계단을 완전히 내려갈 때까지 계단 입구에서 대기해야 한다.
    - 계단마다 길이 K가 주어지며, 계단을 올라간 후 완전히 내려가는데 K분이 걸린다.
- 모든 사람이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우를 찾고, 그때의 소요시간을 출력하는 프로그램을 작성

- 제약 사항
    - 시간 제한: 최대 50개 테스트 케이스를 모두 통과하는데 Java 3초
    - 방의 한 번의 길이 N
        - 4 <= N <= 10
    - 사람의 수
        - 1 <= 사람의 수 <= 10
    - 계단의 입구는 반드시 2개이며, 서로 위치가 겹치지 않는다.
    - 계단의 길이
        - 2 <= 계단의 길이 <= 10
    - 초기 입력에서 사람의 위치와 계단 입구 위치는 서로 겹치지 않는다.

### 문제 접근

- 그리디하게 접근하다가 도저히 모든 케이스를 만족할 수 있는 아이디어가 떠오르지 않아서 완전탐색으로 아이디어 전환

- 사람의 위치에서 계단까지의 거리를 미리 다 구하기
    - stair[2][3] -> 행: 계단 A,B / 열 : r,c,계단길이
- 계단은 2곳이면서 사람의 수는 최대 10명이므로 2^10개의 경우의 수 존재
- pq로 도착시간이 짧은 것부터 차근차근 비교
    - pq -> candidiates
    - waitingQueue에서 대기하는 인원들 체크, endTimeQueue에서 현재 들어간 인원이 계단을 다 내려가는 시간 체크
    - time을 1씩 증가시키면서 경우의 수 체크
        - endTimeQueue 먼저 체크하면서 종료되는 작업 먼저 꺼내주고
        - waitingQueue 에서 대기하는 작업이 있는지 체크 - 있다면 작업 큐의 사이즈가 3을 넘어서지 않을 정도로만 만족하는 작업 넣어주기
            - 이때는 현재시간에 계단의 길이만큼을 종료시간으로 넣어주면 된다.
        - 아직도 작업할 수 있다면 candidate 에 도착시간이 현재 시간과 비교해서 넣어줄 수 있다면
            - 현재시간에 계단의 길이 + 1초 대기시간 을 종료시간으로 넣어준다.
    - 입장 후보 큐, 작업 종료시간 큐, 웨이팅 큐 모두 비었다면 종료 후 최소 시간을 비교 
    - 계단 2군데중 더 늦게 끝나는 시간을 기준으로 최소 시간과 비교해야 한다.