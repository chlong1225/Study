package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/9/21
 * description : 杨辉三角
 *
 *  给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *  在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 */
public class YangHuiTriangle1 {

    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                List<Integer> tem = new ArrayList<>(1);
                tem.add(1);
                result.add(tem);
            } else {
                List<Integer> tem = new ArrayList<>(i + 1);
                tem.add(1);
                List<Integer> pre = result.get(i - 1);
                for (int j = 1; j < i + 1; j++) {
                    if (j < pre.size()) {
                        Integer a = pre.get(j - 1);
                        Integer b = pre.get(j);
                        tem.add(j, a + b);
                    } else {
                        tem.add(1);
                    }
                }
                result.add(tem);
            }
        }
        return result;
    }
}
