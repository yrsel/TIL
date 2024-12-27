## 백준 1487 물건 팔기

문제 링크 : [물건 팔기](https://www.acmicpc.net/problem/1487)

### 문제 조건

- 세준이는 최대 이익으로 상품을 판매하려고 한다.
- N명의 사람이 살 의향을 보였다. 각자는 지불할 금액의 최대한도가 존재한다.
- 세준이는 각각의 사랑에게 배달하는 비용이 얼마나 걸리는 지 알고 있다.
- N명의 사람과, 각각의 사람이 지불할 용의가 있는 최대 금액과 배송비가 주어졌을 때, 세준이의 이익을 최대로 하는 가격을 출력하는 프로그램을 작성하시오.

- 세준이의 물건을 구매할 의향이 있는 사람 수 N
    - 50보다 작거나 같다.
- 각 사람이 주불할 최대 금액과 배송비
    - 1,000,000 보다 작거나 같은 음이 아닌 정수, 배송비는 0이 될 수도 있다.
- 최대 이익을 만들어주는 가격을 출력한다. 여러 개라면, 가장 낮은 가격을 출력한다.
    - 어떤 가격으로 팔아도 이익을 남길 수 없다면 0을 출력한다.

### 문제 접근

- 사람이 구매하려는 금액으로 판매를 진행했을 경우 이득이 얼마인지 계산
- 그 중 최대 이득 금액이 동일한 경우 판매하는 금액이 작은 것을 선택