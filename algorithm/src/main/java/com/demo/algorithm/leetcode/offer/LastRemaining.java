package com.demo.algorithm.leetcode.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/16.
 * description : 剑指Offer62. 圆圈中最后剩下的数字
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
 * 求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出:3
 *
 * 示例 2：
 * 输入: n = 10, m = 17
 * 输出:2
 *
 * 限制：
 * 1 <= n<= 10^5
 * 1 <= m <= 10^6
 */
public class LastRemaining {

    public int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        List<Integer> dates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dates.add(i);
        }
        int index = 0;
        while (dates.size() > 1) {
            int size = dates.size();
            //第几个数count
            int count = m % size;
            if (count == 0) {
                count = size;
            }
            index = (index + count - 1) % size;
            dates.remove(index);
            if (index == dates.size()) {
                index = 0;
            }
        }
        return dates.get(0);
    }

    //使用动态规划解决约瑟夫问题
    public int lastRemaining2(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }
}
