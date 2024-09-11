package Algorithm.SWEA.SWEA5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken()) - 1;

            List<Long> list = new ArrayList<>();
            int loop = N / 4;
            int idx = 0;

            char[] arr = br.readLine().toCharArray();
            StringBuilder curNum = new StringBuilder();
            while (idx!=loop) {
                int cnt = 1;
                while (true) {
                    curNum.append(arr[idx]);
                    if (cnt % loop==0) {
                        calculate(list, curNum.toString());
                        curNum = new StringBuilder();
                    }

                    idx = (idx + 1) % N;
                    if (cnt++==N) break;
                }
                idx++;
            }

            list.sort(Collections.reverseOrder());

            result.append(String.format("#%d %d\n", tc, list.get(K)));
        }

        System.out.println(result);
    }

    private static void calculate(List<Long> list, String strNum) {
        long num = 0L;
        long multiple = 16L;
        for (int i = strNum.length() - 1; i >= 0; i--) {
            int cur = strNum.charAt(i);
            if ('0' <= cur && cur <= '9') {
                num += (cur - '0') * (long) Math.pow(multiple, strNum.length() - 1 - i);
            } else {
                num += (cur - 'A' + 10) * (long) Math.pow(multiple, strNum.length() - 1 - i);
            }
        }
        if (list.contains(num)) return;
        list.add(num);
    }
}
