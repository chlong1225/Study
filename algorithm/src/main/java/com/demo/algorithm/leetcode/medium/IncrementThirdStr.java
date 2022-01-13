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

    public boolean increasingTriplet2(int[] nums) {
        /**
         * 以空间换时间的方式。记录当前位置数据左边最小，右边最大值。
         * 时间复杂度：O(n)，空间复杂度：O(n)
         */
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        //遍历左边最小值
        int[] mins = new int[length];
        int min = nums[0];
        mins[0] = min;
        for (int i = 1; i < length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            mins[i] = min;
        }
        //遍历右边最大值
        int[] maxs = new int[length];
        int max = nums[length - 1];
        maxs[length - 1] = max;
        for (int i = length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                max = nums[i];
            }
            maxs[i] = max;
        }
        //遍历判断是否存在符合条件的数据
        for (int i = 1; i < length - 1; i++) {
            if (mins[i] < nums[i] && nums[i] < maxs[i]) {
                return true;
            }
        }
        return false;
    }

    //使用贪心算法
    public boolean increasingTriplet3(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        int first = nums[0];
        int second = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            if (nums[i] > first) {
                //如果second没有赋值，则没有出现比first更大的，此时直接给second赋值
                if (second == Integer.MIN_VALUE) {
                    second = nums[i];
                } else {
                    if (nums[i] > second) {
                        /**
                         * 此时first，second，num[i]刚好可以构建递增序列。
                         * 最小first可能已经重置到了second之后，但只要second赋值了，在之前肯定存在比second更小的first
                         */
                        return true;
                    } else {
                        //second在大于first的前提条件下，尽可能取更小值，便于查找比second大的值
                        second = nums[i];
                    }
                }
            } else {
                //递增首位重置最小
                first = nums[i];
            }
        }
        return false;
    }
}
