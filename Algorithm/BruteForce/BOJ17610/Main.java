package Algorithm.BruteForce.BOJ17610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[13 * 200_001];
        int[] weights = new int[k];
        S = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            S += weights[i];
        }

        setData(0, weights, 0, visited);
        int cnt = 0;
        for (int i = 1; i <= S; i++) {
            if (!visited[i]) cnt++;
        }
        System.out.println(cnt);
    }

    private static void setData(int idx, int[] weights, int sum, boolean[] visited) {
        if (idx == weights.length) {
            if (sum > 0 && sum <= S) visited[sum] = true;
            return;
        }

        setData(idx + 1, weights, sum + weights[idx], visited);
        setData(idx + 1, weights, Math.abs(sum - weights[idx]), visited);
        setData(idx + 1, weights, sum, visited);
    }
}
