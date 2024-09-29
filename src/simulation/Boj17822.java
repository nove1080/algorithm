//원판 돌리기
//20:20 ~
package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17822 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, t;
    static Circles circles;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < t; i++) {
            String[] input = br.readLine().split(" ");
            int multiple = Integer.parseInt(input[0]);
            int dir = Integer.parseInt(input[1]);
            int attempt = Integer.parseInt(input[2]);

            circles.rotate(multiple, dir, attempt);
        }

        printCircles(circles);
        System.out.println("=================");
        System.out.println(circles.sum());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        t = Integer.parseInt(input[2]);

        Circle[] temp = new Circle[n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int[] numbers = new int[m];
            for (int j = 0; j < m; j++) {
                numbers[j] = Integer.parseInt(input[j]);
            }
            temp[i] = new Circle(0, numbers);
        }

        circles = new Circles(temp);
    }

    static class Circles {
        Circle[] circles; //0-indexed
        int size;

        public Circles(Circle[] circles) {
            this.circles = circles;
            this.size = circles.length;
        }

        public void rotate(int multiple, int dir, int attempt) {
            for (int i = 0; i < size; i++) {
                if((i + 1) % multiple == 0) {
                    circles[i].rotate(dir, attempt);
                }
            }

            boolean hasBeenDeleted = removeAdjacentRow();
            for (int i = 0; i < size; i++) {
                if(removeAdjacentCol(i)) {
                    hasBeenDeleted = true;
                }
            }

            if(!hasBeenDeleted) {
                modifyNumbers();
            }
        }

        public void modifyNumbers() {
            //평균 구하기
            int count = 0;
            int sum = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < circles[i].size; j++) {
                    if(circles[i].numbers[j] != 0) {
                        count++;
                        sum += circles[i].numbers[j];
                    }
                }
            }

            if(count <= 0) return;
            double average = (double) sum / count;

            //수 보정하기
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < circles[i].size; j++) {
                    if(circles[i].numbers[j] != 0) {
                        if(circles[i].numbers[j] > average) circles[i].numbers[j]--;
                        else if (circles[i].numbers[j] < average) circles[i].numbers[j]++;
                    }
                }
            }
        }

        public int sum() {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < circles[i].size; j++) {
                    sum += circles[i].numbers[j];
                }
            }

            return sum;
        }

        public boolean removeAdjacentRow() {
            boolean hasBeenDeleted = false;
            //(i, j)에서 i가 같은 경우
            for (Circle circle : circles) {
                if(circle.removeAdjacentRow()) {
                    hasBeenDeleted = true;
                }
            }

            return hasBeenDeleted;
        }

        public boolean removeAdjacentCol(int col) {
            boolean hasBeenDeleted = false;
            //(i, j)에서 j가 같은 경우
            int prev = 0;
            int now = 0;
            boolean isPrevRemovable = false;

            int savedCol = col;
            prev = circles[0].numbers[getCol(col, 0)];
            for (int i = 1; i < size; i++) {

                col = getCol(savedCol, i);

                if (isPrevRemovable) {
                    hasBeenDeleted = true;
                    circles[i - 1].numbers[col] = 0;
                    isPrevRemovable = false;
                }

                now = circles[i].numbers[col];
                if (prev == now) {
                    isPrevRemovable = true;
                }
                prev = now;
            }

            if (isPrevRemovable) {
                hasBeenDeleted = true;
                circles[size - 1].numbers[col] = 0;
            }

            return hasBeenDeleted;
        }

        private int getCol(int col, int i) {
            int result = 0;
            if (circles[i].pos + col - 1 >= circles[i].size - 1) {
                result = col + circles[i].pos - size - 1;
            } else {
                result = col + circles[i].pos;
            }
            return result;
        }

    }

    static class Circle {
        int pos; //현재 위치
        int size; //numbers의 크기
        int[] numbers;

        public Circle(int pos, int[] numbers) {
            this.pos = pos;
            this.numbers = numbers;
            size = numbers.length;
        }

        //원판 회전
        public void rotate(int dir, int attempt) {
            attempt %= size;
            if(dir == 0) { //시계 방향
                if(pos - attempt < 0) {
                    pos = size + pos - attempt;
                } else {
                    pos -= attempt;
                }
            } else { //반시계 방향
                if (pos + attempt > size - 1) {
                    pos = pos + attempt - size;
                } else {
                    pos += attempt;
                }
            }
        }

        //idx 기준 인접한 숫자를 제거
        public boolean removeAdjacentRow() {
            boolean hasBeenDeleted = false;
            //(i, j)에서 i가 같은 경우 인접한 j끼리 중복 제거
            int prev = 0;
            int now = 0;
            boolean isPrevRemovable = false;
            if (size > 1) {
                if(numbers[size - 1] == numbers[0]) {
                    numbers[size - 1] = 0;
                    isPrevRemovable = true;
                }
            }
            prev = numbers[0];
            for (int i = 1; i < size; i++) {
                if(isPrevRemovable) {
                    hasBeenDeleted = true;
                    numbers[i - 1] = 0;
                    isPrevRemovable = false;
                }

                now = numbers[i];
                if(now == prev) {
                    isPrevRemovable = true;
                }

                prev = now;
            }

            if (isPrevRemovable) {
                hasBeenDeleted = true;
                numbers[size - 1] = 0;
            }

            return hasBeenDeleted;
        }
    }

    static void printCircles(Circles circles) {
        System.out.println("==== printCircles ====");
        for (int i = 0; i < n; i++) {
            System.out.print("R = " + (n-i) + "  ");
            for (int j = 0; j < n; j++) {
                System.out.print(circles.circles[i].numbers[j] + " -> ");
            }
            System.out.println();
        }
    }
}

//4 4 2
//1 1 2 3
//5 2 4 2
//3 1 3 5
//2 1 3 2
//2 0 1
//3 1 3
