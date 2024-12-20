## 백준 9024 두 수의 합

문제 링크 : [두 수의 합](https://www.acmicpc.net/problem/9024)

### 문제 조건

- 여러 개의 서로 다른 정수 S = {a1,a2,...} 와 또 다른 정수 K가 주어졌을 때, S에 속하는 서로 다른 두 개의 정수의 합이 K에 가장 가까운 두 정수를 구하시오.
- 여러 개의 서로 다른 정수가 주어졌을 때, 주어진 정수들 중에서 서로 다른 두 정수의 합이 주어진 또 다른 정수에 가장 가까운 두 정수의 조합의 수를 계산하는 프로그램을 작성하시오.

- 원소의 개수 n
    - 2 <= n <= 1_000_000
- 서로 다른 두 개의 정수의 합 K
    - -10^8 <= K <= 10^8
- 원소의 값
    - -10^8 <= 원소 <= 10^8

### 문제 접근

- 오름차순으로 정렬
- for문으로 하나의 원소 선택 후 선택한 원소의 인덱스보다 큰 숫자들에 이분탐색으로 K에 최대한 근접하도록 진행하며 갱신
- 코틀린으로는 메모리초과를 해결할 수 없을 것 같아서 자바로 진행, 자바도 버전에 따라 메모리 초과문제가 발생 (JVM 처리 성능에 따라 달라지는 것 같다.) 