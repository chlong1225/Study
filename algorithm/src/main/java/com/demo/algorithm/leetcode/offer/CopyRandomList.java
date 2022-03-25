package com.demo.algorithm.leetcode.offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指Offer 35. 复杂链表的复制
 *
 * 请实现copyRandomList函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个next指针指向下一个节点，还有一个random指针指向链表中的任意节点或者null。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 */
public class CopyRandomList {

    private Map<Node, Integer> marks = new HashMap<>();
    private List<Node> dates = new ArrayList<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        marks.clear();
        dates.clear();
        Node p = head;
        int index = 0;
        //创建节点
        while (p != null) {
            Node node = new Node(p.val);
            dates.add(node);
            marks.put(p, index);
            p = p.next;
            index++;
        }
        //关联节点
        index = 0;
        while (head != null) {
            Node node = dates.get(index);
            if (head.random != null) {
                int randomIndex = marks.get(head.random);
                node.random = dates.get(randomIndex);
            }
            if (index < dates.size() - 1) {
                node.next = dates.get(index + 1);
            }
            head = head.next;
            index++;
        }
        return dates.get(0);
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
