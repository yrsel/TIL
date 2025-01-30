package Algorithm.DataStructure.BOJ19700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static class Point {
        int h, level;

        Point(int h, int level) {
            this.h = h;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Point> candidates = new PriorityQueue<>((o1, o2) -> o2.h - o1.h);
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            candidates.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        TreeMap<Integer, Integer> counter = new TreeMap<>();

        while (!candidates.isEmpty()) {
            Point curr = candidates.poll();
            Integer level = counter.lowerKey(curr.level);
            if (level == null) {
                counter.put(1, counter.getOrDefault(1, 0) + 1);
            } else {
                counter.put(level, counter.getOrDefault(level, 0) - 1);
                if (counter.get(level) == 0) counter.remove(level);
                counter.put(level + 1, counter.getOrDefault(level + 1, 0) + 1);
            }
        }

        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            sum += entry.getValue();
        }

        System.out.println(sum);
    }
}