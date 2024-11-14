## 백준 2792 보석 상자

문제 링크 : [보석 상자](https://www.acmicpc.net/problem/2792)

### 문제 조건

- 각각의 보석은 M가지 서로 다른 색상 중 한 색상, N명에게 모든 보석을 나눠준다
- 학생은 보석을 못 받을수도 있다. 하지만, 한 개의 보석을 받은 학생은 다른 색의 보석을 가질 수 없다.
- 가장 많이 보석을 갖는 학생에게 질투심을 느낀다.
- 질투심이 최소가 되게 보석을 나눠주는 프로그램 작성

- 아이들의 수 N
    - 1 <= N <= 10^9
- 보석 색상의 수 M
    - 1 <= M <= 300,000
- M <= N

### 문제 접근

- 하나의 보석의 최대개수를 기준으로 이분 탐색 시작
- 이분탐색의 mid 값에 해당하는 만큼 보석을 나눠줄 때 각 보석마다 mid 로 나누어서 몇명에게 나누어줄 수 있는 지 체크
    - 나누어줄 수 있는 사람이 인원수보다 작거나 같다면 right를 mid -1
    - 나누어줄 수 있는 사람이 인원수보다 크다면 left를 mid+1 로 이동 