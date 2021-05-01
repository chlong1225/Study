package com.demo.algorithm;

import android.os.Bundle;
import android.util.Log;

import com.demo.algorithm.algo.LinkedList;
import com.demo.algorithm.algo.LruLinkedList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testLru();

//        testDelete();

        testReverseStr();

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
        Log.e(TAG, "testReverseStr: " + reverseStr1 + " ;; " + reverseStr2 + " ;; " + reverseStr3);

    }

    private void testDelete() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        list.printAll();
        Log.e("LinkedList", "testDelete: 0");
        list.deleteByValue("0");
        list.printAll();
        Log.e("LinkedList", "testDelete: 5");
        list.deleteByValue("5");
        list.printAll();
        Log.e("LinkedList", "testDelete: 10");
        list.deleteByValue("10");
        list.printAll();
        list.deleteNode(2);
        list.printAll();
        list.deleteNode(10);
        list.printAll();
    }

    private void testLru() {
        LruLinkedList<String> list = new LruLinkedList<>();
        list.printAll();
        Log.e("LruLinkedList", "testLru: 开始添加数据");
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        list.printAll();
        Log.e("LruLinkedList", "testLru: 添加重复数据5");
        list.add("5");
        list.printAll();
        Log.e("LruLinkedList", "testLru: 超出长度");
        list.add("10");
        list.printAll();
        Log.e("LruLinkedList", "testLru: 添加重复数据10");
        list.add("10");
        list.printAll();
    }
}