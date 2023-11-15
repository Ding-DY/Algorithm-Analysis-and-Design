
import java.util.Random;
import java.util.Scanner;

public class Bag {
    private static int bestv = 0; // 最大价值
    private static int[] v; // 各物品的价值
    private static int[] w; // 各物品的重量
    private static int[] x; // 当前解
    private static int[] bestx; // 最佳解

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("请输入物品数量：");
        int num = scanner.nextInt();
        while (num <= 0) {
            System.out.println("请重新输入正确的物品数量：");
            num = scanner.nextInt();
        }

        System.out.println("请输入背包的最大重量：");
        int weight = scanner.nextInt();
        while (weight <= 0) {
            System.out.println("请重新输入正确的背包重量：");
            weight = scanner.nextInt();
        }

        // 根据用户输入的物品数量分配数组空间
        v = new int[num + 1];
        w = new int[num + 1];
        x = new int[num + 1];
        bestx = new int[num + 1];

        System.out.println("系统随机生成每件物品的重量和价值：");
        for (int i = 1; i <= num; i++) {
            w[i] = random.nextInt(weight) + 1; // 生成1到背包最大重量的随机重量
            v[i] = random.nextInt(100) + 1; // 生成1到100的随机价值
            System.out.println("物品 " + i + "：重量=" + w[i] + ", 价值=" + v[i]);
        }

        backtrack(num, weight, 1, 0, 0);

        System.out.println("最大价值为：" + bestv);
        System.out.print("选择的物品为：");
        for (int i = 1; i <= num; i++) {
            if (bestx[i] == 1) {
                System.out.print(i + " ");
            }
        }
    }

    private static void backtrack(int num, int weight, int i, int cv, int cw) {
        if (i > num) { // 到达叶节点
            if (cv > bestv) { // 如果当前价值大于最优价值
                bestv = cv;
                for (int k = 1; k <= num; k++) {
                    bestx[k] = x[k]; // 更新最佳解
                }
            }
        } else {
            for (int j = 0; j <= 1; j++) { // 两种选择，0或1，不放入或放入
                x[i] = j; // 设置当前解
                if (cw + x[i] * w[i] <= weight) { // 判断是否超过背包容量
                    backtrack(num, weight, i + 1, cv + v[i] * x[i], cw + w[i] * x[i]); // 递归调用
                }
            }
        }
    }
}
