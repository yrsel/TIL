## 백준 1548 부분 삼각 수열

문제 링크 : [부분 삼각 수열](https://www.acmicpc.net/problem/1548)

### 문제 조건

- 세수 x,y,z가 x+y>z, x+z>y, y+z>x 관계를 만족하면, 세 수는 삼각관계에 있다고 한다.
- 길이가 N인 수열 B의 모든 b[i], b[j], b[k]가 삼각관계에 있으면 이 수열은 삼각 수열이라고 한다. 이떄 i, j, k는 모두 다른 값이다.
- 수열 A가 주어졌을 때, 이 수열에서 적절히 몇 개의 원소를 빼서 이 수열을 삼각 수열로 만들려고 한다. 삼각 수열의 최대 길이를 구하는 프로그램을 작성하시오.

- 수열의 크기 N
    - 최대 50
- 배열의 원소
    - 1 <= 원소 <= 1_000_000_000

### 문제 접근

- 선택한 세 개의 수가 모두 같은 수인 경우 삼각관계를 만족한다.
- 두 개의 동일한 수와 하나의 수를 선택했다면 2*x > y 를 만족해야 한다.
- 모두 다른 세 개의 수일 경우 x+y>z, x+z>y, y+z>x 를 만족해야 한다.
  - 제외하는 수는 가장 작은 수를 제외하는 것이 조건을 충족할 수 있다.

- 문제 풀이 -> 오름차순으로 정렬하고 for문 3개로 숫자 3개를 선택, 만약 조건을 만족하지 않는 경우가 존재한다면 가장 안쪽의 for문을 종료하고 최댓값과 비교 후 갱신