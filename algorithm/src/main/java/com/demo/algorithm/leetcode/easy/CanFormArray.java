package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/9/22
 * @author chenglong
 * description : 能否连接形成数组
 *
 * 给你一个整数数组arr，数组中的每个整数互不相同。另有一个由整数数组构成的数组pieces，其中的整数也互不相同。请你以任意顺序连接pieces中的数组以形成arr。
 * 但是不允许对每个数组 pieces[i]中的整数重新排序。
 * 如果可以连接pieces中的数组形成arr返回true ；否则返回false。
 *
 * 示例 1：
 * 输入：arr = [15,88], pieces = [[88],[15]]
 * 输出：true
 * 解释：依次连接 [15] 和 [88]
 *
 * 示例 2：
 * 输入：arr = [49,18,16], pieces = [[16,18,49]]
 * 输出：false
 * 解释：即便数字相符，也不能重新排列 pieces[0]
 *
 * 示例 3：
 * 输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * 输出：true
 * 解释：依次连接 [91]、[4,64] 和 [78]
 *
 * 提示：
 * 1 <= pieces.length <= arr.length <= 100
 * sum(pieces[i].length) == arr.length
 * 1 <= pieces[i].length <= arr.length
 * 1 <= arr[i], pieces[i][j] <= 100
 * arr中的整数互不相同
 * pieces中的整数互不相同（也就是说，如果将pieces扁平化成一维数组，数组中的所有整数互不相同）
 */
public class CanFormArray {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] marks = new int[101];
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            marks[arr[i]] = i + 1;
        }
        for (int i = 0; i < pieces.length; i++) {
            int[] dates = pieces[i];
            int startIndex = marks[dates[0]];
            if (startIndex == 0) {
                return false;
            }
            if (dates.length == 1) {
                continue;
            }
            for (int j = 1; j < dates.length; j++) {
                int index = marks[dates[j]];
                if (index - startIndex != j) {
                    return false;
                }
            }
        }
        return true;
    }
}
