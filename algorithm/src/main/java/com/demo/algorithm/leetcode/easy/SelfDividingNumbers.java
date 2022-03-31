package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/3/31
 * @author chenglong
 * description : 自除数
 *
 * 自除数是指可以被它包含的每一位数整除的数。
 * 例如，128 是一个自除数，因为128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数不允许包含0 。
 * 给定两个整数left和right ，返回一个列表，列表的元素是范围[left, right]内所有的自除数 。
 *
 * 示例 1：
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 * 示例 2:
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 *
 * 提示：
 * 1 <= left <= right <= 10^4
 */
public class SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDivid(i)) {
                result.add(i);
            }
        }
        return result;
    }

    //判断当前数是否为自除数
    private boolean isSelfDivid(int num) {
        int sum = num;
        while (num > 0) {
            int tem = num % 10;
            num /= 10;
            if (tem == 0) {
                return false;
            }
            if (sum % tem != 0) {
                return false;
            }
        }
        return true;
    }
}
