package com.demo.algorithm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.algorithm.leetcode.medium.DetectSquares;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        int a1 = detectSquares.count(new int[]{11, 10});
        int a2 = detectSquares.count(new int[]{14, 8});
        detectSquares.add(new int[]{11, 2});
        int a3 = detectSquares.count(new int[]{11, 10});
        int a = 10;
    }
}
