package Algorithm.SWEA.SWEA1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static boolean[][] visited;
    private static int[][] map;
    private static int R, C;
    private static final int[][][] dir = {
            {{}},
            {{-1, 0}, {1, 0}, {0, 1}, {0, -1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {0, 1}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, -1}, {-1, 0}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map = new int[R][C];
            visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result.append(String.format("#%d %d\n", tc, bfs(startR, startC, L)));
        }
        System.out.println(result);
    }

    private static int bfs(int startR, int startC, int time) {
        int count = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        --time;

        while (!q.isEmpty()) {
            if (time==0) break;
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                int[][] curDir = dir[map[curr[0]][curr[1]]];
                for (int[] d : curDir) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];
                    if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==0) continue;
                    if (isAvailMove(nr, nc, curr[0], curr[1])) {
                        visited[nr][nc] = true;
                        count++;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
            --time;
        }

        return count;
    }

    private static boolean isAvailMove(int r, int c, int curR, int curC) {
        int[][] curDir = dir[map[r][c]];
        for (int[] d : curDir) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (isOut(nr, nc)) continue;
            if (nr==curR && nc==curC) return true;
        }
        return false;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
