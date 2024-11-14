## 백준 13168 내일로 여행

문제 링크 : [내일로 여행](https://www.acmicpc.net/problem/13168)

### 문제 조건

- 친한 친구인 승현이와 지학이는 여행을 가기로 계획했다.
- N개의 도시가 있으며, 이 중 여행할 M개의 도시를 결정했다.
- 여행 경로에서 같은 도시를 여러 번 방문할 수 있으며, 여행을 시작하는 도시와 끝내는 도시는 같다.
- 두 도시 사이를 오갈 수 있는 K개의 교통수단이 있다.
    - 기차,지하철,버스,택시,비행기,... / 특히, 기차 코스는 무궁화화,ITX-새마을,ITX-청춘,KTX.. 등이 있다.
    - 모든 교통수단은 한 번 이용하는 데 필요한 비용이 정해져 있다.
- 계획한 M개의 도시를 최소비용으로 차례대로 움직일 것이다.
- 한편, 코레일에서는 '내일로' 라는 특별한 기차표를 판매하고 있다. R원을 주고 내일로 티켓을 구매할 수 있다.
    - 한 번 내일로 티켓을 사면, 며칠동안 무궁화호,ITX-새마을,ITX-청춘은 무료로 이용할 수 있으며, S-Train,V-Train은 50% 할인된 가격으로 이용할 수 있다.
- 내일로 티켓을 사는 것과 사지 않는 것 중 어떤 선택이 더 좋은 지 구하는 프로그램을 작성하세요.

- 도시의 수 N
    - 1 <= N <= 100
- 1인당 내일로 티켓의 가격 R
    - 1 <= R <= 1,000,000
- 도시 이름 : 알파벳 대소문자로 구성된 길이 20 이하의 문자열
- 여행할 도시의 수 M
    - 1 <= M <= 200
- 교통수단의 수 K
    - 1 <= K <=10,000
- 비용 C
    - 1 <= C <= 100,000

### 문제 접근

- 도시의 수가 100개이고 정점간의 간선을 하나로 추려낼 수 있기에 플로이드 와샬로 모든 경우를 탐색해도 될 것 같다.
- 2차원 배열 이동거리를 2개로 기록한다.

1. 내일로 이용할 때 최소 금액
2. 내일로 이용하지 않을 때 최소 금액

- 금액이 1원 일 경우도 있어서 50%할인 금액에 대해 0.5 와 같이 실수 자료형이 필요하다.
    - 실수 자료형을 사용
    - 금액 *2 한 값을 가지고 50%할인을 적용