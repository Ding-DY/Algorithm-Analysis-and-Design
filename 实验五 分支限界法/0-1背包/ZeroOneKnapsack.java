import java.util.*;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // 创建输入对象
        Random random = new Random();  // 创建随机对象

        int C;
        int M;

        // 获取用户输入的物品数量
        while (true) {
            System.out.println("请输入物品数量:");
            M = scanner.nextInt();
            if (M > 0) break;
            System.out.println("请输入正确的数字");
        }

        // 获取用户输入的背包最大重量
        while (true) {
            System.out.println("请输入背包的最大承重:");
            C = scanner.nextInt();
            if (C > 0) break;
            System.out.println("请输入正确的数字");
        }

        List<Thing> things = new ArrayList<>();  // 创建物品列表
        PriorityQueue<Node> q = new PriorityQueue<>();  // 创建优先队列，用于分支限界法

        // 系统随机生成物品的重量和价值
        System.out.println("物品的重量和价值由系统随机生成：");
        for (int i = 0; i < M; i++) {
            int w = random.nextInt(C) + 1;  // 随机生成重量在[1, C]之间
            int v = random.nextInt(100) + 1;  // 随机生成价值在[1, 100]之间
            things.add(new Thing(w, v));
            System.out.println("物品 " + (i+1) + " - 重量: " + w + ", 价值: " + v);
        }

        Collections.sort(things);  // 根据单位重量价值排序

        Node t = new Node();  // 创建初始节点
        t.getUb(C, things, M);  // 计算上界
        q.add(t);  // 加入优先队列

        int result = 0;  // 保存最终结果

        // 分支限界法主体部分
        // 使用优先队列继续处理，直到队列为空或找到最优解
        while (!q.isEmpty()) {
            // 从优先队列中取出上界估算值最大的节点
            Node f = q.poll();

            // 如果当前节点的价值已经大于或等于其上界估算值，则找到了最优解或近似解
            if (f.V >= f.ub) {
                result = f.V;  // 将当前最优价值赋值给结果变量
                break;  // 结束循环
            }

            // 生成左子节点：不选择当前物品的决策
            if (f.index < M) {
                Node l = new Node();  // 创建新节点代表不选择当前物品
                l.index = f.index + 1;  // 设置新节点的物品索引为下一个物品
                l.getUb(C, things, M);  // 为新节点计算上界估算值
                q.add(l);  // 将新节点加入优先队列
            }

            // 生成右子节点：选择当前物品的决策
            if (f.index < M && f.W + things.get(f.index).w <= C) {
                Node r = new Node();  // 创建新节点代表选择当前物品
                r.index = f.index;
                r.W = f.W + things.get(r.index).w;  // 更新总重量
                r.V = f.V + things.get(r.index).v;  // 更新总价值
                r.a[r.index] = 1;  // 标记选择了当前物品
                r.index = f.index + 1;  // 设置新节点的物品索引为下一个物品
                r.getUb(C, things, M);  // 为新节点计算上界估算值
                q.add(r);  // 将新节点加入优先队列
            }
        }


        System.out.println("最大价值为: " + result);
    }
}

// 定义物品类
class Thing implements Comparable<Thing> {
    int w;  // 物品重量
    int v;  // 物品价值
    double k;  // 单位重量的价值，用于排序

    public Thing(int w, int v) {
        this.w = w;
        this.v = v;
        getK();  // 初始化单位重量价值
    }

    public void  getK() {
        k = (double) v / w;  // 计算单位重量价值
    }

    // 实现比较方法，按单位重量价值排序
    @Override
    public int compareTo(Thing other) {

        return Double.compare(other.k, this.k);
    }
}

// 定义节点类，用于分支限界法中的每个状态
class Node implements Comparable<Node> {
    int W;  // 当前节点的物品总重量
    int V;  // 当前节点的物品总价值
    int ub;  // 上界
    int[] a = new int[105];  // 记录哪些物品被选中
    int index;  // 当前考虑的物品下标

    public Node() {
        W = 0;
        V = 0;
        ub = 0;
        index = 0;
        Arrays.fill(a, 0);  // 初始化为0
    }

    // 实现比较方法，按上界排序
    @Override
    public int compareTo(Node other) {

        return Integer.compare(other.ub, this.ub);
    }

    // 计算上界
    public void getUb(int C, List<Thing> things, int M) {
        int lb = 0;  // 临时的价值下界
        int _W = W;  // 临时的重量
        int i = index;
        while (i < M && _W + things.get(i).w <= C) {
            _W += things.get(i).w;
            lb += things.get(i).v;
            i++;
        }
        if (i < M) {
            int leave = (C - _W) * (int) things.get(i).k;
            ub = V + lb + leave;
        } else {
            ub = V + lb;
        }
    }
}
