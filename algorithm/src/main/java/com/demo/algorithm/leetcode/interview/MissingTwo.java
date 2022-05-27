package com.demo.algorithm.leetcode.interview;

/**
 * create on 2022/5/27
 * @author chenglong
 * description : 面试题17.19. 消失的两个数字
 *
 * 给定一个数组，包含从1到N所有的整数，但其中缺了两个数字。你能在O(N)时间内只用O(1)的空间找到它们吗？
 * 以任意顺序返回这两个数字均可。
 *
 * 示例 1:
 * 输入: [1]
 * 输出: [2,3]
 *
 * 示例 2:
 * 输入: [2,3]
 * 输出: [1,4]
 *
 * 提示：
 * nums.length <=30000
 */
public class MissingTwo {

    public int[] missingTwo(int[] nums) {
        int length = nums.length;
        //区间范围为：1～length+2
        boolean more1 = false;
        boolean more2 = false;
        //1，对数组进行排序
        for (int i = 0; i < length; i++) {
            while (!(nums[i] == (i + 1) || nums[i] == -1)) {
                //进行交换
                if (nums[i] == length + 1) {
                    more1 = true;
                    nums[i] = -1;
                } else if (nums[i] == length + 2) {
                    more2 = true;
                    nums[i] = -1;
                } else {
                    int tem = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = tem;
                }
            }
        }
        int[] result = new int[2];
        int index = 0;
        if (!more1) {
            result[index++] = length + 1;
        }
        if (!more2) {
            result[index++] = length + 2;
        }
        if (index < 2) {
            for (int i = 0; i < length; i++) {
                if (nums[i] == -1) {
                    result[index++] = i + 1;
                    if (index == 2) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}
