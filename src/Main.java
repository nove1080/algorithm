//16935

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int maxR, maxC;

    static int[][] board;

    static int[] command; //수행할 연산

    /**
     * 4등분 한 지점의 시작점 보관
     * ex) section[0]: 왼쪽 위에 위치한 영역의 좌상단 좌표
     */
    static Point[] section;

    /**
     * 2차원 배열의 맨 위에서 바라본 방향 정보를 저장 <br>
     * <br>
     * index: 2차원 배열의 실제 위치 - 상,우,하,좌 순서<br>
     * value: 실제 위치에 해당하는 가상 위치<br>
     * (0, 상), (1, 우), (2, 하), (3, 좌) 를 의미<br>
     * <br>
     * ex) direction[0] = 1: 2차원 배열의 "상" 은 "하"로 간주한다.
     */
    static int[] direction;

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        print2DArray();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);
        int commandLength = Integer.parseInt(input[2]);

        board = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        //init command
        command = new int[commandLength];
        input = br.readLine().split(" ");
        for (int i = 0; i < commandLength; i++) {
            command[i] = Integer.parseInt(input[i]);
        }

        //init section
        Point leftTop = new Point(0, 0);
        Point rightTop = new Point(0, maxC / 2);
        Point leftBottom = new Point(maxR / 2, 0);
        Point rightBottom = new Point(maxR / 2, maxC / 2);

        section = new Point[]{
                leftTop, rightTop,
                rightBottom, leftBottom
        };

        //init direction
        direction = new int[]{0, 1, 2, 3};
    }

    public static void simulate() {
        for (int c : command) {
            doCommand(c);
        }
    }

    public static void doCommand(int c) {
        System.out.println("DO COMMAND = " + c);

        System.out.println("\tBEFORE COMMAND");
        System.out.print("\t\tDIRECTION = ");
        for (int i = 0; i < 4; i++) {
            System.out.print(direction[i] + " ");
        }
        System.out.println();

        System.out.print("\t\tSECTION = ");
        for (int i = 0; i < 4; i++) {
            System.out.print(section[i] + " ");
        }

        if (c == 1) { //상하 반전
            swap(direction, 0, 2);
        } else if (c == 2) { //좌우 반전
            swap(direction, 1, 3);
        } else if (c == 3) { //오른쪽 90도 회전
            rotateDirection('R');
        } else if (c == 4) { //왼쪽 90도 회전
            rotateDirection('L');
        } else if (c == 5) {
            rotateSection('R');
        } else if (c == 6) {
            rotateSection('L');
        } else {
            throw new IllegalArgumentException();
        }

        System.out.println();
        System.out.println("\tAFTER COMMAND");
        System.out.print("\t\tDIRECTION = ");
        for (int i = 0; i < 4; i++) {
            System.out.print(direction[i] + " ");
        }
        System.out.println();

        System.out.print("\t\tSECTION = ");
        for (int i = 0; i < 4; i++) {
            System.out.print(section[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private static void rotateDirection(char dir) {
        if (dir == 'R') {
            int tmp = direction[direction.length - 1];
            for (int i = direction.length - 1; i > 0; i--) {
                direction[i] = direction[i - 1];
            }
            direction[0] = tmp;
        } else if (dir == 'L') {
            int tmp = direction[0];
            for (int i = 0; i < direction.length - 1; i++) {
                direction[i] = direction[i + 1];
            }
            direction[direction.length - 1] = tmp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void rotateSection(char dir) {
        if (dir == 'R') {
            Point tmp = new Point(section[section.length - 1]);
            for (int i = section.length - 1; i > 0; i--) {
                section[i] = section[i - 1];
            }
            section[0] = tmp;
        } else if (dir == 'L') {
            Point tmp = new Point(section[0]);
            for (int i = 0; i < section.length - 1; i++) {
                section[i] = section[i + 1];
            }
            section[section.length - 1] = tmp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void print2DArray() {
        int top = 0, bottom = 0, left = 0, right = 0;
        for (int i = 0; i < 4; i++) {
            if (direction[i] == 0) top = i;
            if (direction[i] == 1) right = i;
            if (direction[i] == 2) bottom = i;
            if (direction[i] == 3) left = i;
        }

        // CASE 1) top=0, left=3 (기본)
        if (top == 0 && left == 3) {
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 0; k < 2; k++) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + i;
                        int c = section[k].c + j;
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 3; k > 1; k--) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + i;
                        int c = section[k].c + j;
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        // CASE 2) top=0, left=1 (좌우 반전)
        else if (top == 0 && left == 1) {
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 1; k >= 0; k--) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + i;
                        int c = section[k].c + maxC / 2 - 1;
                        sb.append(board[r][c - j]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 2; k < 4; k++) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + i;
                        int c = section[k].c + maxC / 2 - 1;
                        sb.append(board[r][c - j]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        // 90도 회전
        else if (top == 1 && left == 0) {
            for (int i = 0; i < maxC / 2; i++) {
                for (int k = 1; k < 3; k++) {
                    for (int j = 0; j < maxR / 2; j++) {
                        int r = section[k].r + j;
                        int c = section[k].c + (maxC / 2 - 1 - i);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxC / 2; i++) {
                for (int k = 0; k < 4; k += 3) {
                    for (int j = 0; j < maxR / 2; j++) {
                        int r = section[k].r + j;
                        int c = section[k].c + (maxC / 2 - 1 - i);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        else if (top == 1 && left == 2) {
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 2; k < 4; k++) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + (maxC / 2 - 1 - j);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 1; k >= 0; k--) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + (maxC / 2 - 1 - j);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        // CASE 5) top=2, left=3 (상하 반전)
        else if (top == 2 && left == 3) {
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 0; k < 2; k++) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + j;
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 3; k > 1; k--) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + j;
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        // CASE 6) top=2, left=1
        else if (top == 2 && left == 1) {
            for (int i = 0; i < maxR / 2; i++) {
                // 아랫줄: section[2], section[3]
                for (int k = 2; k < 4; k++) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + (maxC / 2 - 1 - j);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            // 예시: "위=하, 왼=우" → 상하 + 좌우 뒤집힘에 가깝게...
            for (int i = 0; i < maxR / 2; i++) {
                // 윗줄: section[1], section[0]
                for (int k = 1; k >= 0; k--) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + (maxC / 2 - 1 - j);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        // CASE 7) top=3, left=0 (270도 회전?)
        else if (top == 3 && left == 0) {
            // 예시: "위=좌, 왼=상" → 270도 시계 회전 정도
            // 윗줄: section[0], section[3]... (임의)
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 0; k <= 3; k += 3) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + j;
                        int c = section[k].c + (maxC / 2 - 1 - i);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxR / 2; i++) {
                for (int k = 1; k <= 2; k += 1) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + j;
                        int c = section[k].c + (maxC / 2 - 1 - i);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        // CASE 8) top=3, left=2
        else if (top == 3 && left == 2) {
            // 예시: "위=좌, 왼=하" → 180도 회전+90도?...
            for (int i = 0; i < maxR / 2; i++) {
                // 윗줄: section[2], section[3]
                for (int k = 2; k < 4; k++) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + (maxC / 2 - 1 - j);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
            for (int i = 0; i < maxR / 2; i++) {
                // 아랫줄: section[1], section[0]
                for (int k = 1; k >= 0; k--) {
                    for (int j = 0; j < maxC / 2; j++) {
                        int r = section[k].r + (maxR / 2 - 1 - i);
                        int c = section[k].c + (maxC / 2 - 1 - j);
                        sb.append(board[r][c]).append(" ");
                    }
                }
                sb.append("\n");
            }
        }

        // 출력
        System.out.println(sb);
    }



    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(Point p) {
            this.r = p.r;
            this.c = p.c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
