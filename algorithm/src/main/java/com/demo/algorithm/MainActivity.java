package com.demo.algorithm;

import android.os.Bundle;
import android.util.Log;

import com.demo.algorithm.algo.LruLinkedList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testLru();
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