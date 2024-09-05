package Algorithm.BinarySearch.BOJ2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {

    private static long closedZero = Long.MAX_VALUE;
    private static final int[] result = new int[3];
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 2; j < N; j++) {
                bs(i, j);
            }
        }
        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
        br.close();
    }

    private static void bs(int left, int right) {
        int L = left + 1;
        int R = right - 1;
        int sum = arr[left] + arr[right];

        while (L <= R) {
            int mid = (L + R) / 2;
            compareResult(arr[left], arr[right], arr[mid]);
            if (Math.abs(sum + arr[mid + 1]) > Math.abs(sum + arr[mid - 1])) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
    }

    private static void compareResult(int a, int b, int c) {
        long sum = Math.abs((long) a + b + c);
        if (closedZero > sum) {
            result[0] = a;
            result[1] = b;
            result[2] = c;
            closedZero = sum;
        }
    }
}
