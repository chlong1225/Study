package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/3/23.
 * description : 字典序的第K小数字
 *
 * 给定整数n和k，返回[1, n]中字典序第k小的数字。
 *
 * 示例 1:
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 *
 * 示例 2:
 * 输入: n = 1, k = 1
 * 输出: 1
 *
 * 提示:
 * 1 <= k <= n <= 10^9
 */
public class FindKthNum {

    public int findKthNumber(int n, int k) {
        int sum = 1;
        int curDigit = 1;
        k--;
        //统计n有多少位
        int digit = 0;
        int tem = n;
        while (tem > 0) {
            digit++;
            tem /= 10;
        }
        while (k > 0) {
            int count = getCounts(sum, curDigit, digit, n);
            if (count > k) {
                //此时需要在sum下一层查找
                sum *= 10;
                k--;
                curDigit++;
            } else {
                sum++;
                k -= count;
            }
        }
        return sum;
    }

    //以cur为起始的数字有多少位
    private int getCounts(int cur, int curDigit, int digit, int n) {
        int sum = 0;
        int start = cur;
        int last = cur;
        for (int i = curDigit + 1; i <= digit; i++) {
            start *= 10;
            last = last * 10 + 9;
            if (i < digit) {
                sum += (last - start + 1);
            } else {
                if (n >= last) {
                    sum += (last - start + 1);
                } else {
                    if (n >= start) {
                        sum += (n - start + 1);
                    }
                }
            }
        }
        //包含当前根节点cur
        return sum + 1;
    }
}
