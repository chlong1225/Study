package com.demo.algorithm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.algorithm.leetcode.hard.ContainVirus;

//https://mp.weixin.qq.com/s/zQeA3WCovd_KYr2LHit_hg
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[][] dates = {{0, 1, 0, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}};

        ContainVirus containVirus = new ContainVirus();
        int a1 = containVirus.containVirus(dates);
        int a = 10;
    }
}
