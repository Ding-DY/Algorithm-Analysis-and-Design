

import java.util.Random;
import java.util.Scanner;

public class QuickSort {

    // 定义一个交换数组元素的方法
    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // 快速排序递归方法
    private static void quickSort(int[] arr, int left, int right) {
        // 如果左边界小于右边界，进行快排
        if (left < right) {
            // 对数组进行分区，获得中枢点位置
            int pivotIndex = partition(arr, left, right);
            // 分别对中枢点左右两侧的数组进行递归排序
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    // 分区方法，通过选取一个基准值将数组划分为两部分
    private static int partition(int[] arr, int left, int right) {
        // 选择最右侧的元素作为基准
        int pivot = arr[right];
        int i = left - 1;
        // 从左到右遍历数组
        for (int j = left; j < right; j++) {
            // 如果当前元素小于基准，i向右移动一位，然后交换i和j对应的元素
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // 最后将基准与i+1位置的元素交换，确保基准左侧的元素都比基准小，右侧的元素都比基准大
        swap(arr, i + 1, right);
        // 返回基准的最终位置
        return i + 1;
    }

    // 主函数
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // 用于接收用户输入
        int num;
        // 输入验证，确保输入的数大于0
        while (true) {
            System.out.print("请输入数组的长度:");
            num = scanner.nextInt();  // 接收用户输入
            if (num > 0) break;
            System.out.println("请输入一个正整数。");
        }

        int[] arr = new int[num];  // 根据输入的数值定义整数数组
        Random random = new Random();  // 实例化Random对象

        System.out.print("生成的随机数数组:\n");
        // 填充数组
        for (int i = 0; i < num; ++i) {
            arr[i] = random.nextInt(10000);  // 生成0到100之间的随机数填充数组
            System.out.print(arr[i] + " ");  // 打印生成的随机数
        }
        System.out.println();


        quickSort(arr, 0, num - 1);  // 快速排序

        System.out.print("通过快速排序排序后的数组:\n");
        for (int i = 0; i < num; ++i) {
            System.out.print(arr[i] + " ");  // 打印排序后的数组
        }
    }
}
