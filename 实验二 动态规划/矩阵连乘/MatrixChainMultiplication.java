
import java.util.Scanner;  // 引入Java的Scanner类，用于从控制台读取用户输入

public class MatrixChainMultiplication {

    private static final int N = 100;  // 定义矩阵的最大数量为100

    /**
     * 使用动态规划算法来求解矩阵链乘法的最优解
     * p: 矩阵链的尺寸
     * m: 保存乘法的最小次数
     * s: 保存最优解的分割位置
     */
    public static void matrixChain(int[] p, int[][] m, int[][] s) {
        int n = p.length - 1;  // 获取矩阵数量

        // 对角线上的元素初始化为0
        for (int i = 1; i <= n; i++)
            m[i][i] = 0;

        // 外循环: l代表当前处理的链长度
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j];
                s[i][j] = i;

                // 内循环: 尝试所有可能的两部分分割
                for (int k = i + 1; k < j; k++) {
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    // 更新乘法的最小次数和分割位置
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    /**
     * 递归地回溯并打印最优矩阵乘法的顺序
     */
    public static void traceback(int i, int j, int[][] s) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            traceback(i, s[i][j], s);  // 打印左侧的子链
            traceback(s[i][j] + 1, j, s);  // 打印右侧的子链
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入矩阵的数量（数量 <= 100）:\n>>");
        int n = scanner.nextInt();

        int[] Q = new int[2 * N];
        int[] p = new int[n + 1];
        int flag = 1;
        int[][] m = new int[N + 1][N + 1];
        int[][] s = new int[N + 1][N + 1];

        // 从用户处获取每个矩阵的行和列
        for (int i = 0; i <= 2 * n - 1; i++) {
            System.out.print(i % 2 == 0 ? "请输入矩阵A" + ((i / 2) + 1) + "的行数:\n>>" :
                    "请输入矩阵A" + ((i / 2) + 1) + "的列数:\n>>");
            Q[i] = scanner.nextInt();
        }

        // 验证连续两个矩阵是否可以相乘
        for (int i = 1; i <= 2 * n - 2; i++) {
            if (i % 2 != 0 && Q[i] != Q[i + 1]) {
                flag = 0;
                break;
            }
        }

        // 初始化p数组
        for (int j = 1; j <= n - 1; j++) {
            p[j] = Q[2 * j];
        }

        if (flag != 0) {
            p[0] = Q[0];
            p[n] = Q[2 * n - 1];

            matrixChain(p, m, s);  // 执行矩阵链乘法算法

            System.out.print("最优乘法公式是:\t");
            traceback(1, n, s);  // 打印最优乘法的顺序
            System.out.println("\n最少乘法次数是:\t" + m[1][n]);
        } else {
            System.out.println(n + "个矩阵不能进行乘法操作");
        }
        scanner.close();
    }
}

