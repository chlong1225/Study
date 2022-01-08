package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/7.
 * description : 格雷编码
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class GrayCode {

    public static List<Integer> grayCode(int n) {
        if (n < 0 || n > 32) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        pre.add(0);
        if (n == 0) {
            return pre;
        }
        int add = 1;
        for (int i = 1; i <= n; i++) {
            result.clear();
            result.addAll(pre);
            int size = pre.size();
            for (int j = size - 1; j >= 0; j--) {
                result.add(pre.get(j) | add);
            }
            if (i < n) {
                add <<= 1;
                pre.clear();
                pre.addAll(result);
            }
        }
        return result;
    }

    public static List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<>();
        if (n < 1 || n > 16) {
            return result;
        }
        List<Integer> pre = new ArrayList<>();
        pre.add(0);
        pre.add(1);
        if (n == 1) {
            return pre;
        }
        int add = 1;
        for (int i = 2; i <= n; i++) {
            int size = pre.size();
            add <<= 1;
            result.clear();
            result.addAll(pre);
            for (int j = size - 1; j >= 0; j--) {
                result.add(pre.get(j) | add);
            }
            if (i < n) {
                pre.clear();
                pre.addAll(result);
            }
        }
        return result;
    }
}
