package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.DoubleListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2022/1/10.
 * description : LRU缓存
 *
 * 请你设计并实现一个满足LRU(最近最少使用)缓存约束的数据结构。
 * 实现LRUCache类：
 * LRUCache(int capacity)以正整数作为容量capacity初始化LRU缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key已经存在，则变更其数据值value ；
 * 如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，
 * 则应该逐出最久未使用的关键字。
 * 函数get和 put必须以O(1)的平均时间复杂度运行。
 *
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 */
public class LRUCache {

    private int mCapacity;
    private Map<Integer, DoubleListNode> dates = new HashMap<>();
    private DoubleListNode head;
    private DoubleListNode foot;
    private int count;

    public LRUCache(int capacity) {
        mCapacity = capacity;
        dates.clear();
        count = 0;
    }

    public int get(int key) {
        if (dates.get(key) == null) {
            return -1;
        }
        DoubleListNode node = dates.get(key);
        if (count > 1) {
            //只有一个的时候，此时node指向head，foot，此时不处理
            if (node == foot) {
                foot = node.pre;
                foot.next = null;
                node.next = head;
                head.pre = node;
                head = node;
            } else {
                if (node != head) {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                    node.next = head;
                    head.pre = node;
                    head = node;
                    head.pre = null;
                }
            }
        }
        return node.value;
    }

    public void put(int key, int value) {
        if (count == 0) {
            DoubleListNode node = new DoubleListNode(key, value);
            head = node;
            foot = node;
            dates.put(key, node);
            count++;
            return;
        }
        if (dates.get(key) == null) {
            //key不存在时，创建节点并存在首位
            DoubleListNode node = new DoubleListNode(key, value);
            if (count == mCapacity) {
                //此时需要删除尾结点
                dates.remove(foot.key);
                if (mCapacity > 1) {
                    foot = foot.pre;
                    foot.next = null;
                }
            } else {
                count++;
            }
            //创建的node放在首位
            if (mCapacity == 1) {
                head = node;
                foot = node;
            } else {
                node.next = head;
                head.pre = node;
                head = node;
            }
            dates.put(key, node);
        } else {
            DoubleListNode node = dates.get(key);
            //更新节点的值
            node.value = value;
            //将节点放置到首位
            get(key);
        }
    }
}
