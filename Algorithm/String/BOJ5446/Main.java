package Algorithm.String.BOJ5446;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final int SIZE = 64;

    static int ctoi(char a) {
        if (a == '.') return 62;
        if (Character.isDigit(a)) { // 0~9
            return a - '0';
        }
        int num = a - 'A';
        if (num >= 26) return a - 'a' + 10;
        return num + 36;
    }

    static class Trie {
        Trie[] child;
        boolean end;
        boolean availDelete;

        public Trie() {
            child = new Trie[SIZE];
            end = false;
            availDelete = true;
        }

        public void insert(String str) {
            Trie tmp = this;
            for (int i = 0; i < str.length(); i++) {
                int cur = ctoi(str.charAt(i));
                if (tmp.child[cur] == null) {
                    tmp.child[cur] = new Trie();
                }
                tmp = tmp.child[cur];
            }
            tmp.end = true;
        }

        public void markNotDelete(String str) {
            Trie tmp = this;

            for (int i = 0; i < str.length(); i++) {
                int cur = ctoi(str.charAt(i));
                if (tmp.child[cur] == null) {
                    return;
                }
                tmp.child[cur].availDelete = false;
                tmp = tmp.child[cur];
            }
        }

        public int getAns() {
            Trie curr = this;
            int count = 0;

            for (int i = 0; i < SIZE; i++) {
                if (curr.child[i] != null) {
                    if (curr.child[i].end && !curr.child[i].availDelete) {
                        count++;
                    }
                    if (curr.child[i].availDelete) {
                        count++;
                    } else {
                        count += curr.child[i].getAns();
                    }
                }
            }
            return count;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int T = 0; T < t; T++) {
            int removeFileCount = Integer.parseInt(br.readLine());

            Trie root = new Trie();

            for (int i = 0; i < removeFileCount; i++) {
                String trieInput = br.readLine();
                root.insert(trieInput);
            }

            int cantRemoveFileCount = Integer.parseInt(br.readLine());

            for (int i = 0; i < cantRemoveFileCount; i++) {
                String notDeleteName = br.readLine();
                root.markNotDelete(notDeleteName);
            }

            boolean flag = true;
            for (int i = 0; i < SIZE; i++) {
                if (root.child[i] != null && !root.child[i].availDelete) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println(1);
                continue;
            }

            System.out.println(root.getAns());
        }
    }
}