## 백준 16943 숫자 재배치

문제 링크 : [수자 재배치](https://www.acmicpc.net/problem/16943)

### 문제 조건

- 두 정수 A와 B가 있을 때, A에 포함된 숫자의 순서를 섞어서 새로운 수 C를 만들려고 한다.
- 즉, C는 A의 순열 중 하나가 되어야 한다.
- 가능한 C 중에서 B보다 작으면서, 가장 큰 값을 구해보자, C는 0으로 시작하면 안된다.
- 그러한 C가 없는 경우 -1을 출력한다.

- 1 <= A, B < 1_000_000_000

### 문제 접근

- A자릿수가 B의 자릿수보다 크면 -1 출력
- A를 오름차순으로 정렬 후 A의 마지막인덱스쪽(큰 수)부터 채워나가면서 숫자를 비교해주기
- 최초로 B보다 작으면서 가장 앞자리가 0이 아닌 수를 찾으면 종료
- 순열 진행
  