package Algorithm.Implementation.BOJ33043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, List<Integer>> mapper = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            List<Integer> value;
            if (mapper.get(name) == null) {
                value = new ArrayList<>();
            } else {
                value = mapper.get(name);
            }
            value.add(i);
            mapper.put(name, value);
        }
        int result = Integer.MAX_VALUE;
        for (Map.Entry<String, List<Integer>> entry : mapper.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.size() <= 4) continue;
            for (int i = 4; i < value.size(); i++) {
                result = Math.min(result, value.get(i) - value.get(i - 4) + 1);
            }
        }

        if (result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);
    }
}
