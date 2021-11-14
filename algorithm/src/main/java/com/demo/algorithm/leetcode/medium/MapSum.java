package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/14.
 * description : 键值映射
 * <p>
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *  
 * 示例：
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *  
 * 提示：
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
 */
public class MapSum {

    private TrieTree root;

    public MapSum() {
        root = new TrieTree();
    }

    public void insert(String key, int val) {
        int length = key.length();
        TrieTree node = root;
        for (int i = 0; i < length; i++) {
            int index = key.charAt(i) - 'a';
            if (node.next[index] == null) {
                node.next[index] = new TrieTree();
            }
            node = node.next[index];
        }
        node.vale = val;
    }

    public int sum(String prefix) {
        TrieTree node = root;
        int length = prefix.length();
        //1,找到前缀对应的节点
        for (int i = 0; i < length; i++) {
            int index = prefix.charAt(i) - 'a';
            node = node.next[index];
            if (node == null) {
                //找不到前缀对应的节点,
                return 0;
            }
        }
        //2,统计节点下的值
        return total(node);
    }

    private int total(TrieTree node) {
        int sum = node.vale;
        for (int i = 0; i < 26; i++) {
            TrieTree tem = node.next[i];
            if (tem != null) {
                sum += total(tem);
            }
        }
        return sum;
    }

    //字典树结构
    private static class TrieTree{

        TrieTree[] next;
        int vale;

        public TrieTree() {
            next = new TrieTree[26];
        }
    }
}
