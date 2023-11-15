
import java.util.Random;
import java.util.Scanner;

public class LinearTimeSelection {

    public static void Sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = Spartition(arr, low, high);

            // 递归地排序分区的元素
            Sort(arr, low, pi - 1);
            Sort(arr, pi + 1, high);
        }
    }

    // 快速排序的辅助函数
    private static int Spartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // 小于枢轴元素的索引
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                // 交换arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 交换 arr[i+1] 和 arr[high]（或枢轴）
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 用于重新排列数组的方法
    public static int partition(int[] arr, int p, int q, int mid) {
        int i = p, j = q;
        // 当i和j未相遇时，循环进行
        while (i <= q && j >= p) {
            // 在左侧找到第一个大于mid的元素
            while (arr[i] < mid) {
                i++;
            }
            // 在右侧找到第一个小于mid的元素
            while (arr[j] > mid) {
                j--;
            }
            // 如果i和j未相遇
            if (i >= j)
                break;
            else {
                // 交换arr[i]和arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return j;
    }

    // 选择算法的主体函数
    public static int select(int[] arr, int p, int q, int k) {
        // 当子数组大小小于等于75时，直接排序并返回第k小的元素
        if (q - p < 75) {
            Sort(arr, p, q);
            return arr[p + k - 1];
        }
        // 将arr[p..q]划分为n/5个组，每组5个元素，并对每组进行排序
        for (int i = 0; i <= (q - p - 4) / 5; i++) {
            Sort(arr, p + 5 * i, p + 5 * i + 4);
            // 将每组的中位数与arr[p..p+n/5]中相应的元素交换位置
            int temp = arr[p + 5 * i + 2];
            arr[p + 5 * i + 2] = arr[p + i];
            arr[p + i] = temp;
        }
        // 递归调用选择算法找到中位数的中位数mid
        int mid = select(arr, p, p + (q - p - 4) / 5, ((q - p - 4) / 5 + 1) / 2);
        // 使用mid将数组划分为两部分
        int midId = partition(arr, p, q, mid);
        int midRank = midId - p + 1;
        // 递归处理找到的部分
        if (k == midRank) {
            return arr[midId];
        } else if (k < midRank) {
            return select(arr, p, midId, k);
        } else {
            return select(arr, midId + 1, q, k - midRank);
        }
    }

    // 主函数
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("请输入数组的长度:");
        int num = scanner.nextInt();

        // 输入的合法性检查
        while (num <= 0) {
            System.out.print("请输入正确的数字:");
            num = scanner.nextInt();
        }

        // 使用随机数生成数组
        int[] arr = new int[num];
        System.out.println("生成的随机数数组:");
        for (int i = 0; i < num; i++) {
            arr[i] = random.nextInt(1000);  // 1000是随机数生成的上限
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.print("请输入你想找到的第i小的数字i的值:");
        int k = scanner.nextInt();

        // 输入的合法性检查
        while (k > num) {
            System.out.print("无法找到数组中序号大于数组大小的数字，请重新输入:");
            k = scanner.nextInt();
        }

        // 运行选择算法
        int res = select(arr, 0, num - 1, k);


        System.out.println("第" + k + "小的数字是: " + res);
    }
}
