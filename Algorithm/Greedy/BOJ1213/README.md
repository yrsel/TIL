## 백준 1213 팰린드롬 만들기

문제 링크 : [팰린드롬 만들기](https://www.acmicpc.net/problem/1213)

### 문제 조건

- 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만들려고 한다.
- 대문자로만 이루어진 최대 50글자의 영어 이름이 주어진다.
- 만약 불가능할 때는 "I'm Sorry Hansoo" 출력하고, 여러개 일 경우 사전순으로 앞서는 것을 출력한다.

### 문제 접근

- 영어 이름 길이가 홀수이면서 알파벳 쌍이 홀수인 경우가 2개 이상인 경우 불가능
- 영어 이름 길이가 짝수이면서 알파벳 쌍이 홀수인 경우가 존재하면 불가능
- TreeMap<Char,Int> 로 알파벳의 개수 저장
- 영어 이름 길이 만큼의 빈 배열 선언 후 , 포인터 idx 옮기면서 넣어주기 (idx, N-1-idx)
- value/2 만큼 반복 , 만약 개수가 홀수라면 size/2 위치에 알파벳 넣어놓기