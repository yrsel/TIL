## 백준 1474 밑 줄

문제 링크 : [밑 줄](https://www.acmicpc.net/problem/1474)

### 문제 조건

- 세준이는 N개의 영어 단어를 이용해 길이가 M인 새로운 단어를 만들려고 한다.
- 새로운 단어는 N개의 단어를 순서대로 이어 붙이고, 각 단어의 사이에 _을 넣어서 만든다.
- 이렇게 만든 새로운 단어의 길이가 M이 아닌 경우 _를 추가해서 길이가 M이 되게 만들어야 한다.
- _는 단어와 단어 사이에만 추가할 수 있다. 따라서, 새로운 단어는 _으로 시작하거나, _로 끝날 수 없다.
- 단어와 단어 사이에 있는 _의 개수는 모두 같아야 한다.
- 모두 같게 만드는 것이 불가능한 경우 단어와 단어 사이에 있는 _의 개수의 최댓값과 최솟값의 차이는 1이 되어야 한다.
- 새로운 단어 중 사전 순으로 가장 앞서는 단어를 구해보자.

- 영어단어 수 N
    - 2 <= N <= 10
- 새로운 단어 길이 M
    - 3 <= M <= 200
- 단어는 알파벳 소문자, 대문자로만 이루어져 있다.
- 단어의 길이
    - 1 <= 단어의 길이 <= 10
- 단어 N개의 길이의 합을 len이라고 했을 때, len+N-1 <= M 을 만족한다.

### 문제 접근

- _의 개수의 최댓값과 최솟값 차이는 1이어야 하므로 _의 총개수를 단어 사이의 개수만큼으로 나눈다
- 남은 _은 우선순위에 따라 정방향으로 알파벳 소문자 위치부터 추가해주고 아직도 남았다면 역방향으로 대문자 위치 앞에 추가해준다.