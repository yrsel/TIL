## 백준 21315 카드 섞기

문제 링크 : [카드 섞기](https://www.acmicpc.net/problem/21315)

### 문제 조건

- 1 ~ N 숫자 카드가 존재, 초기상태에는 1이 맨위, N개의 카드가 번호 순서대로 쌓여있다.
- 마술을 위해 (2,K) 섞기를 만들었다.
- (2,K) 섞기는 총 K + 1 단계로 이루어져 있다.

1. 카드 더미 중 밑에서 2^k개의 카드를 더미 맨 위로 올린다.
2. 이후 i(2<=i<=K+1) 번째 단계는 직전에 맨 위로 올린 카드 중 밑에서 2^(K-i+1)개의 카드를 더미의 맨 위로 올린다.

- 초기 상태에서 (2,K) 섞기를 2번 한 결과를 보고 2번의 (2,K) 섞기에서 K가 각각 무엇인지 맞추는 마술이다.
- 2번의 (2,K)섞기 후의 카드 더미 결과를 만드는 각각의 K는 유일함이 보장된다.

- 카드 개수 N
    - 3 <= N <= 1,000
- K
    - 1 <=K, 2^K < N

### 문제 접근

- N이 최대 1000일 때, 2^k가 1000보다 작아야하므로, K는 최대 9
- 2번의 섞기를 진행해야하고 K는 유일하기 때문에 9P2 = 72의 경우의 수가 존재하고
- 맨 앞에서 부터 swap 하면서 모든 구간을 스왑했을 때 1부터 오름차순으로 정렬되었는지 체크

- `문제에서 카드 더미 결과를 만드는 각각의 K는 유일함이 보장된다.` : 이 문구가 중복인 K값을 허용하지 않는다고 의미했는데 그게 아닌가보다.
  - 중복순열이 당연히 아니라고 생각하고 코드 구현에 실수가 있었나하며 1~N을 목표배열로 변경하는 방식, 목표배열을 거꾸로 1~N으로 만들어 보는 방식으로 수정하다가 시간을 많이 허비했다. 
  - 국어를 더 공부해야 하나...
