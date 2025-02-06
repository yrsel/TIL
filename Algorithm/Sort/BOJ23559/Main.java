package Algorithm.Sort.BOJ23559;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Point implements Comparable<Point> {
        int five, one, diff;

        public Point(int five, int one, int diff) {
            this.five = five;
            this.one = one;
            this.diff = diff;
        }

        @Override
        public int compareTo(@NotNull Point o) {
            return Integer.compare(o.diff, this.diff);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int f = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            pq.offer(new Point(f, o, Math.abs(f - o)));
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (max - 5000 >= pq.size() * 1_000 && curr.five > curr.one) {
                max -= 5000;
                sum += curr.five;
            } else {
                max -= 1000;
                sum += curr.one;
            }
        }

        System.out.println(sum);
    }
}
