package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/19
 * @author chenglong
 * description : 是否所有1都至少相隔k个元素
 *
 * 你一个由若干0和1组成的数组nums以及整数k。如果所有1都至少相隔k个元素，则返回True；否则返回False。
 *
 * 示例 1：
 * 输入：nums = [1,0,0,0,1,0,0,1], k = 2
 * 输出：true
 * 解释：每个1都至少相隔2个元素。
 *
 * 示例 2：
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：false
 * 解释：第二个 1 和第三个 1 之间只隔了 1 个元素。
 *
 * 示例 3：
 * 输入：nums = [1,1,1,1,1], k = 0
 * 输出：true
 *
 * 示例 4：
 * 输入：nums = [0,1,0,1], k = 1
 * 输出：true
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] 的值为 0 或 1
 */
public class KLengthApart {

    public boolean kLengthApart(int[] nums, int k) {
        int preIndex = -1;
        int curIndex;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (preIndex == -1) {
                    preIndex = i;
                } else {
                    curIndex = i;
                    //中间0的个数
                    int count = curIndex - preIndex - 1;
                    if (count < k) {
                        return false;
                    }
                    preIndex = curIndex;
                }
            }
        }
        return true;
    }
}
