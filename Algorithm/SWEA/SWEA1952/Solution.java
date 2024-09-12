package Algorithm.SWEA.SWEA1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer;
    private static int[] months, costs;

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            costs = getInput(4); // 일 , 월 , 3달, 년 요금제
            months = getInput(12); // 1~12월

            answer = costs[3];
            search(0, 0);

            result.append(String.format("#%d %d", tc, answer)).append('\n');
        }
        System.out.println(result);
    }

    private static void search(int month, int cost) {
        if (month > 12) return;
        if (month==12) {
            answer = Math.min(answer, cost);
            return;
        }

        if (months[month]==0) {
            search(month + 1, cost);
        }

        search(month + 1, Math.min(cost + costs[1], cost + costs[0] * months[month]));
        search(month + 3, cost + costs[2]);
    }

    private static int[] getInput(int size) throws IOException {
        int[] arr = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }
}
