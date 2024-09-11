## SW Expert Academy 5658 [모의 SW 역량테스트] 보물상자 비밀번호

문제 링크 : [[모의 SW 역량테스트] 보물상자 비밀번호](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&categoryId=AWXRUN9KfZ8DFAUo&categoryType=CODE&problemTitle=%EC%97%AD%EB%9F%89&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

### 문제 조건

- 시간: 50개 테스트케이스 합쳐서 JAVA의 경우 3초
- 메모리: 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내

- 16진수(0~F) 숫자가 적혀 있는 보물상자
- 보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴때마다 숫자가 시계방향으로 한칸 회전
- 각 변에는 동일한 개수의 숫자가 존재, 시계방향 순으로 높은 자리 숫자에 해당
- Fig 1, Fig 2에 대한 그림이 없지만 예시를 보면 10시방향부터 시계방향으로 시작인 것 같다.
- 보물상자의 좌물쇠의 비밀번호
  - 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 숫자를 10진수로 만든 수
  - N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀번호를 출력하는 프로그램을 만들기 
- N은 4의 배수이고, 8 <= N <= 28
- N개의 숫자는 각각 0이상 F이하로 주어진다. (A~F는 알파벳 대문자로 주어진다.)
- K는 생성 가능한 수의 개수보다 크게 주어지지 않는다.

### 문제 접근
- 한 변의 길이 -> N/4
- 회전 가능한 횟수 -> N/4 회
- list로 가능한 숫자 저장 ( 중복 수 체크 )
- idx 변수를 두고 모듈러 연산으로 시작 위치를 조정해서 회전 후 한 변의 길이들을 모두 체크해주기
- FFFFFFF -> 15_151_515_151_515 : 최대 -> Long 자료형으로 값들을 관리 
- 소요시간 : 50분 