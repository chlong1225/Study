package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/8/24
 * @author chenglong
 * description : 通过翻转子数组使两个数组相等
 *
 * 给你两个长度相同的整数数组target和arr。每一步中，你可以选择arr的任意非空子数组并将它翻转。你可以执行此过程任意次。
 * 如果你能让arr变得与target相同返回True；否则返回False。
 *
 * 示例 1：
 * 输入：target = [1,2,3,4], arr = [2,4,1,3]
 * 输出：true
 * 解释：你可以按照如下步骤使 arr 变成 target：
 * 1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
 * 2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
 * 3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
 * 上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
 *
 * 示例 2：
 * 输入：target = [7], arr = [7]
 * 输出：true
 * 解释：arr 不需要做任何翻转已经与target相等。
 *
 * 示例 3：
 * 输入：target = [3,7,9], arr = [3,7,11]
 * 输出：false
 * 解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。
 *
 * 提示：
 * target.length == arr.length
 * 1 <= target.length <= 1000
 * 1 <= target[i] <= 1000
 * 1 <= arr[i] <= 1000
 */
public class CanBeEqual {

    public boolean canBeEqual(int[] target, int[] arr) {
        int[] marks = new int[1001];
        int length = target.length;
        for (int i = 0; i < length; i++) {
            marks[target[i]]++;
        }
        for (int i = 0; i < length; i++) {
            if (marks[arr[i]] > 0) {
                marks[arr[i]]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
