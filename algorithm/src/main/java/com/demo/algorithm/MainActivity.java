package com.demo.algorithm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.algorithm.leetcode.easy.tree.FindSecondMinimumValue;
import com.demo.algorithm.leetcode.entity.TreeNode;


//https://mp.weixin.qq.com/s/zQeA3WCovd_KYr2LHit_hg
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TreeNode root = new TreeNode(5);
        TreeNode a1 = new TreeNode(8);
        TreeNode a2 = new TreeNode(5);
        root.left = a1;
        root.right = a2;
        FindSecondMinimumValue findSecondMinimumValue = new FindSecondMinimumValue();
        int b1 = findSecondMinimumValue.findSecondMinimumValue(root);
        int b = 10;
    }
}
