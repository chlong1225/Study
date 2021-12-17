package com.demo.algorithm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.chl.common.utils.LogUtil;
import com.demo.algorithm.algo.ArrayQueue;
import com.demo.algorithm.algo.ArrayStack;
import com.demo.algorithm.algo.CalculatorByStack;
import com.demo.algorithm.algo.LinkedList;
import com.demo.algorithm.algo.LinkedQueue;
import com.demo.algorithm.algo.LinkedStack;
import com.demo.algorithm.algo.LruLinkedList;
import com.demo.algorithm.algo.Node;
import com.demo.algorithm.leetcode.medium.NoisyAndRich;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testAlgorithm();
        NoisyAndRich noisyAndRich = new NoisyAndRich();
        int[][] rich = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
//        int[] a1 = noisyAndRich.loudAndRich(rich, quiet);
        int a = 10;
    }

    private void testAlgorithm() {
        testLru();
        testDelete();
        testReverseStr();
        testCenter();
        testMerge();
        testStack();
        testCalculator();
        testQueue();
    }

    private void testQueue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue("" + i);
        }
        String dequeue = arrayQueue.dequeue();
        arrayQueue.enqueue("a");
        arrayQueue.enqueue("b");
        int size = arrayQueue.size();
        LogUtil.e(TAG, "testQueue: " + size + " ;; " + dequeue);
        for (int i = 0; i < size; i++) {
            LogUtil.e(TAG, "testQueue: " + arrayQueue.dequeue());
        }
        LogUtil.e(TAG, "testQueue: -----------------------------");
        LinkedQueue linkedQueue = new LinkedQueue();
        for (int i = 0; i < 10; i++) {
            linkedQueue.enqueue("" + i);
        }
        int size1 = linkedQueue.size();
        LogUtil.e(TAG, "testQueue: size1 = " + size1);
        for (int i = 0; i < size1; i++) {
            LogUtil.e(TAG, "testQueue: " + linkedQueue.dequeue());
        }
        LogUtil.e(TAG, "testQueue: size2 = " + linkedQueue.size());
    }

    private void testCalculator() {
        String expression1 = "";
        String expression2 = "1+3*4+6/3";
        String expression3 = "2*3/6+2-1";
        int calculator1 = CalculatorByStack.calculator(expression1);
        int calculator2 = CalculatorByStack.calculator(expression2);
        int calculator3 = CalculatorByStack.calculator(expression3);
        LogUtil.e(TAG, "testCalculator: calculator1 = " + calculator1 + " ;; calculator2 = " + calculator2 + " ;; calculator3 = " + calculator3);
    }

    private void testStack() {
        ArrayStack stack = new ArrayStack();
        String pop = stack.pop();
        LogUtil.e(TAG, "testStack: pop = " + pop);
        for (int i = 0; i < 10; i++) {
            LogUtil.e(TAG, "testStack: push = " + i);
            stack.push("" + i);
        }
        LogUtil.e(TAG, "testStack: push = a");
        stack.push("a");
        for (int i = 0; i < 11; i++) {
            String pop1 = stack.pop();
            LogUtil.e(TAG, "testStack: pop1 = " + pop1);
        }
        LogUtil.e(TAG, "testStack: ---------------------");
        LinkedStack linkedStack = new LinkedStack();
        String pop2 = linkedStack.pop();
        LogUtil.e(TAG, "testStack: pop2 = " + pop2);
        for (int i = 0; i < 10; i++) {
            linkedStack.push("" + i * i);
        }
        for (int i = 0; i < 10; i++) {
            String pop3 = linkedStack.pop();
            LogUtil.e(TAG, "testStack: pop3 = " + pop3);
        }
    }

    private void testMerge() {
        LinkedList list1 = new LinkedList();
        list1.add("1");
        list1.add("3");
        list1.add("5");
        list1.add("7");
        LinkedList list2 = new LinkedList();
        list2.add("2");
        list2.add("4");
        list2.add("6");
        list2.add("8");
        Node node = LinkedList.mergeNode(list1.getHead(), list2.getHead());
        list1.printAll(node);
    }

    private void testCenter() {
        LinkedList list = new LinkedList();
        Node head = new Node("a", null);
        list.add(head);
        list.add("b");
        list.add("c");
        list.add("d");
        Node end = new Node("e", head);
        list.add(end);
        boolean ring1 = list.isRing();
        LinkedList list1 = new LinkedList();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        String center1 = list1.quaryCenter().getData();
        boolean ring2 = list1.isRing();
        LinkedList list2 = new LinkedList();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        String center2 = list2.quaryCenter().getData();
        LogUtil.e(TAG, "testCenter: " + ring1 + " ;; " + ring2 + " ;; " + center1 + " ;; " + center2);
    }

    private void testReverseStr() {
        LinkedList list1 = new LinkedList();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("b");
        list1.add("a");
        boolean reverseStr1 = list1.isReverseStr();
        LinkedList list2 = new LinkedList();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("c");
        list2.add("b");
        list2.add("a");
        boolean reverseStr2 = list2.isReverseStr();
        LinkedList list3 = new LinkedList();
        list3.add("a");
        list3.add("b");
        list3.add("c");
        boolean reverseStr3 = list3.isReverseStr();
        LogUtil.e(TAG, "testReverseStr: " + reverseStr1 + " ;; " + reverseStr2 + " ;; " + reverseStr3);

    }

    private void testDelete() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        list.printAll();
        LogUtil.e("LinkedList", "testDelete: 0");
        list.deleteByValue("0");
        list.printAll();
        LogUtil.e("LinkedList", "testDelete: 5");
        list.deleteByValue("5");
        list.printAll();
        LogUtil.e("LinkedList", "testDelete: 10");
        list.deleteByValue("10");
        list.printAll();
        list.deleteNode(2);
        list.printAll();
        list.deleteNode(10);
        list.printAll();
    }

    private void testLru() {
        LruLinkedList list = new LruLinkedList();
        list.printAll();
        LogUtil.e("LruLinkedList", "testLru: 开始添加数据");
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        list.printAll();
        LogUtil.e("LruLinkedList", "testLru: 添加重复数据5");
        list.add("5");
        list.printAll();
        LogUtil.e("LruLinkedList", "testLru: 超出长度");
        list.add("10");
        list.printAll();
        LogUtil.e("LruLinkedList", "testLru: 添加重复数据10");
        list.add("10");
        list.printAll();
    }
}