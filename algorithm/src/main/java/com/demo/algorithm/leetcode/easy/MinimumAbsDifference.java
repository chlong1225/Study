package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2022/7/4
 * @author chenglong
 * description : 最小绝对差
 *
 * 给你个整数数组arr，其中每个元素都不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 * 示例 1：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 *
 * 示例 2：
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 *
 * 示例 3：
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *
 * 提示：
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class MinimumAbsDifference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        //1，对数据进行排序
        Arrays.sort(arr);
        //2，依次遍历比较
        int minDiff = Integer.MAX_VALUE;
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff == minDiff) {
                List<Integer> item = new ArrayList<>();
                item.add(arr[i - 1]);
                item.add(arr[i]);
                result.add(item);
            } else if (diff < minDiff) {
                minDiff = diff;
                result.clear();
                List<Integer> item = new ArrayList<>();
                item.add(arr[i - 1]);
                item.add(arr[i]);
                result.add(item);
            }
        }
        return result;
    }
}
