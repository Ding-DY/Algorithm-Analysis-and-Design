import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinarySearch {

    // 使用分治法的二分查找算法实现
    public static int binarySearch(int[] arr, int start, int end, int key) {
        // 基本情况：如果数组部分没有元素，返回-1
        if (start > end) {
            return -1;
        }

        // 计算中间索引
        int mid = start + (end - start) / 2;

        // 如果中间元素是目标键，返回其索引
        if (arr[mid] == key) {
            return mid;
        }
        // 如果中间元素大于目标键，在左半部分递归查找
        else if (arr[mid] > key) {
            return binarySearch(arr, start, mid - 1, key);
        }
        // 如果中间元素小于目标键，在右半部分递归查找
        else {
            return binarySearch(arr, mid + 1, end, key);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int num;

        // 循环直到用户输入一个正数
        while (true) {
            System.out.print("请输入数组的长度:");
            num = scanner.nextInt();
            if (num > 0) {
                break;
            } else {
                System.out.println("请输入一个正确的数字。");
            }
        }

        // 创建并用随机数填充数组
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = random.nextInt(10000);
        }

        // 排序数组并打印
        Arrays.sort(arr);
        System.out.println("创建并用随机数填充数组");
        System.out.print("排序后的数组:");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        // 要求用户输入一个要搜索的数字
        System.out.print("\n请输入一个要搜索的数字:");
        int k = scanner.nextInt();

        // 执行二分查找
        int res = binarySearch(arr, 0, num - 1, k);

        // 如果没有找到，打印相应消息；否则打印索引
        if (res == -1) {
            System.out.println("数字未找到");
        } else {
            System.out.println("索引: " + res + " (从0开始)");
        }
    }
}
