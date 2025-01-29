package Algorithm.BackTracking.BOJ6443;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static Set<String> hs;
    private static char[] word, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            word = br.readLine().toCharArray();
            result = new char[word.length];
            Arrays.sort(word);
            comb(0, 0);
        }
        System.out.println(sb.toString());
    }

    private static void comb(int idx, int flag) {
        String str = toWord(result);
        if (idx == word.length) {
            if (hs.add(str)) {
                sb.append(str).append('\n');
            }
            return;
        }

        if (hs.contains(str)) return;

        for (int i = 0; i < word.length; i++) {
            if ((flag & (1 << i)) != 0) continue;
            result[idx] = word[i];
            comb(idx + 1, flag | (1 << i));
        }
    }

    private static String toWord(char[] arr) {
        StringBuilder builder = new StringBuilder();
        for (char c : arr) {
            if (c == '\u0000') return "";
            builder.append(c);
        }
        return builder.toString();
    }
}
