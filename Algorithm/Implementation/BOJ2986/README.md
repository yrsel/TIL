## 백준 2986 파스칼

문제 링크 : [파스칼](https://www.acmicpc.net/problem/2986)

### 문제 조건

- 고창영이 10살 때 있었던 실화이다.
- 창영이가 10살 때 파스칼을 독학했다. 창영이가 공부하던 책에는 다음과 같은 프로그램이 있었다.

```
readln(N);
counter := 0;
for i := N-1 downto 1 do begin
    counter := counter + 1;
    if N mod i = 0 then break;
end;
writeln(counter);
```

- 창영이는 N을 입력했을 때, 무엇이 출력될지 궁금해졌다.
- 창영이가 입력한 N이 주어졌을 때, 무엇이 출력되는지 구하는 프로그램을 작성하시오.

- N
    - 1 <= N <= 1,000,000,000
- 첫째 줄에 결과를 출력한다.

### 문제 접근

- 문제의 코드에서 종료 조건은 N이 나누어 떨어지는 최초의 수를 구하는 것이다. -> 최대공약수 중 가장 크면서 N이 아닌 값 