## 백준 16974 레벨 햄버거

문제 링크 : [레벨 햄버거](https://www.acmicpc.net/problem/16974)

### 문제 조건

- 레벨 L 버거
- 레벨-0 버거 : P
- 레벨-L 버거 : B, L-1버거, P, L-1버거, B
- 레벨-N 버거를 시켰을 때, 아래 X장 까지 패티는 몇장 존재하는가?
- 1 <= N <= 50 (레벨)
- 1 <= X <= 레벨-N 버거에 있는 레이어의 수

### 접근 방향

- 레벨이 1 증가할 때마다 길이가 현재 길이*2+3 만큼 증가한다.
- 대략 2^50 을 넘어서는 길이가 되므로 Long 자료형으로 햄버거의 길이를 선정한다.
- 재귀 호출의 인자로 (현재 레벨,햄버거의 길이,패티의 길이) 로 설정한다
- 만약 X보다 햄버거의 길이가 길어졌다면 return 을 호출
  - return 후 현재 X는 현재 레벨의 모든 패티를 충족 ( 패티 갯수 더해주기)
    - 만약 현재 레벨 + 2 위치보다 크다면 ( B 레벨 P 레벨 B ) 에서 (앞에 번 +1, 패티위치 +1) => + 2 위치를 체크 
      - 조건 만족하다면 패티 하나 추가 
      - X에서 현재 레벨 햄버거 길이 + 2 를 빼주기 ( 햄버거의 남은 뒷쪽부분을 재귀를 진행하며 처리 )
    - 만약 X가 현재 레벨 햄버거 길이에 못 미친다면 맨 앞에 추가된 햄버거 번만 빼주기 
  - 레벨 1 길이(=5) 보다 작아진다면 조건분기를 통해서 마지막 추가할 패티 더해주기
    - add() 따로 뺴준 이유 : 만약 5보다 작거나 같은 길이가 들어올 경우 재귀를 진행하지않고 바로 종료하게 된다.

### 문제 상황

- level을 사용하지 않아도 된다고 생각하고 길이에 대한 조건만 찾아서 해결했는데 레벨에 따라 작은 수일 수록 앞에 B가 더 쌓일 수 밖에 없었다.
- 다시 레벨까지 활용해서 문제 접근

### 한달 뒤 다시 접근 후 해결 
- level0 : P  / 햄버거 길이 : 1 , 패티 수 : 1
- level1 : B(level0)P(level0)B / 햄버거 길이 : 5, 패티 수 : 3
- level2 : B(level1)P(level1)B / 햄버거 길이 : 13, 패티 수 : 7 
- 위와 같이 레벨이 증가함에따라 모양이 변하게 된다.
  - 레벨 N까지의 햄버거의 길이,패티의 수를 기록한다.
- 만약 K가 햄버거 절반의 길이 + 1 보다 작거나 같다면 왼쪽 부분만 탐색
  - 만약 K가 햄버거 절반의 길이와 같다면 정답에 [현재 레벨 -1]의 패티길이를 더해주고 return
  - 만약 K가 햄버거 절반의 길이 + 1 와 같다면 정답에 [현재 레벨 -1]의 패티길이 + 1 를 더해주고 return (B () P () B , 구조에서 P 까지 해당하기에 +1 )
  - 만약 K가 현재 레벨 햄버거 절반의 길이보다 작다면 레벨을 하나 줄여주면서 K의 길이도 -1 해주기 (다음 레벨 탐색할 때는 맨 앞에 B는 제거해줘야 level-1과 동일한 모양)
- 만약 K가 햄버거 절반의 길이 + 1 보다 크다면 [현재 레벨 -1]의 패티 길이 + 1 를 정답에 더해주고 오른쪽 부분 탐색 진행 (B () P () B , 구조에서 P 까지 해당하기에 +1 )  
  오른쪽 부분의 길이는 K에서 현재 햄버거 길이 / 2 + 1 만큼 빼주면 오른쪽부분 남은 길이를 구할 수 있다. 