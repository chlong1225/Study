package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/1/12
 * @author chenglong
 * description : 递增的三元子序列
 *
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为3的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i]<nums[j]<nums[k] ，返回true ；否则，返回false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 *
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 *
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *
 * 提示：
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 */
public class IncrementThirdStr {

    public boolean increasingTriplet(int[] nums) {
        /**
         * 优化后的枚举，最坏时间复杂度n^2。而数据规模为10^5。肯定会超时。
         * 不过可以通过break，continue进行优化裁剪，实际运行还是超时。
         */
        int length = nums.length;
        //少于3个元素肯定不行
        if (length < 3) {
            return false;
        }
        //以中间点进行拆分遍历
        int min = nums[0];
        for (int i = 1; i < length - 1; i++) {
            int b = nums[i];
            //从0～i查找比b小的数，从i+1～lemgth-1查找比b大的数
            if (b < min) {
                min = b;
            }
            if (min == b) {
                //比b小的数找不到，比b大的也不用查找
                continue;
            }
            //此时找到比b小的数字
            for (int j = i + 1; j < length; j++) {
                if (nums[j] > b) {
                    return true;
                }
            }
        }
        return false;
    }
}
