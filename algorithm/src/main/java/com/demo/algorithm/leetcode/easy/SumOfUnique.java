package com.demo.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chl on 2022/2/6.
 * description : 唯一元素的和
 *
 * 给你一个整数数组nums。数组中唯一元素是那些只出现恰好一次的元素。
 * 请你返回nums中唯一元素的和。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,2]
 * 输出：4
 * 解释：唯一元素为 [1,3] ，和为 4 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：没有唯一元素，和为 0 。
 *
 * 示例 3 ：
 * 输入：nums = [1,2,3,4,5]
 * 输出：15
 * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class SumOfUnique {

   public int sumOfUnique(int[] nums) {
      int total = 0;
      int sameSum = 0;
      int length = nums.length;
      //记录出现的所有数字,相同数字记一次
      Set<Integer> datas = new HashSet<>();
      //记录出现的相同数字
      Set<Integer> sames = new HashSet<>();
      for (int i = 0; i < length; i++) {
         if (datas.add(nums[i])) {
            total += nums[i];
         } else {
            if (sames.add(nums[i])) {
               sameSum += nums[i];
            }
         }
      }
      return total - sameSum;
   }
}
