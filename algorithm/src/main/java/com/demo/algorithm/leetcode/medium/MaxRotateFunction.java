package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/4/22.
 * description : 旋转函数
 *
 * 给定一个长度为n的整数数组nums。
 *
 * 假设arrk是数组nums顺时针旋转k个位置后的数组，我们定义nums的旋转函数F为：
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回F(0), F(1), ..., F(n-1)中的最大值。
 * 生成的测试用例让答案符合32 位 整数。
 *
 * 示例 1:
 * 输入: nums = [4,3,2,6]
 * 输出: 26
 * 解释:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 *
 * 示例 2:
 * 输入: nums = [100]
 * 输出: 0
 *
 * 提示:
 * n == nums.length
 * 1 <= n <= 10^5
 * -100 <= nums[i] <= 100
 */
public class MaxRotateFunction {

    public int maxRotateFunction(int[] nums) {
        /**
         * 分析：F(0)=a0+1*a1+2*a2+...(n-1)*an-1
         * F(k)=ka0+(k+1)a1+...+(n-1)a(n-1-k)+o*a(n-k)+....(k-1)a(n-1)
         * F(k) = F(0)+k(a0+...a(n-1-k))-(n-k)(a(n-k)+...+a(n-1))
         */
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        //包含当前位置以及左边的和
        int[] left = new int[length];
        //包含当前位置以及右边的和
        int[] right = new int[length];
        //对应F(0)
        int base = 0;
        left[0] = nums[0];
        for (int i = 1; i < length; i++) {
            base += (i * nums[i]);
            left[i] = left[i - 1] + nums[i];
        }
        right[length - 1] = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] + nums[i];
        }
        int max = base;
        for (int k = 1; k < length; k++) {
            int sum = base + k * left[length - 1 - k] - (length - k) * right[length - k];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
