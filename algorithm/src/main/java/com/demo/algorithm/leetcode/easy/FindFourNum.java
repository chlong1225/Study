package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/12/29.
 * description : 统计特殊四元组
 *
 * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * a < b < c < d
 *  
 * 示例 1：
 * 输入：nums = [1,2,3,6]
 * 输出：1
 * 解释：满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
 *
 * 示例 2：
 * 输入：nums = [3,3,6,4,5]
 * 输出：0
 * 解释：[3,3,6,4,5] 中不存在满足要求的四元组。
 *
 * 示例 3：
 * 输入：nums = [1,1,1,3,5]
 *
 * 输出：4
 * 解释：满足要求的 4 个四元组如下：
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 *  
 * 提示：
 * 4 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 */
public class FindFourNum {

    //使用暴力解法。时间复杂度O(n^4)
    public int countQuadruplets(int[] nums) {
        int length = nums.length;
        int count = 0;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1; k < length - 1; k++) {
                    for (int l = k + 1; l < length; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    //通过等式转换+遍历顺序优化获取特殊四元组
    public int countQuadruplets2(int[] nums) {
        int length = nums.length;
        int count = 0;
        int[] marks = new int[101];
        /**
         * 原理: a+b+c=d 转换为a+b = d-c
         * 以b为边界拆分成两段。倒序遍历大于b的d,c的差值,并记录
         * 正序遍历:获取a+b的值,然后在记录的差值中查询
         */
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                /**
                 * 这个地方只需要统计b+1与d的差值，c不需要遍历b到d之间
                 * 比如: b = n-3，d = n-1，此时c只有n-2一种可能
                 * b = n-4，d=n-2，此时c只有n-3。
                 * 但：d正序遍历时：d = n-1，此时c = n-3或n-2,之前已经计算了d=n-1,c-n-2的场景
                 * 故只需要计算c=n-3即可
                 */
                if (nums[j] - nums[i + 1] >= 2) {
                    marks[nums[j] - nums[i + 1]]++;
                }
            }
            for (int k = 0; k < i; k++) {
                //计算a+b
                int tem = nums[k] + nums[i];
                if (tem <= 99) {
                    //d-c的差值为tem的数量即:d-c=a+b的数量
                    count += marks[tem];
                }
            }
        }
        return count;
    }
}
