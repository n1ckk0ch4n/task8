package cs.vsu.ru.goryacheva.task8;
import java.util.Arrays;
public class FindFriendlyElements {
    public static int[][] solution(int[][] A) {

        int rows = A.length;
        int col= A[0].length;
        int[][] B = new int[rows][col];
        int[][] Temp = new int[rows][col];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(Temp[i], 0);
            Arrays.fill(B[i], 0);
        }

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < col; x++) {
                if (Temp[y][x] == 0) {
                    int cnt = ElemCount(A, Temp, rows, col, y, x, A[y][x]);
                    ElemFill(A, Temp, B, rows, col, y, x, A[y][x], cnt - 1);
                }
            }
        }
        return B;
    }

    private static int ElemCount(int[][] A, int[][] Temp, int rows, int col, int y, int x, int val) {
        if (y < 0 || x < 0 || x == col|| y == rows || Temp[y][x] > 0 || A[y][x] != val) {
            return 0;
        }
        Temp[y][x] = 1;
        int result = 1;
        result += ElemCount(A, Temp, rows, col, y - 1, x, val);
        result += ElemCount(A, Temp, rows, col, y, x - 1, val);
        result += ElemCount(A, Temp, rows, col, y + 1, x, val);
        result += ElemCount(A, Temp, rows, col, y, x + 1, val);
        return result;
    }

    private static void ElemFill(int[][] A, int[][] Temp, int[][] B, int rows, int col, int y, int x, int val, int fval) {
        if (y < 0 || x < 0 || x == col|| y == rows || Temp[y][x] == 2 || A[y][x] != val) {
            return;
        }
        Temp[y][x] = 2;
        B[y][x] = fval;
        ElemFill(A, Temp, B, rows, col, y - 1, x, val, fval);
        ElemFill(A, Temp, B, rows, col, y, x - 1, val, fval);
        ElemFill(A, Temp, B, rows, col, y + 1, x, val, fval);
        ElemFill(A, Temp, B, rows, col, y, x + 1, val, fval);
    }
}
