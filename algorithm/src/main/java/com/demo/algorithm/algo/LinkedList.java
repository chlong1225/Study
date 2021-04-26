package com.demo.algorithm.algo;


import android.util.Log;


/**
 * Created by w on 2021/4/24.
 *
 * 链表
 *
 */
public class LinkedList {

    private static final String TAG = "LinkedList";

    private Node head = null;

    /**
     * 添加节点,便于构建数据
     * @param data :
     */
    public void add(String data) {
        Node node = new Node(data, null);
        if (head == null) {
            head = node;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
        }
    }

    /**
     * 删除节点 (默认节点data不为null)
     * @param data :
     */
    public void deleteByValue(String data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node pre = head;
        Node current = head.next;
        while (current != null && !current.data.equals(data)) {
            pre = current;
            current = current.next;
        }
        if (current == null) {
            return;
        }
        pre.next = current.next;

    }

    /**
     *  使用单链表判断字符串是否是回文字符串
     */

    public boolean isReverseStr() {
        //链表为空,返回false
        if (head == null) {
            return false;
        }
        Node p = head;
        //链表只有一个元素,返回true
        if (p.next == null) {
            return true;
        }
        //直接反转链表
        Node q = reverse();
        while (p.next != null) {
            if (!p.data.equals(q.data)) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    /**
     * @return : 单链表反转
     */
    public Node reverse() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node p = head;
        Node result = head;
        while(p.next != null) {
            Node tem = result;
            result = p.next;
            result.next = tem;
        }
        return result;
    }

    /**
     * @return : 链表是否为环形
     */
    public boolean isRing() {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 两个链表存入整数
     * @param head1 :
     * @param head2 :
     * @return : 两个有序的链表合并
     */
    public Node mergeNode(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node result = null;
        Node p = head1;
        Node q = head2;
        while (p != null && q != null) {
            if (Integer.parseInt(p.data) < Integer.parseInt(q.data)) {
                if (result == null) {
                    result = p;
                } else {
                    result.next = p;
                }
                p = p.next;
            } else {
                if (result == null) {
                    result = q;
                } else {
                    result.next = q;
                }
                q = q.next;
            }
        }
        if (p == null) {
            if (result == null) {
                result = q;
            } else {
                result.next = q;
            }
        }
        if (q == null) {
            if (result == null) {
                result = p;
            } else {
                result.next = p;
            }
        }
        return result;
    }

    /**
     * 删除链表倒数第k个结点
     * @param k : 倒数节点的序号
     */
    public void deleteNode(int k) {
        if (head == null) {
            return;
        }
        Node fast = head;
        int i = 0;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (fast == null) {
            return;
        }
        Node p = head;
        Node pre = null;
        while (fast.next != null) {
            fast = fast.next;
            pre = p;
            p.next = p;
        }
        if (pre == null) {
            head = head.next;
        } else {
            pre.next = p.next;
        }
    }

    /**
     * @return : 求链表的中间结点
     */
    public Node quaryCenter() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    /**
     * 打印单链表的数据
     */
    public void printAll() {
        printAll(head);
    }
    
    public void printAll(Node head) {
        if (head == null) {
            Log.e(TAG, "printAll: 链表为空");
            return;
        }
        int count = 0;
        while (head != null) {
            Log.e(TAG, "printAll: data = " + head.data);
            head = head.next;
            count++;
        }
        Log.e(TAG, "printAll: count = " + count);

    }



}


