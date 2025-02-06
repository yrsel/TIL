package Algorithm.BruteForce.BOJ16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static char[] selected, A;
    private static int B;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = st.nextToken().toCharArray();
        String goal = st.nextToken();
        B = Integer.parseInt(goal);
        selected = new char[A.length];
        Arrays.sort(A);
        result = -1;
        if (A.length <= goal.length()) comb(0, 0);
        System.out.println(result);
    }

    private static void comb(int idx, int flag) {
        if (result != -1) return;

        if (idx == A.length) {
            if (selected[0] == '0') return;
            int num = 0;
            for (int i = 0; i < A.length; i++) {
                num = num * 10 + (selected[i] - '0');
            }
            if (num < B) {
                result = num;
            }
            return;
        }

        for (int i = 0; i < A.length; i++) {
            if ((flag & (1 << i)) != 0) continue;
            selected[idx] = A[A.length - 1 - i];
            comb(idx + 1, flag | (1 << i));
        }

    }
}
