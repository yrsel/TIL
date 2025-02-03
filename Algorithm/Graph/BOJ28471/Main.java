package Algorithm.Graph.BOJ28471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static final int[][] dir = {{-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {-1, 0}, {1, 1}};
    private static char[][] map;
    private static boolean[][] visited;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        int[] start = new int[2];
        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = arr[j];
                if (map[i][j] == 'F') start = new int[]{i, j};
            }
        }

        System.out.println(bfs(start));
    }

    private static int bfs(int[] start) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[start[0]][start[1]] = true;
        q.offer(start);
        int cnt = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 7; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] != '.') continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
