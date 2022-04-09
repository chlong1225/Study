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
//使用线段树的方式
public class NumArray2 {

    private int n;
    private int[] trees;

    public NumArray2(int[] nums) {
        n = nums.length;
        trees = new int[4 * n];
        buildTree(0, 0, n - 1, nums);
    }

    private void buildTree(int node, int start, int end, int[] nums) {
        if (start == end) {
            trees[node] = nums[start];
            return;
        }
        int middle = start + (end - start) / 2;
        buildTree(node * 2 + 1, start, middle, nums);
        buildTree(node * 2 + 2, middle + 1, end, nums);
        trees[node] = trees[node * 2 + 1] + trees[node * 2 + 2];
    }


    public void update(int index, int val) {
        changeTree(index, val, 0, 0, n - 1);
    }

    private void changeTree(int index, int val, int node, int start, int end) {
        if (start == end) {
            trees[node] = val;
            return;
        }
        int middle = (end - start) / 2 + start;
        if (index <= middle) {
            //只更新左边的树
            changeTree(index, val, node * 2 + 1, start, middle);
        } else {
            changeTree(index, val, node * 2 + 2, middle + 1, end);
        }
        trees[node] = trees[2 * node + 1] + trees[2 * node + 2];
    }

    public int sumRange(int left, int right) {
        return range(left, right, 0, 0, n - 1);
    }

    private int range(int left, int right, int node, int start, int end) {
        if (left == start && right == end) {
            return trees[node];
        }
        int middle = (end - start) / 2 + start;
        if (right <= middle) {
            return range(left, right, node * 2 + 1, start, middle);
        } else if (left > middle) {
            return range(left, right, node * 2 + 2, middle + 1, end);
        }
        return range(left, middle, node * 2 + 1, start, middle) + range(middle + 1, right, node * 2 + 2, middle + 1, end);
    }
}
