package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 可被5整除的二进制前缀
 *
 * 给定一个二进制数组nums(索引从0开始)。
 * 我们将xi定义为其二进制表示形式为子数组nums[0..i] (从最高有效位到最低有效位)。
 * 例如，如果 nums =[1,0,1]，那么x0=1,x1=2, 和x2=5。
 * 返回布尔值列表answer，只有当xi可以被5整除时，答案answer[i]为true，否则为false。
 *
 * 示例 1：
 * 输入：nums = [0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为 true 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：[false,false,false]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i]仅为0或1
 */
public class PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        int date = 0;
        List<Boolean> result = new ArrayList<>(n);
        for (int i = 0; i < nums.length; i++) {
            date = date * 2 + nums[i];
            if (date % 5 == 0) {
                result.add(true);
            } else {
                result.add(false);
            }
            date %= 5;
        }
        return result;

    }
}
