## 백준 4097 수익

문제 링크 : [수익](https://www.acmicpc.net/problem/4097)

### 문제 조건

- 연종이는 창업했다. 오늘은 창업한 지 N일이 되었고, 매일 매일 수익을 적어놓았다.
- 어느날 연종이는 가장 많이 돈을 번 구간이 언제인지 궁금해졌다.

- 창업 일 수 N
    - 1 <= N <= 250,000
- 수익 P
    - -10,000 <= P <= 10,000

### 문제 접근

- 1차원 배열로 이전까지의 최대에서 현재값을 더하는 것과 현재 값을 비교해서 큰 값으로 갱신 