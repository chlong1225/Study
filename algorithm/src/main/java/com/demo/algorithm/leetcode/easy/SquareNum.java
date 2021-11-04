package com.demo.algorithm.leetcode.easy;

/**
 * create on 11/4/21
 * @author chenglong
 * description : 有效的完全平方数
 *
 * 给定一个正整数num ，编写一个函数，如果num是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 *
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 *
 * 提示：
 * 1 <= num <= 2^31 - 1
 */
public class SquareNum {

    public static boolean isPerfectSquare(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1 || num == 4 || num == 9 || num == 16) {
            return true;
        }
        if (num < 16) {
            return false;
        }
        int min = 4;
        int max = num >> 2;
        while (min <= max) {
            int middle = (min + max) >> 1;
            int compare = num / middle;
            if (compare == middle) {
                if (num % middle == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (compare < middle) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return false;
    }
}
