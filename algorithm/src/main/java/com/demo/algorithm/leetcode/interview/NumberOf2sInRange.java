package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/5/28.
 * description : 面试题17.06. 2出现的次数
 *
 * 编写一个方法，计算从0到n(含n)中数字2出现的次数。
 *
 * 示例:
 * 输入: 25
 * 输出: 9
 * 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
 *
 * 提示：
 * n <= 10^9
 */
public class NumberOf2sInRange {

    public int numberOf2sInRange(int n) {
        if (n < 2) {
            return 0;
        }
        if (n < 10) {
            return 1;
        }
        //统计n的位数
        int count = 0;
        int tem = n;
        while (tem > 0) {
            count++;
            tem /= 10;
        }
        int base = 1;
        for (int i = 0; i < count - 1; i++) {
            base *= 10;
        }
        //预处理数量
        int[] marks = new int[count];
        marks[1] = 1;
        int twoNum = 10;
        for (int i = 2; i < count; i++) {
            marks[i] = 10 * marks[i - 1] + twoNum;
            twoNum *= 10;
        }
        int sum = 0;
        while (count > 1) {
            int first = n / base;
            n %= base;
            sum += first * marks[count - 1];
            if (first == 2) {
                sum += (n + 1);
            } else if (first > 2) {
                sum += base;
            }
            base /= 10;
            count--;
        }
        if (n >= 2) {
            sum++;
        }
        return sum;
    }
}
