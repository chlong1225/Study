package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/11/21
 * @author chenglong
 * description : 分汤
 *
 * 有A和B两种类型的汤。一开始每种类型的汤有n毫升。有四种分配操作：
 * 提供 100ml 的 汤A 和 0ml 的 汤B 。
 * 提供 75ml 的 汤A 和 25ml 的 汤B 。
 * 提供 50ml 的 汤A 和 50ml 的 汤B 。
 * 提供 25ml 的 汤A 和 75ml 的 汤B 。
 * 当我们把汤分配给某人之后，汤就没有了。每个回合我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 * 注意不存在先分配100ml汤B的操作。
 * 需要返回的值：汤A先分配完的概率+汤A和汤B同时分配完的概率/2。返回值在正确答案10^-5的范围内将被认为是正确的。
 *
 * 示例 1:
 * 输入: n = 50
 * 输出: 0.62500
 * 解释:如果我们选择前两个操作，A 首先将变为空。
 * 对于第三个操作，A 和 B 会同时变为空。
 * 对于第四个操作，B 首先将变为空。
 * 所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
 *
 * 示例 2:
 * 输入: n = 100
 * 输出: 0.71875
 *
 * 提示:
 * 0 <= n <= 10^9
 */
public class SoupServings {

    private static final double BASE = 0.25;
    private static final int MOD = 181;

    private final Map<Integer, Double> marks = new HashMap<>();

    public double soupServings(int n) {
        if (n == 0) {
            return 0.5;
        }
        if (n % 25 == 0) {
            n /= 25;
        } else {
            n /= 25;
            n++;
        }
        if (n >= MOD) {
            return 1;
        }
        marks.clear();
        return soup(n, n);
    }

    private double soup(int a, int b) {
        int key = a * MOD + b;
        if (marks.containsKey(key)) {
            return marks.get(key);
        }
        double total = 0;
        //第一种方式分配
        if (b == 0) {
            if (a <= 4) {
                //此时a,b都分配完
                total += 0.5;
            }
        } else {
            if (a <= 4) {
                //此时a分配完
                total += 1;
            } else {
                total += soup(a - 4, b);
            }
        }
        //第二种分配方式
        if (b <= 1) {
            if (a <= 3) {
                //此时a，b都可以分配完
                total += 0.5;
            }
        } else {
            if (a <= 3) {
                total += 1;
            } else {
                total += soup(a - 3, b - 1);
            }
        }
        //d第三种分配方式
        if (b <= 2) {
            if (a <= 2) {
                total += 0.5;
            }
        } else {
            if (a <= 2) {
                total += 1;
            } else {
                total += soup(a - 2, b - 2);
            }
        }
        //第四种分配方式
        if (b <= 3) {
            if (a <= 1) {
                total += 0.5;
            }
        } else {
            if (a <= 1) {
                total += 1;
            } else {
                total += soup(a - 1, b - 3);
            }
        }
        total *= BASE;
        marks.put(key, total);
        return total;
    }
}
