package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/3/9.
 * description : 得分最高的最小轮调
 *
 * 给你一个数组nums，我们可以将它按一个非负整数k进行轮调，这样可以使数组变为[nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]]的形式。
 * 此后，任何值小于或等于其索引的项都可以记作一分。
 * 例如，数组为nums =[2,4,1,3,0]，我们按k=2进行轮调后，它将变成[1,3,0,2,4]。这将记为3分，因为1>0[不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择k = 3，得分最高。
 *
 * 示例 2：
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 */
public class BestRotation {

    public int bestRotation(int[] nums) {
        /**
         * 分析：暴力枚举遍历k=0~n-1，时间复杂度：n^2。根据数据规模肯定会超时
         * 这里需要预处理数据：对每个位置计算出满足计算时k的取值区间。
         * 最后合并统计所有区间的和，找出最小出现次数最多的k值
         */
        int length = nums.length;
        int[] marks = new int[length + 2];
        for (int i = 0; i < length; i++) {
            /**
             * 分析：执行轮掉的次数为k：
             * i>=k时，轮调后index = i-k，0<=i-k<length，对应值为nums[i]。满足记一分的条件：nums[i]<=i-k。
             * 转换后：k的取值范围为：[i+1-length,i-nums[i]]。其中i+1-length<=0。取值范围可以缩减为：[0,i-nums[i]]
             * i<k时，轮调后index = length-k+i，满足区间[0~lenght-1]，对应值为nums[i]。满足记一分的条件：nums[i]<=lenth-k+i
             * 转换后：k的取值范围：[i+1,length+i-nums[i]]。length
             */
            int a = i - nums[i];
            if (a >= 0) {
                //区间：[0,i-nums[i]],[i+1,n-1]
                marks[0]++;
                marks[a + 1]--;

                marks[i + 1]++;
                marks[length]--;
            } else {
                //区间：[i+1,length+i-nums[i]]
                int b = length + a;
                marks[i + 1]++;
                marks[b + 1]--;
            }
        }
        //统计差分数组获取每个位置上的数量
        for (int i = 1; i <= length; i++) {
            marks[i] += marks[i - 1];
        }
        //比较获取最大值
        int index = 0;
        for (int i = 1; i < length; i++) {
            if (marks[i] > marks[index]) {
                index = i;
            }
        }
        return index;
    }
}
