package com.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/9/21
 * description : 杨辉三角2
 *
 *  给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *  在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 *
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 *
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 */
public class YangHuiTriangle2 {

    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        if (rowIndex == 0) {
            result.add(1);
            return result;
        }
        int[] datas = new int[rowIndex + 1];
        int middle = (rowIndex + 2) / 2;
        for (int i = 0; i < middle; i++) {
            datas[i] = getNum(i, rowIndex);
            if (i < rowIndex - i) {
                datas[rowIndex - i] = datas[i];
            }
        }
        int length = datas.length;
        for (int i = 0; i < length; i++) {
            result.add(datas[i]);
        }
        return result;
    }

    //在n个数据中区m个，m个本身不存在排列组合。
    private static int getNum(int m, int n) {
        if (m == 0) {
            return 1;
        }
        if (m == 1) {
            return n;
        }
        int multiplyStart = n - m + 1;
        long result = multiplyStart;
        int multiplyIndex = 1;
        int divideIndex = 2;
        while (multiplyIndex < m || divideIndex <= m) {
            if (divideIndex <= m && result % divideIndex == 0) {
                result /= divideIndex;
                divideIndex++;
            } else {
                result *= (multiplyStart + multiplyIndex);
                multiplyIndex++;
            }
        }
        return (int) result;
    }

}
