package com.demo.algorithm.leetcode.contest.week299;

/**
 * Created by chl on 2022/6/26.
 * description : 拼接数组的最大分数
 *
 * 给你两个下标从0开始的整数数组nums1和nums2，长度都是n 。
 * 你可以选择两个整数 left 和 right ，其中 0 <= left <= right < n ，接着 交换 两个子数组 nums1[left...right] 和 nums2[left...right] 。
 * 例如，设 nums1 = [1,2,3,4,5] 和 nums2 = [11,12,13,14,15] ，整数选择 left = 1 和 right = 2，那么 nums1 会变为 [1,12,13,4,5] 而 nums2 会变为 [11,2,3,14,15] 。
 * 你可以选择执行上述操作 一次 或不执行任何操作。
 * 数组的 分数取sum(nums1)和sum(nums2)中的最大值，其中sum(arr)是数组arr中所有元素之和。
 * 返回可能的最大分数 。
 * 子数组是数组中连续的一个元素序列。arr[left...right] 表示子数组包含 nums 中下标 left 和 right 之间的元素（含 下标 left 和 right 对应元素）。
 *
 * 示例 1：
 * 输入：nums1 = [60,60,60], nums2 = [10,90,10]
 * 输出：210
 * 解释：选择 left = 1 和 right = 1 ，得到 nums1 = [60,90,60] 和 nums2 = [10,60,10] 。
 * 分数为 max(sum(nums1), sum(nums2)) = max(210, 80) = 210 。
 *
 * 示例 2：
 * 输入：nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
 * 输出：220
 * 解释：选择 left = 3 和 right = 4 ，得到 nums1 = [20,40,20,40,20] 和 nums2 = [50,20,50,70,30] 。
 * 分数为 max(sum(nums1), sum(nums2)) = max(140, 220) = 220 。
 *
 * 示例 3：
 * 输入：nums1 = [7,11,13], nums2 = [1,1,1]
 * 输出：31
 * 解释：选择不交换任何子数组。
 * 分数为 max(sum(nums1), sum(nums2)) = max(31, 3) = 31 。
 *
 * 提示：
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^4
 */
public class MaximumsSplicedArray {

   public int maximumsSplicedArray(int[] nums1, int[] nums2) {
      int n = nums1.length;
      //1，统计两个数组的和与差分
      int sum1 = 0;
      int sum2 = 0;
      int[] diffs = new int[n];
      for (int i = 0; i < n; i++) {
         sum1 += nums1[i];
         sum2 += nums2[i];
         diffs[i] = nums1[i] - nums2[i];
      }
      //2，计算从nums1中交换数字到nums2中使nums2最大的值
      int max2 = 0;
      int total = 0;
      for (int i = 0; i < n; i++) {
         total += diffs[i];
         if (total < 0) {
            //重新开始统计和
            total = 0;
         } else {
            if (total > max2) {
               max2 = total;
            }
         }
      }
      //3，计算从nums2中交换数字到nums1中使nums1最大
      int max1 = 0;
      total = 0;
      for (int i = 0; i < n; i++) {
         total += (-diffs[i]);
         if (total < 0) {
            total = 0;
         } else {
            if (total > max1) {
               max1 = total;
            }
         }
      }
      return Math.max(sum1 + max1, sum2 + max2);
   }
}
