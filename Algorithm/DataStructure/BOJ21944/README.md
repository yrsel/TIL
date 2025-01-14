## 백준 21944 문제 추천 시스템 Version 2

문제 링크 : [문제 추천 시스템 Version 2](https://www.acmicpc.net/problem/21944)

### 문제 조건

- tony9402는 코딩테스트 대비 문제를 뽑아서 "문제 번호, 난이도, 알고리즘 분류"로 정리해놨다.
- 만들려고 하는 명령어는 총 3가지가 있다.

1. recommend G x
    - x가 1인 경우, 추천 문제 리스트에서 알고리즘 분류가 G인 문제 중 가장 어려운 문제 번호를 출력한다.
        - 만약, 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 큰 것으로 출력한다.
    - x가 -1인 경우, 추천 문제 리스트에서 알고리즘 분류가 G인 문제 중 가장 쉬운 문제 번호를 출력한다.
        - 만약, 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 작은 것으로 출력한다.
    - 해당 명령어는 해당 그룹 G에 문제 번호가 한 개 이상이 있을 경우에만 주어진다.
2. recommend2 x
    - x가 1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 가장 어려운 문제 번호를 출력한다.
        - 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 큰 것으로 출력한다.
    - x가 -1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 가장 쉬운 문제 번호를 출력한다.
        - 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 작은 것으로 출력한다.
3. recommend3 x L
    - x가 1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 난이도 L보다 크거나 같은 문제 중 가장 쉬운 문제 번호를 출력한다.
        - 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 작은 것으로 출력한다.
        - 만약 조건을 만족하는 문제 번호가 없다면 -1을 출력한다.
    - x가 -1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 난이도 L보다 작은 문제 중 가장 어려운 문제 번호를 출력한다.
        - 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 큰 것으로 출력한다.
        - 만약 조건을 만족하는 문제 번호가 없다면 -1을 출력한다.
4. add P L G
    - 추천 문제 리스트에 난이도가 L이고 알고리즘 분류가 G인 문제 번호 P를 추가한다.
    - 추천 문제 리스트에 없는 문제 번호 P만 입력으로 주어진다.
    - 이전에 추천 문제 리스트에 있던 문제 번호가 다른 난이도와 다른 알고리즘 분류로 다시 들어 올 수 있다.
5. solved P
    - 추천 문제 리스트에서 문제 번호 P를 제거한다.
    - 추천 문제 리스트에 있는 문제 번호 P만 입력으로 주어진다.

- 문제 수 N, 문제 번호 P
    - 1 <= N <= 100,000
- 명령문 개수 M
    - 1 <= M <= 10,000
- 난이도 L, 알고리즘 분류 G
    - 1 <= L, G <= 100, L과 G은 자연수

### 문제 접근

- 전체 데이터 관리 Map<문제번호,정보>
    - 추가, 제거 될 때 데이터 갱신 -> 분류기준별, 또는 분류 상관없을 때 추천 쿼리의 데이터를 항상 비교
- TreeSet으로 데이터 저장 -> 알고리즘 분류 상관없이 쿼리에 대한 응답
- Map<문제번호,TreeSet> 으로 알고리즘별 쿼리에 대한 응답