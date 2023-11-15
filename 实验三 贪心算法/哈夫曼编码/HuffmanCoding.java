import java.util.*;

public class HuffmanCoding {

    // 定义节点类，用于哈夫曼树的结点
    public static class Node implements Comparable<Node> {
        char ch; // 该节点表示的字符
        int freq; // 该字符的频率
        Node left; // 左子节点
        Node right; // 右子节点

        // 节点构造函数
        public Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        // 用于优先队列的比较，按照频率排序
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    // 哈夫曼编码的映射关系：字符 -> 编码
    public static Map<Character, String> codes = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("请输入字符串:"); // 提示用户输入
        Scanner scanner = new Scanner(System.in); // 创建扫描器读取用户输入
        String inputData = scanner.nextLine(); // 读取一行输入
        scanner.close(); // 关闭扫描器

        // 计算输入字符串中每个字符的频率
        Map<Character, Integer> freqMap = calculateFrequency(inputData);
        // 显示字符及其频率
        show(freqMap);
        // 创建哈夫曼树
        Node root = createHuffmanTree(freqMap);
        // 为每个字符生成哈夫曼编码
        createHuffmanCode(root, "");
        // 使用哈夫曼编码对输入字符串进行编码
        String encodedData = encode(inputData);
        System.out.println("编码后的数据: " + encodedData);
        // 解码编码后的数据
        String decodedData = decode(root, encodedData);
        System.out.println("解码后的数据: " + decodedData);
    }

    // 计算字符串中每个字符的频率
    public static Map<Character, Integer> calculateFrequency(String data) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : data.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    // 显示字符及其频率
    public static void show(Map<Character, Integer> freqMap) {
        System.out.println("字符及其对应的频率:");
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println("字符 '" + entry.getKey() + "' 的频率: " + entry.getValue());
        }
    }

    // 使用字符的频率信息创建哈夫曼树
    public static Node createHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }
        return queue.poll();
    }

    // 递归创建哈夫曼编码
    public static void createHuffmanCode(Node root, String code) {
        if (root == null) {
            return;
        }
        // 如果是叶子节点，保存哈夫曼编码
        if (root.left == null && root.right == null) {
            codes.put(root.ch, code);
        }
        // 递归创建左右子树的哈夫曼编码
        createHuffmanCode(root.left, code + "0");
        createHuffmanCode(root.right, code + "1");
    }

    // 对字符串进行哈夫曼编码
    public static String encode(String data) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : data.toCharArray()) {
            encoded.append(codes.get(ch));
        }
        return encoded.toString();
    }

    // 对哈夫曼编码后的数据进行解码
    public static String decode(Node root, String encodedData) {
        StringBuilder decoded = new StringBuilder();
        Node currentNode = root;
        for (char ch : encodedData.toCharArray()) {
            // 根据编码确定是向左子树还是右子树前进
            currentNode = ch == '0' ? currentNode.left : currentNode.right;
            // 如果是叶子节点，表示解码出一个字符
            if (currentNode.left == null && currentNode.right == null) {
                decoded.append(currentNode.ch);
                currentNode = root; // 返回根节点开始下一个字符的解码
            }
        }
        return decoded.toString();
    }
}

