package Algorithm.BackTracking.BOJ12101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, K, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        solve(0, "");
        if (cnt < K) System.out.println(-1);
    }

    private static void solve(int number, String str) {
        if (number > N) return;

        if (number == N) {
            cnt++;
            if (cnt == K) System.out.println(str.substring(1));
            return;
        }

        solve(number + 1, str + "+1");
        solve(number + 2, str + "+2");
        solve(number + 3, str + "+3");
    }
}
