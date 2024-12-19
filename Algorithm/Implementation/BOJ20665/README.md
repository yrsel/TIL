## 백준 20665 독서실 거리두기

문제 링크 : [독서실 거리두기](https://www.acmicpc.net/problem/20665)

### 문제 조건

1. 사람들은 가장 가까이에 앉아있는 사람이 가장 먼 자리를 선호한다.
    - 만약 독서실을 이용하는 사람이 없다면 좌석번호 1번 자리를 가장 선호한다.
2. 1번 규칙으로 비교할 수 없다면, 가장 먼 좌석들 중에서 좌석 번호가 가장 작은 자리를 선호한다.

- 독서실 관리자로 오래 근무한 민규에게는 선호하는 좌석이 있다. 하지만 민규는 매우 소심하기 때문에, 사람들이 본인 때문에 이용하고자 하는 자리를 이용하지 못하는 일은 피하고 싶다.
- 민규가 근무하는 독서실은 09:00 부터 21:00 까지 운영되며, 철저히 예약제로 운영되기 때문에 민규는 사람들이 언제부터 언제까지 독서실을 이용하는 지 알 수 있다.
- 민규는 자신이 선호하는 자리를 얼마나 이용할 수 있는지 계산해보고자 한다.

- 독서실 좌석의 개수 N
    - 1 <= N <= 100
- 독서실 예약자 수 T
    - 1 <= T <= 500
- 민규가 좋아하는 좌석 번호 P
    - 1 <= P <= N
- 독서실 입실 시간, 퇴실 시간이 HHMM HHMM 형태로 입력된다.
- (0900 <= HHMM <= 2100 , 퇴실 시간이 입실 시간보다 빠른 경우는 없다.)
- 독서실 예약이 같은 시각에 시작된다면 짧은 이용시간을 가진 사람을 먼저 앉힌다.
- 독서실 예약 리스트에 있는 예약자들이 좌석이 없어서 못 앉는 상태는 존재하지 않는다.

### 접근 방향

- 시작시간 빠른 순, 이용시간 짧은 순서로 정렬
- 배열을 순회하면서 좌석 자리에 끝나는 시간을 기록하며 앉을 수 있는 자리 탐색
    - 현재 시간 기준으로 자리에 앉아있는 인덱스 체크
- 선호하는 좌석을 이용할 수 있는 시간 -> 전체 시간 - 독서실 예약자가 이용하는 시간