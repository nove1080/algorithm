package Stack;
// ���� [S4]

// ���� Stack�� ����

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10828_01 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Stack stack = new Stack(n);
        StringTokenizer st;
        while (n-- != 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                int val = Integer.parseInt(st.nextToken());
                stack.push(val);
            } else if (command.equals("pop")) {
                sb.append(stack.pop()).append("\n");
            } else if (command.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (command.equals("empty")) {
                sb.append(stack.isEmpty()).append("\n");
            } else {
                sb.append(stack.top()).append("\n");
            } // if
        } // while
        System.out.println(sb);
    }

    static class Stack {
        // ���� ��
        int pos = 0;
        int[] data = null;

        Stack(int size) {
            data = new int[size];
        }

        int isEmpty() {
            return pos == 0 ? 1 : 0;
        }

        int isFull() {
            return pos == data.length ? 1 : 0;
        }

        void push(int x) {
            if (isFull() == 0) {
                data[pos++] = x;
            }
        }

        int pop() {
            if (isEmpty() == 0) {
                return data[--pos];
            }
            return -1;
        }

        int top() {
            if (isEmpty() == 1) {
                return -1;
            }
            return data[pos - 1];
        }

        int size() {
            return pos;
        }
    }
}