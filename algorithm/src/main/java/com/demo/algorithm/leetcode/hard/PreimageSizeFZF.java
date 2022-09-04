package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/8/28
 * @author chenglong
 * description : 阶乘函数后K个零
 *
 * f(x)是x!末尾是0的数量。回想一下x! = 1 * 2 * 3 * ... * x，且 0! = 1。
 * 例如，f(3)=0，因为 3! = 6 的末尾没有0 ；而f(11) = 2，因为 11!= 39916800 末端有2个0 。
 * 给定k，找出返回能满足f(x)=k的非负整数x的数量。
 *
 * 示例 1：
 * 输入：k = 0
 * 输出：5
 * 解释：0!, 1!, 2!, 3!, 和 4!均符合k=0的条件。
 *
 * 示例 2：
 * 输入：k = 5
 * 输出：0
 * 解释：没有匹配到这样的 x!，符合 k = 5 的条件。
 *
 * 示例 3:
 * 输入: k = 3
 * 输出: 5
 *
 * 提示:
 * 0 <= k <= 10^9
 */
public class PreimageSizeFZF {

    public int preimageSizeFZF(int k) {
        long a = finMaxIndex(k);
        if (a == 0) {
            return 0;
        }
        return (int) (a - finMaxIndex(k - 1));
    }

    //获取K个零对应的最大位置
    private long finMaxIndex(int k) {
        if (k < 0) {
            return 0;
        }
        long min = 4;
        long max = 4;
        while (true) {
            long middle = (max - min) / 2 + min;
            long count = getCount(middle);
            if (count > k) {
                max = middle;
            } else if (count < k) {
                min = middle;
                if (getCount(min + 1) > k) {
                    return min + 1;
                }
                max *= 2;
            } else {
                min = middle;
                if (getCount(min + 1) > k) {
                    return min + 1;
                }
            }
        }
    }

    private long getCount(long num) {
        long sum = 0;
        while (num >= 5) {
            sum += (num / 5);
            num /= 5;
        }
        return sum;
    }
}
