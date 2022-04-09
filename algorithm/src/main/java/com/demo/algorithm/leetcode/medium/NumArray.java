package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/4/4.
 * description : 区域和检索 - 数组可修改
 *
 * 给你一个数组nums，请你完成两类查询。
 * 其中一类查询要求更新数组nums下标对应的值
 * 另一类查询要求返回数组nums中索引left和索引right之间（包含）的nums元素的和，其中left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组nums初始化对象
 * void update(int index, int val)将nums[index]的值更新为val
 * int sumRange(int left, int right) 返回数组nums中索引left和索引right之间（包含）的nums元素的和（即，nums[left] + nums[left + 1], ..., nums[right]）
 *
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 *10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用update和sumRange方法次数不大于3 * 10^4
 */
//使用分块处理
public class NumArray {

    private int[] dates;
    private int[] sums;
    private int size;

    public NumArray(int[] nums) {
        dates = nums;
        //将数据进行分块
        int n = nums.length;
        size = (int) Math.sqrt(n);
        sums = new int[n / size + 1];
        for (int i = 0; i < n; i++) {
            sums[i / size] += nums[i];
        }
    }


    public void update(int index, int val) {
        sums[index / size] += (val - dates[index]);
        dates[index] = val;
    }

    public int sumRange(int left, int right) {
        //left对应的分块
        int a = left / size;
        //left对应分块中的位置
        int aIndex = left % size;
        int b = right / size;
        int bIndex = right % size;
        int sum = 0;
        if (a == b) {
            //left与right在同一个分块中
            for (int i = left; i <= right; i++) {
                sum += dates[i];
            }
        } else {
            //left与right在不同分块中
            //1，统计a分块中的总和
            for (int i = left; i < (a + 1) * size; i++) {
                sum += dates[i];
            }
            //2，统计a，b之间的分块和
            for (int i = a + 1; i < b; i++) {
                sum += sums[i];
            }
            //3，统计b分块中的总和
            for (int i = b * size; i <= right; i++) {
                sum += dates[i];
            }
        }
        return sum;
    }
}
