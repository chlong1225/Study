package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/25
 * @author chenglong
 * description : 找到K个最接近的元素
 *
 * 给定一个排序好的数组arr，两个整数k和x，从数组中找到最靠近x（两数之差最小）的k个数。返回的结果必须要是按升序排好的。
 * 整数a比整数b更接近x需要满足：
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length<= 10^4
 * arr按升序排列
 * -10^4<= arr[i],x<= 10^4
 */
public class FindClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //1，遍历数组获取差值
        int length = arr.length;
        int minIndex = 0;
        int[] diff = new int[length];
        diff[0] = Math.abs(arr[0] - x);
        for (int i = 1; i < length; i++) {
            diff[i] = Math.abs(arr[i] - x);
            if (diff[i] < diff[minIndex]) {
                minIndex = i;
            }
        }
        //2，向两边遍历查找index的区间
        int start = minIndex;
        int end = minIndex;
        k--;
        while (k > 0) {
            k--;
            if (start == 0) {
                end++;
            } else if (end == length - 1) {
                start--;
            } else {
                if (diff[end + 1] < diff[start - 1]) {
                    end++;
                } else {
                    start--;
                }
            }
        }
        //3，添加满足要求的值
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
