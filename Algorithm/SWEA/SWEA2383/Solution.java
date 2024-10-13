package Algorithm.SWEA.SWEA2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    private static StreamTokenizer st;
    private static int peopleCnt;
    private static int result;
    private static int[][] distance;
    private static int[][] stairs;
    private static int[] selected;

    private static int input() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();
        int T = input();
        for (int tc = 1; tc <= T; tc++) {
            int n = input();
            result = Integer.MAX_VALUE;
            stairs = new int[2][3]; // 계단 [0][] , [1][] 2군데 , [][0,1,2] : r,c,time
            int[][] map = new int[n][n];
            int stairIdx = 0;
            peopleCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = input();
                    if (map[i][j]!=0 && map[i][j]!=1) {
                        stairs[stairIdx][0] = i;
                        stairs[stairIdx][1] = j;
                        stairs[stairIdx++][2] = map[i][j];
                    }
                    if (map[i][j]==1) peopleCnt++;
                }
            }

            distance = new int[peopleCnt][2]; // 계단과의 거리,  [][0] : stairs[0][]과의 거리, [][1] : stairs[1][]과의 거리
            int peopleIdx = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j]==1) {
                        distance[peopleIdx][0] = Math.abs(i - stairs[0][0]) + Math.abs(j - stairs[0][1]);
                        distance[peopleIdx++][1] = Math.abs(i - stairs[1][0]) + Math.abs(j - stairs[1][1]);
                    }
                }
            }

            selected = new int[peopleCnt];
            searchAllCase(0);
            sb.append(String.format("#%d %d\n", tc, result));
        }

        System.out.println(sb);
    }

    private static void searchAllCase(int idx) {
        if (peopleCnt==idx) {
            int stairA = calculateStair(stairs[0][2], getTimes(0));
            int stairB = Math.max(stairA, calculateStair(stairs[1][2], getTimes(1)));
            result = Math.min(result, stairB);
            return;
        }

        selected[idx] = 0;
        searchAllCase(idx + 1);
        selected[idx] = 1;
        searchAllCase(idx + 1);
    }

    private static PriorityQueue<Integer> getTimes(int stair) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]==stair) {
                pq.add(distance[i][stair]);
            }
        }
        return pq;
    }

    private static int calculateStair(int downTime, PriorityQueue<Integer> candidates) {
        if (candidates.isEmpty()) return 0;
        int curTime = candidates.peek();
        Queue<Integer> workingEndTimes = new ArrayDeque<>();
        Queue<Integer> waitingQueue = new ArrayDeque<>();

        while (true) {
            while (!workingEndTimes.isEmpty()) { // 작업 완료되었으면 꺼내주기
                if (workingEndTimes.peek()==curTime){
                    workingEndTimes.poll();
                } else{
                    break;
                }
            }

            if (workingEndTimes.size()!=3) {
                while (!waitingQueue.isEmpty()) { // 기다리고 있는 작업 먼저 체크
                    waitingQueue.poll();
                    workingEndTimes.offer(curTime + downTime);
                    if (workingEndTimes.size()==3) break;
                }
            }

            while (!candidates.isEmpty()) { // 후보 체크

                if (candidates.peek() > curTime) break; // 현재 후보들로는 할 수 있는 게 없다.
                candidates.poll();

                if (workingEndTimes.size()!=3) { // 웨이팅도 확인했는데 아직 작업할 수 있는 상태라면
                    workingEndTimes.offer(curTime + 1 + downTime); // 1초 대기를 포함한 내려가는 시간 넣어주기
                } else { // 웨이팅에 대기 시키기
                    waitingQueue.offer(1);
                }
            }

            if (candidates.isEmpty() && workingEndTimes.isEmpty() && waitingQueue.isEmpty()) break;
            ++curTime;
        }

        return curTime;
    }
}
