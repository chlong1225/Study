package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 设计哈希集合
 *
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * 实现MyHashSet类：
 * void add(key)向哈希集合中插入值key 。
 * bool contains(key)返回哈希集合中是否存在这个值key。
 * void remove(key) 将给定值key从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * 示例：
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 *
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 *
 * 提示：
 * 0 <= key <= 10^6
 * 最多调用10^4次add、remove和contains
 */
public class MyHashSet {

    private boolean[] marks;

    public MyHashSet() {
        marks = new boolean[1000001];
    }

    public void add(int key) {
        marks[key] = true;
    }

    public void remove(int key) {
        marks[key] = false;
    }

    public boolean contains(int key) {
        return marks[key];
    }
}
