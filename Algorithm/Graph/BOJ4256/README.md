## 백준 4256 트리

문제 링크 : [트리](https://www.acmicpc.net/problem/4256)

### 문제 조건

- 노드 n개로 이루어진 이진 트리 ( 1 ~ n번 )
- 전위 순회, 중위 순회 결과가 주어질 때 후위 순회했을 때의 결과를 구하는 프로그램을 작성하세요

- 테스트 케이스의 수 T
- 노드의 개수 n
    - 1 <= n <= 1_000

### 문제 접근

- 전위 순회 ( V -> left -> right )
- 중위 순회 ( left -> V -> right )
- 후위 순회 ( left -> right -> V )
- 전위 순회의 0 인덱스 : (루트 노드)
    - 중위 순회에서 루트 노드의 인덱스보다 작은 인덱스 -> 왼쪽 자식, 큰 인덱스 -> 오른쪽 자식
    - 전위 순회 순서에서 중위 순회의 인덱스
- 중위 순회를 기준으로 왼쪽 오른쪽을 나누고 전위 순회의 가장 앞쪽 원소부터 방문하면 왼쪽부터 모두 탐색 가능
    - 기준으로 오른쪽도 모두 방문 한다음 정점을 체크하면 후위 순회와 동일 