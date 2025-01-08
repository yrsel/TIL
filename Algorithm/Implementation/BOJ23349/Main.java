package Algorithm.Implementation.BOJ23349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> hs = new HashSet<>();
        Map<String, int[]> hm = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            String place = st.nextToken();
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (hs.add(name)) {
                int[] value;
                if (hm.get(place) == null) {
                    value = new int[50_002];
                } else {
                    value = hm.get(place);
                }
                value[from]++;
                value[to]--;
                hm.put(place, value);
            }
        }
        String findPlace = "";
        int findStartTime = 0;
        int maxCnt = 0;
        for (Map.Entry<String, int[]> entry : hm.entrySet()) {
            int[] times = entry.getValue();
            int time = 0;
            int cnt = 0;
            for (int i = 1; i < 50_002; i++) {
                times[i] += times[i - 1];
                if (cnt < times[i]) {
                    time = i;
                    cnt = times[i];
                }
            }
            if (findPlace.isEmpty() || maxCnt < cnt || (findPlace.compareTo(entry.getKey()) > 0 && maxCnt == cnt)) {
                findPlace = entry.getKey();
                maxCnt = cnt;
                findStartTime = time;
                hm.put(entry.getKey(), times);
            }
        }
        int[] value = hm.get(findPlace);
        int endTime;
        for (int i = findStartTime; ; i++) {
            if (value[i] != maxCnt) {
                endTime = i;
                break;
            }
        }
        System.out.println(findPlace + " " + findStartTime + " " + endTime);
    }
}
