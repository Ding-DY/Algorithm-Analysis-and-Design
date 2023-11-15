import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SingleSourceShortestPath {

    // 定义无穷大，表示两个点之间不可达
    static final int INF = 10000000;
    // 最大顶点数量
    static final int maxn = 500005;

    // 边的数据结构，包含起点、终点、权重和指向下一条边的指针
    static class Edge {
        int u, v, w, next;

        Edge(int u, int v, int w, int next) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.next = next;
        }
    }

    // dis数组存储从起点到各点的最短距离v
    static int[] dis = new int[maxn];
    //vis数组标记某点是否已被访问
    static boolean[] vis = new boolean[maxn];
    // head数组是邻接表的头指针
    static int[] head = new int[maxn];
    //e数组存储所有的边
    static Edge[] e = new Edge[maxn * 2];
    static int cnt = 0;  // 边的计数

    // 添加一条边到邻接表中
    static void add(int u, int v, int w) {
        e[cnt] = new Edge(u, v, w, head[u]);
        head[u] = cnt++;  //从顶点 u 出发的第一条边在 e 数组中的索引
    }

    // Dijkstra算法的实现
    static void dijkstra(int s) {
        // 使用优先队列，根据距离大小来获取当前最短的边
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        q.add(new int[]{0, s});
        /*a[0]: 这是从起始点到该顶点的当前最短距离。
        a[1]: 这是顶点的标识符（ID）。*/

        while (!q.isEmpty()) {
            int[] top = q.poll();    //从队列中取出当前距离最小的顶点和它的距离。
            int x = top[1];
            if (!vis[x]) {    //如果该顶点还未被访问过，则进入内部逻辑
                vis[x] = true;
                for (int i = head[x]; i != -1; i = e[i].next) {   //遍历与顶点x相连的所有边
                    int v = e[i].v;   //获取当前边的另一端点
                    if (dis[v] > dis[x] + e[i].w) {  // 如果从x到v的距离加上x到起点的距离小于v当前的最短距离，那么更新v的最短距离。
                        dis[v] = dis[x] + e[i].w;
                        q.add(new int[]{dis[v], v});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 输入顶点数、边数和起始点
        System.out.println("请输入地点数量:");
        int n = Integer.parseInt(reader.readLine());
        System.out.println("请输入边的数量:");
        int m = Integer.parseInt(reader.readLine());
        System.out.println("请输入起始点的编号:");
        int s = Integer.parseInt(reader.readLine());

        // 初始化dis数组和head数组
        Arrays.fill(dis, INF);
        Arrays.fill(head, -1);
        dis[s] = 0;

        System.out.println("请按顺序输入" + m + "条边的起始点、终止点和权重:");
        for (int i = 0; i < m; i++) {
            String[] inputs = reader.readLine().split(" ");
            int u = Integer.parseInt(inputs[0]);
            int v = Integer.parseInt(inputs[1]);
            int w = Integer.parseInt(inputs[2]);
            add(u, v, w);  // 添加边到邻接表中
        }

        dijkstra(s);

        // 输出从起点到每一个点的最短距离
        System.out.println("从起点到每个点的最短距离为:");
        for (int i = 1; i <= n; i++) {
            System.out.printf("从起点到 %d 的距离为: %d%n", i, dis[i]);
        }
    }
}


