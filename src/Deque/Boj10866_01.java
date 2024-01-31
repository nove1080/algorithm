import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �� [S4]

// solution
//  1. Deque�� �迭�� ����
//  2. ���� : Deque�� stack, queue�� �޸� �������� Ȯ���� �� �ֱ⿡
//            �迭�� ũ�⸦ �˳��� ��� head,tail�� �߰������� �����ϴ� ���� ����.
public class Boj10866_01 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        MyDeque d = new MyDeque(10000);
        StringTokenizer st;
        int last = 0; // ������ ���� ��� (back ����)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push_front":
                    int x = Integer.parseInt(st.nextToken());
                    d.push_front(x);
                    break;
                case "push_back":
                    int y = Integer.parseInt(st.nextToken());
                    d.push_back(y);
                    break;
                case "pop_front":
                    sb.append(d.pop_front()).append("\n");
                    break;
                case "pop_back":
                    sb.append(d.pop_back()).append("\n");
                    break;
                case "size":
                    sb.append(d.size()).append("\n");
                    break;
                case "empty":
                    sb.append(d.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(d.front()).append("\n");
                    break;
                case "back":
                    sb.append(d.back()).append("\n");
                    break;
            } // swtich
        } // for

        System.out.println(sb);
    }

    static class MyDeque {
        int data[];
        int head, tail;

        MyDeque(int size) {
            data = new int[size * 2 + 1];
            head = data.length / 2;
            tail = head + 1;
        }

        boolean isEmpty() {
            return (tail - head) == 1;
        }

        void push_front(int x) {
            data[head--] = x;
        }

        void push_back(int x) {
            data[tail++] = x;
        }

        int pop_front() {
            return isEmpty() ? -1 : data[++head];
        }

        int pop_back() {
            return isEmpty() ? -1 : data[--tail];
        }

        int size() {
            return tail - head - 1;
        }

        int front() {
            return isEmpty() ? -1 : data[head + 1];
        }

        int back() {
            return isEmpty() ? -1 : data[tail - 1];
        }
    }

}
