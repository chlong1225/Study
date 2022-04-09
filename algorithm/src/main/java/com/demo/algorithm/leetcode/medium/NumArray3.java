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
//使用树状数组的方式
public class NumArray3 {

    private int[] dates;
    private int n;
    private int[] tree;

    public NumArray3(int[] nums) {
        dates = nums;
        n = dates.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    private void add(int index, int num) {
        for (int i = index; i <= n; i += lowbit(i)) {
            tree[i] += num;
        }
    }

    private int lowbit(int index) {
        return index & -index;
    }

    public void update(int index, int val) {
        add(index + 1, val - dates[index]);
        dates[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }

    private int query(int index) {
        int sum = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            sum += tree[i];
        }
        return sum;
    }
}
