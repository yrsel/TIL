package Algorithm.SWEA.SWEA2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    private static int[][] map;
    private static Set<Integer> hs;
    private static int N, max;
    private static final StringBuilder result = new StringBuilder();
    private static final int[][] dir = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            max = -1;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            hs = new HashSet<>();
            for (int r = 0; r < N - 2; r++) {
                for (int c = 1; c < N - 1; c++) {
                    hs.add(map[r][c]);
                    dfs(r, c, r, c, 0);
                    hs.remove(map[r][c]);
                }
            }

            result.append(String.format("#%d %d\n", tc, max));
        }
        System.out.println(result);
    }

    private static void dfs(int originR, int originC, int curR, int curC, int curDir) {
        for (int d = curDir; d < 4; d++) {
            int nr = curR + dir[d][0];
            int nc = curC + dir[d][1];
            if (isIn(nr, nc)) {
                if (nr==originR && nc==originC && hs.size() >= 4) {
                    max = Math.max(max, hs.size());
                    return;
                }
                if (hs.add(map[nr][nc])) {
                    dfs(originR, originC, nr, nc, d);
                    hs.remove(map[nr][nc]);
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
