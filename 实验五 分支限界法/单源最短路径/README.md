# 分支限界法解决单源最短路径问题 (Branch and Bound for Single Source Shortest Path)

## 项目描述

这是一个基于Java的单源最短路径问题解决方案实现，使用分支限界法进行求解。本项目旨在为有向图中的单一源点到其他所有顶点的最短路径问题提供高效的算法实现。

## 功能

- 允许用户输入图的顶点数和边数。
- 允许用户输入每条边的起始点、终点以及边的权重。
- 计算并输出从单一源点到其他所有顶点的最短路径及其路径长度。

## 使用方法

1. 确保您的计算机已安装Java运行环境。（本程序使用JDK 17版本）
2. 从文件夹中找到.Java源代码文件、.class字节码文件以及可执行的jar包。
3. 使用命令行界面或IDE运行Java程序。
4. 根据程序提示输入顶点数、边数以及每条边的具体信息。
5. 程序将会输出从源点到其他各点的最短路径和路径长度。

## 输入输出示例

```
请输入顶点数:>> 5
请输入边数:>> 7
请输入起始顶点编号:>> 1
请输入边的数据（起点 终点 权重）:
1 2 10
1 4 30
1 5 100
2 3 50
3 5 10
4 3 20
4 5 60
从顶点1出发到其他顶点的最短路径长度为：
顶点2: 10
顶点3: 50
顶点4: 30
顶点5: 60
```

## 性能

本算法采用分支限界法，在某些情况下时间复杂度可以优于传统的Dijkstra算法。算法使用优先队列优化搜索过程，并通过剪枝来避免不必要的计算，从而提高求解效率。

## 贡献者和联系方式

[丁帝尧] [[ding-dy7@qq.com](mailto:ding-dy7@qq.com)]

## 许可证

本项目遵循MIT许可证。