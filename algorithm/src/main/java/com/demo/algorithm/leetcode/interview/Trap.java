package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/5/27.
 * description : 面试题17.21. 直方图的水量
 *
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为1。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接6个单位的水（蓝色部分表示水）。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Trap {

   public int trap(int[] height) {
      if (height == null || height.length < 3) {
         return 0;
      }
      int length = height.length;
      int[] left = new int[length];
      left[0] = 0;
      int max = height[0];
      for (int i = 1; i < length; i++) {
         left[i] = max;
         if (height[i] > max) {
            max = height[i];
         }
      }
      int[] right = new int[length];
      right[length - 1] = 0;
      max = height[length - 1];
      for (int i = length - 2; i >= 0; i--) {
         right[i] = max;
         if (height[i] > max) {
            max = height[i];
         }
      }
      int sum = 0;
      for (int i = 0; i < length; i++) {
         int add = (Math.min(left[i], right[i]) - height[i]);
         if (add > 0) {
            sum += add;
         }
      }
      return sum;
   }
}
