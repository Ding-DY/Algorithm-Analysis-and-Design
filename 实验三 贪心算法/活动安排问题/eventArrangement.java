import java.util.*;

// 定义一个类来表示单个活动的详细信息。
class Activity {
    int start;
    int finish;
    int index; // 这是活动的索引，可以帮助我们在输出时标识特定的活动。

    // 构造函数，用于初始化活动的开始和结束时间。
    public Activity(int start, int finish, int index) {
        this.start = start;
        this.finish = finish;
        this.index = index;
    }
}

public class eventArrangement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 使用Scanner类来获取用户输入。

        System.out.println("请输入活动数量: ");
        int n = scanner.nextInt(); // 读取用户输入的活动数。

        List<Activity> activities = new ArrayList<>(); // 创建一个列表来存储所有活动。

        System.out.println("请依次输入每个活动的开始时间和结束时间(用空格分隔):");
        for (int i = 1; i <= n; i++) {
            System.out.println(">> ");
            int start = scanner.nextInt(); // 活动的开始时间。
            int finish = scanner.nextInt(); // 活动的结束时间。

            activities.add(new Activity(start, finish, i)); // 将新活动添加到列表中。
        }


        // 按结束时间对活动进行排序，这是贪心算法的关键步骤。
        Collections.sort(activities, (a1, a2) -> Integer.compare(a1.finish, a2.finish));


        // 选择活动的贪心策略部分。
        List<Integer> selectedActivities = new ArrayList<>(); // 存储被选中的活动索引。
        int lastFinishTime = Integer.MIN_VALUE; // 上一个被选中的活动的结束时间。

        for (Activity activity : activities) {
            // 如果此活动的开始时间晚于上一个选定活动的结束时间，则选择此活动。
            if (activity.start >= lastFinishTime) {
                selectedActivities.add(activity.index); // 添加被选中的活动的索引。
                lastFinishTime = activity.finish; // 更新最后的结束时间，以便进行下一轮比较。
            }
        }

        // 输出被选中的活动。
        System.out.println("被选中的活动如下:");
        for (Integer index : selectedActivities) {
            System.out.print(index + " ");
        }

    }
}
