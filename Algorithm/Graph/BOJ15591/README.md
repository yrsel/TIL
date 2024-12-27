## 백준 15591 MooTube(Silver)

문제 링크 : [MooTube(Silver)](https://www.acmicpc.net/problem/15591)

### 문제 조건

- MooTube에서 농부 존의 소들은 재밌는 동영상들을 서로 공유할 수 있다.
- 소들은 MooTube에 1부터 N까지 번호가 붙여진 N (1<=N<=5,000)개의 동영상을 이미 올려 놓았다.
- 하지만, 존은 아직 어떻게 하면 소들이 그들이 좋아할 만한 새 동영상을 찾을 수 있을지 괜찮은 방법을 떠올리지 못했다.
- 존은 두 동영상이 서로 얼마나 가까운 지 측정하는 단위인 "USADO"를 만들었다.
- 존은 N-1개의 동영상 쌍을 골라서 직접 두 쌍의 USADO를 계산했다. 그 다음에 존은 이 동영상들을 네트워크 구조로 바꿔서, 각 동영상을 정점으로 나타내기로 했다.
- 또 존은 동영상들의 연결 구조를 서로 연결되어 있는 N-1개의 동영상 쌍으로 나타내었다.
- N-1개의 동영상 쌍을 골라서 어떤 동영상에서 다른 동영상으로 가는 경로가 반드시 하나 존재하도록 했다.
- 존은 임의의 두 쌍 사이의 동영상의 USADO를 그 경로의 모든 연결들의 USADO 중 최솟값으로 하기로 했다.
- 존은 어떤 주어진 MooTube 동영상에 대해, 값 K를 정해서 그 동영상과 USADO가 K 이상인 모든 동영상이 추천되도록 할 것이다.
- 하지만 존은 너무 많은 동영상이 추천되면 소들이 일하는 것이 방해될까 봐 걱정하고 있다! 그래서 그는 K를 적절한 값으로 결정하려고 한다.
- 농부 존은 어떤 K 값에 대한 추천 동영상의 개수를 묻는 질문 여러 개에 당신이 대답해주기를 바란다.

- N, Q
    - 1 <= N, Q <= 5,000
- 농부 존이 직접 잰 두 동영상 쌍의 USADO
    - p,q,r ( p,q : 동영상, r : 동영상 연결되어 있는 크기)
        - 1 <= p,q <= N
        - 1 <= r <= 1,000,000,000
- 질문 Q의 내용
    - k,v
    - 1 <= k <= 1,000,000,000
    - 1 <= v <= N
    - K=k라면, 동영상 v를 보고 있는 소들에게 몇 개의 동영상이 추천될 지 묻는 것을 의미

### 문제 접근
