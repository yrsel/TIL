package Algorithm.BinarySearch.BOJ9024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9024 {

    private static int[] arr;
    private static int N, K;
    private static int minDiff;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            minDiff = Integer.MAX_VALUE;
            result = 1;
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            for (int i = 0; i < N - 1; i++) {
                search(i, N - 1);
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }

    private static void search(int left, int right) {
        int selectNum = arr[left];
        left++;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cur = selectNum + arr[mid];
            if (K > cur) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            comp(Math.abs(K - cur));
        }
    }

    private static void comp(int num) {
        if (minDiff > num) {
            minDiff = num;
            result = 1;
        } else if (minDiff==num) {
            result++;
        }
    }
}
