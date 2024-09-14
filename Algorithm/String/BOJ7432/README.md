## 백준 7432 디스크 트리

문제 링크 : [디스크 트리](https://www.acmicpc.net/problem/7432)

### 문제 조건

- 디렉토리 전체 경로를 텍스트 파일로 따로 저장하고 있다.
- 중요한 디렉토리의 전체 경로가 모두 주어졌을 때, 디렉토리 구조를 구해 보기 좋게 출력하는 프로그램 작성하기

- 디렉토리 전체 경로의 개수 N
    - 1 <= N <= 500
- 디렉토리명
    - 한 줄로 이루어져 있으며, 공백을 포함하지 않는다.
    - 경로는 80글자를 넘지 않는다.
    - 디렉토리는 `\` 로 구분된다.
    - 디렉토리명은 1 ~ 8 글자이며, 알파벳 대문자, 숫자, 특수 문자로 이루어져있다.
    - 특수문자는 !#$%&'()-@^_`{}~ 가능하다.

- 출력
    - 한 줄에 하나씩 디렉토리 이름을 출력하고, 공백으로 디렉토리 구조상 깊이를 표현한다.
    - 각 서브 디렉토리는 사전순으로 출력
        - 부모 디렉토리에서 출력한 공백의 개수보다 1개 많게 공백을 출력

### 문제 접근

- 트리로 구현 
- data class Node(현재 노드의 이름, 자식들)
  - 자식들은 우선순위큐로 이름이 빠른 순으로 정렬
- root 이름은 list 로 관리
- 재귀 탐색으로 우선순위에 맞춰 StringBuilder 에 답을 하나씩 저장 후 출력 