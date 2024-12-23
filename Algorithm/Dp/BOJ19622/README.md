## 백준 19622 회의실 배정 3

문제 링크 : [회의실 배정 3](https://www.acmicpc.net/problem/19622)

### 문제 조건

- 서준이는 아빠로부터 N개의 회의와 하나의 회의실을 선물로 받았다.
- 각 회의는 시작 시간, 끝나는 시간, 회의 인원이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다.
- 단, 회의는 한 번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
- 회의의 시작 시간은 끝나는 시간보다 항상 작다. N개의 회의를 회의실에 효율적으로 배정할 경우 회의를 진행할 수 있는 최대 인원을 구하자.

- 임의의 회의 K는 회의 K-1, K+1 과는 회의 시간이 겹치고 다른 회의들과는 회의 시간이 겹치지 않는다.
- 회의의 수 N
    - 1 <= N <= 100,000
- 회의의 시작 시간과 끝나는 시간
    - 0 <= 시간 <= 2^31-1
- 회의 인원
    - 1 <= 인원 <= 1,000

### 문제 접근

- 임의의 회의 K는 K-1,K+1 외에는 회의 시간이 겹치지 않으므로 회의 시작, 회의 종료 시간 기준으로 정렬
- 회의 K를 선택한 경우 -> K-2 의 회의실 선택한 경우 최대와 K-1 회의실 선택안한 경우 최대 중 큰 값에 현재 인원 더해주기
    - 선택 안한 경우 -> 선택한경우 또는 안한경우 중 최대값 
