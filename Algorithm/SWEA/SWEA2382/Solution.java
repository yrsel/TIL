package Algorithm.SWEA.SWEA2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static class Point {
        int r, c, cnt, dir;

        public Point(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    private static PriorityQueue<Point> pq;
    private static final List<Point> movePoints = new ArrayList<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cnt, o1.cnt));
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                pq.offer(new Point(r, c, cnt, dir));
            }
            for (int i = 0; i < M; i++) procedure();

            result.append(String.format("#%d %d\n", tc, calculate()));
        }
        System.out.println(result);
    }

    private static void procedure() {
        int[][] visited = new int[N][N];
        movePoints.clear();
        movePoints.add(new Point(0, 0, 0, 0)); // 더미

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            int nr = curr.r + dir[curr.dir][0];
            int nc = curr.c + dir[curr.dir][1];
            if (isBound(nr, nc)) { // 가장자리
                curr.cnt /= 2;
                if (curr.cnt!=0) movePoints.add(new Point(nr, nc, curr.cnt, turnBack(curr.dir)));
            } else {
                if (visited[nr][nc]==0) {
                    visited[nr][nc] = movePoints.size(); // 인덱스 번호 기록
                    movePoints.add(new Point(nr, nc, curr.cnt, curr.dir));
                } else { // 합쳐지는 경우
                    movePoints.get(visited[nr][nc]).cnt += curr.cnt;
                }
            }
        }
        pq.addAll(movePoints); // 움직인 후의 위치들
    }

    private static int turnBack(int dir) {
        if (dir==0 || dir==2) return ++dir;
        return --dir;
    }

    private static boolean isBound(int r, int c) {
        return r==0 || c==0 || r==N - 1 || c==N - 1;
    }

    private static int calculate() {
        int cnt = 0;
        while (!pq.isEmpty()) cnt += pq.poll().cnt;
        return cnt;
    }
}
