package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by chl on 2022/7/28.
 * description : 设计跳表
 *
 * 不使用任何库函数，设计一个跳表 。
 * 跳表是在O(log(n))时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * 了解更多 :https://en.wikipedia.org/wiki/Skip_list
 *
 * 在本题中，你的设计应该要包含这些函数：
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num):插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果num不存在，直接返回false. 如果存在多个num，删除其中任意一个即可。
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 *
 * 示例 1:
 * 输入
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * 输出
 * [null, null, null, null, false, null, true, false, true, false]
 * 解释
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 *
 * 提示:
 * 0 <= num, target <= 2 * 10^4
 * 调用search, add, erase操作次数不大于5 * 10^4
 */
public class Skiplist {

    private static final int MAX_LEVEL = 32;
    private static final double P_FACTOR = 0.5;
    private SkiplistNode head;
    private int level;
    private Random random;


    public Skiplist() {
        head = new SkiplistNode(-1, MAX_LEVEL);
        level = 0;
        random = new Random();
    }

    public boolean search(int target) {
        SkiplistNode p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forward[i] != null && p.forward[i].val < target) {
                p = p.forward[i];
            }
        }
        p = p.forward[0];
        if (p != null && p.val == target) {
            return true;
        }
        return false;
    }

    public void add(int num) {
        SkiplistNode[] updates = new SkiplistNode[MAX_LEVEL];
        Arrays.fill(updates, head);
        SkiplistNode p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forward[i] != null && p.forward[i].val < num) {
                p = p.forward[i];
            }
            updates[i] = p;
        }
        int lev = randomLevel();
        level = Math.max(level, lev);
        SkiplistNode node = new SkiplistNode(num, lev);
        for (int i = 0; i < lev; i++) {
            node.forward[i] = updates[i].forward[i];
            updates[i].forward[i] = node;
        }
    }

    public boolean erase(int num) {
        SkiplistNode[] updates = new SkiplistNode[MAX_LEVEL];
        SkiplistNode p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forward[i] != null && p.forward[i].val < num) {
                p = p.forward[i];
            }
            updates[i] = p;
        }
        p = p.forward[0];
        if (p == null || p.val != num) {
            return false;
        }
        for (int i = 0; i < level; i++) {
            if (updates[i].forward[i] != p) {
                break;
            }
            updates[i].forward[i] = p.forward[i];
        }
        while (level > 1 && head.forward[level - 1] == null) {
            level--;
        }
        return true;
    }

    private int randomLevel() {
        int lev = 1;
        while (random.nextDouble() < P_FACTOR && lev < MAX_LEVEL) {
            lev++;
        }
        return lev;
    }

    static class SkiplistNode {
        int val;
        SkiplistNode[] forward;

        public SkiplistNode(int val, int maxLevel) {
            this.val = val;
            this.forward = new SkiplistNode[maxLevel];
        }
    }

}
