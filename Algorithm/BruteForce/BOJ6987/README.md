## 백준 6987 월드컵

문제 링크 : [월드컵](https://www.acmicpc.net/problem/6987)

### 문제 조건

- 6개국으로 구성된 각 조 별로 각 국가들과 1번씩 경기해서 총 5번의 경기를 진행한다.
- 네 가지의 경기 결과가 주어질 때 각각의 경기 결과가 가능한 경우인지 판단하기
- 가능하면 1, 불가능한 경우라면 0 을 출력

- 승, 무, 패 수는 6보다 작거나 같은 자연수 또는 0

### 문제 접근

- A 국가 승 -> B~F 국가 중 하나의 패 
  - 국가의 승이 0이 될 때까지 확인 -> 무승부 0 될때 까지 확인 -> 다음 국가의 승 확인  
  - 5경기 * 6 국가 = 30 -> 한 경기하면 2국가가 참여하니 15개의 count가 도달했을 때의 남은 경기있는지 확인
    