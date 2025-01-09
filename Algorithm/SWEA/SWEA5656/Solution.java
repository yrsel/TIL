package Algorithm.SWEA.SWEA5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    private static final String format = "#%d %d";
    private static int[] selected;
    private static int[][] map;
    private static int N, R, C;
    private static int brokenCnt;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            map = new int[R][C];
            selected = new int[N];
            int result = 0;
            brokenCnt = 0;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0) result++;
                }
            }
            search(0);
            sb.append(String.format(format, tc, result - brokenCnt)).append('\n');
        }
        System.out.println(sb);
    }

    private static void search(int idx) {
        if (idx == N) {
            brokenCnt = Math.max(brokenCnt, broken());
            return;
        }

        for (int i = 0; i < C; i++) {
            selected[idx] = i;
            search(idx + 1);
        }
    }

    private static int broken() {
        int count = 0;
        int[][] copy = copyMap();
        for (int i = 0; i < N; i++) {
            int c = selected[i];
            int r = findTop(copy, c);
            if (r == -1) continue; // 폭발할 것이 없다.
            count += bomb(copy, r, c);
            down(copy);
        }
        return count;
    }

    private static int[][] copyMap() {
        int[][] copy = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static int findTop(int[][] copy, int c) {
        int r = 0;
        while (r < R && copy[r][c] == 0) r++;
        return r == R ? -1 : r;
    }

    private static int bomb(int[][] copy, int r, int c) {
        int count = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c, copy[r][c]});
        copy[r][c] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int range = curr[2];
            for (int i = 1; i < range; i++) {
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dir[d][0] * i;
                    int nc = curr[1] + dir[d][1] * i;
                    if (isOut(nr, nc) || copy[nr][nc] == 0) continue;
                    q.offer(new int[]{nr, nc, copy[nr][nc]});
                    copy[nr][nc] = 0;
                    count++;
                }
            }
        }
        return count;
    }

    private static void down(int[][] copy) {
        Stack<Integer> stack = new Stack<>();
        for (int c = 0; c < C; c++) {
            for (int r = 0; r < R; r++) {
                if (copy[r][c] != 0) {
                    stack.push(copy[r][c]);
                    copy[r][c] = 0;
                }
            }
            int idx = R - 1;
            while (!stack.isEmpty()) {
                copy[idx--][c] = stack.pop();
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
