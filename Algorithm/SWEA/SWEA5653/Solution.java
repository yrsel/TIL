package Algorithm.SWEA.SWEA5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    private static int[][] map;
    private static final int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static PriorityQueue<Cell> cells;
    private static Set<Integer> dead, alive;

    private static class Cell implements Comparable<Cell> {
        int r, c, originTime, remainTime;
        boolean isActive;

        public Cell(int r, int c, int originTime, int remainTime, boolean isActive) {
            this.r = r;
            this.c = c;
            this.originTime = originTime;
            this.remainTime = remainTime;
            this.isActive = isActive;
        }

        @Override
        public int compareTo(Cell o) {
            return Integer.compare(o.originTime, this.originTime);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            map = new int[360][360];
            cells = new PriorityQueue<>();
            dead = new HashSet<>();
            alive = new HashSet<>();

            st = new StringTokenizer(br.readLine(), " ");
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            for (int r = 150; r < 150 + R; r++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int c = 150; c < 150 + C; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c]!=0) {
                        cells.offer(new Cell(r, c, map[r][c], map[r][c], false));
                        alive.add(r * 360 + c);
                    }
                }
            }

            while (K-- > 0) {
                spread();
            }

            sb.append(String.format("#%d %d\n", tc, cells.size()));
        }

        System.out.println(sb);
    }

    private static void spread() {
        Set<Integer> visited = new HashSet<>();
        List<Cell> memo = new ArrayList<>();

        while (!cells.isEmpty()) {
            Cell curr = cells.poll();
            if (curr.isActive) { // 활성 상태
                if (curr.remainTime==curr.originTime) { // 활성화 된 직후 ( 퍼트리기 )
                    for (int d = 0; d < 4; d++) {
                        int nr = curr.r + dir[d][0];
                        int nc = curr.c + dir[d][1];
                        int pos = nr * 360 + nc;
                        if (dead.contains(pos) || alive.contains(pos)) continue;
                        if (visited.add(pos)) {
                            alive.add(pos);
                            memo.add(new Cell(nr, nc, curr.originTime, curr.originTime, false));
                        }
                    }
                }

                if (curr.remainTime!=1) {
                    memo.add(new Cell(curr.r, curr.c, curr.originTime, curr.remainTime - 1, true));
                } else {
                    alive.remove(curr.r * 360 + curr.c);
                    dead.add(curr.r * 360 + curr.c);
                }
            } else { // 비활성 상태
                if (curr.remainTime==1) {
                    memo.add(new Cell(curr.r, curr.c, curr.originTime, curr.originTime, true));
                } else {
                    memo.add(new Cell(curr.r, curr.c, curr.originTime, curr.remainTime - 1, false));
                }
            }
        }
        cells.addAll(memo);
    }
}
