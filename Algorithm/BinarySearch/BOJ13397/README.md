## 백준 13397 구간 나누기 2

문제 링크 : [구간 나누기 2](https://www.acmicpc.net/problem/13397)

### 문제 조건

- N개의 수로 이루어진 1차원 배열이 있다.
- M개 이하의 구간으로 나누어서 구간의 점수의 최댓값을 최소로 하려고 한다.
- 구간은 다음과 같은 조건을 만족해야 한다.
    - 하나의 구간은 하나 이상의 연속된 수들로 이루어져 있다.
    - 배열의 각 수는 모두 하나의 구간에 포함되어 있어야 한다.
- 구간의 점수란 구간에 속한 수의 최댓값과 최솟값의 차이이다.
- 구간 점수들의 최솟값을 구하고 그 중 최댓값이 최소가 되는 프로그램을 작성하시오

- 배열의 크기 N
    - 1 <= N <= 5_000
- 나눠야할 구간의 수 M
    - 1 <= M <= N
- 원소의 크기
    - 1 <= 원소 <= 10_000

### 문제 접근

- 배열 원소의 최대 크기를 구하고 left = 0 ,right = 최대 크기로 둔다.
- 길이에 대해 이분 탐색을 진행하며 해당 길이라면 몇 개의 구간이 나올 수 있는 지 체크한다.

### 파라메트릭 서치 
- 최적화 문제를 결정 문제로 바꾼 뒤 이분 탐색을 이용해 최적해를 찾는 알고리즘
  - 최적화 문제 : 어떤 값의 최대,최소를 구하는 문제
  - 결정 문제 : 답이 Y/N 으로 갈리는 문제
- 최적화 문제를 결정문제로 바꿀 수 있는 문제더라도 결정 문제의 결과가 두 구간으로 나누어져 있지 않다면 사용 불가능하다.
  - false,false,false,true,true,... 로 구간이 나누어져 있다면 가능 
  - false,true,false,true 와 같이 구간이 여러개로 나누어지는 경우가 존재한다면 불가능

- 이분 탐색 구간 팁
```kotlin
    // 답이 [a,b] 구간내에 존재한다면
    var left = a-1 
    var right = b
    while(left+1 < right){
        val mid = (left+right) / 2
        if(결정조건체크함수) lo = mid
        else hi = mid
    }
```
- 위처럼 구현하면 left 에는 false 구간이 right 에는 결정 조건을 만족하는 true 구간이 시작
  - while문 탈출 조건에 의해서 while문이 끝난 뒤에는 left+1 >= right 
  - while문 내에서는 left+1 < right 이므로 left 와 right의 차이는 2 이상이다. mid는 반드시 (left,right) 구간에 존재한다.