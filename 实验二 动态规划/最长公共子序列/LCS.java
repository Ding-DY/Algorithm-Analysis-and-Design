import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LCS {

    private static int[][] arr;   // 用于存储中间计算结果的二维数组
    private static Set<String> lcsSet = new HashSet<>(); // 存储所有的最长公共子序列

    // 计算两个字符串的最长公共子序列的长度
    public static int lcsMax(String s1, String s2) {
        int m = s1.length(); // s1的长度
        int n = s2.length(); // s2的长度
        arr = new int[m + 1][n + 1];  // 初始化二维数组的大小

        // 通过双循环来填充二维数组arr
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 当s1和s2的当前字符相同时
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    arr[i][j] = arr[i - 1][j - 1] + 1; // 取左上角的值加1
                else
                    // 取左边或上边的最大值
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
            }
        }
        return arr[m][n]; // 返回s1和s2的最长公共子序列的长度
    }

    // 输出所有最长公共子序列
    public static void lcsPrint(String s1, String s2, int i, int j, String str) {
        while (i > 0 && j > 0) {
            // 当s1和s2的当前字符相同时
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                i--;
                j--;
                str = s1.charAt(i) + str; // 将字符加到子序列的前面
            } else {
                if (arr[i - 1][j] > arr[i][j - 1])
                    i--;  // 向上移动
                else if (arr[i - 1][j] < arr[i][j - 1])
                    j--;  // 向左移动
                else {
                    // 当存在多个最长公共子序列时，需要递归处理
                    lcsPrint(s1, s2, i - 1, j, str);
                    lcsPrint(s1, s2, i, j - 1, str);
                    return;
                }
            }
        }
        if (!str.isEmpty())
            lcsSet.add(str);  // 如果子序列不为空，则加入到集合中
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第一个字符串:>>");
        String s1 = scanner.nextLine(); // 读取第一个字符串
        System.out.print("请输入第二个字符串:>>");
        String s2 = scanner.nextLine(); // 读取第二个字符串
        int m = s1.length();
        int n = s2.length();
        int len = lcsMax(s1, s2);  // 计算最长公共子序列的长度
        String str = "";
        lcsPrint(s1, s2, m, n, str); // 输出所有的最长公共子序列

        // 输出结果
        if (lcsSet.isEmpty()) {
            System.out.println("两个字符串没有公共子序列");
        } else {
            System.out.println("最长公共子序列的长度是: " + len);
            System.out.println("最长公共子序列是:");
            for (String lcs : lcsSet) {
                System.out.println(lcs);
            }
        }
    }
}
