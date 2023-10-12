package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/10/12
 * @author chenglong
 * description : 找出数组的串联值
 *
 * 给你一个下标从0开始的整数数组nums。
 * 现定义两个数字的串联是由这两个数值串联起来形成的新数字。
 * 例如，15和49的串联是1549。
 * nums的串联值最初等于0。执行下述操作直到nums变为空：
 * 如果 nums 中存在不止一个数字，分别选中 nums 中的第一个元素和最后一个元素，将二者串联得到的值加到 nums 的 串联值 上，然后从 nums 中删除第一个和最后一个元素。
 * 如果仅存在一个元素，则将该元素的值加到 nums 的串联值上，然后删除这个元素。
 * 返回执行完所有操作后 nums 的串联值。
 *
 * 示例 1：
 * 输入：nums = [7,52,2,4]
 * 输出：596
 * 解释：在执行任一步操作前，nums 为 [7,52,2,4] ，串联值为 0 。
 *  - 在第一步操作中：
 * 我们选中第一个元素 7 和最后一个元素 4 。
 * 二者的串联是 74 ，将其加到串联值上，所以串联值等于 74 。
 * 接着我们从 nums 中移除这两个元素，所以 nums 变为 [52,2] 。
 *  - 在第二步操作中：
 * 我们选中第一个元素 52 和最后一个元素 2 。
 * 二者的串联是 522 ，将其加到串联值上，所以串联值等于 596 。
 * 接着我们从 nums 中移除这两个元素，所以 nums 变为空。
 * 由于串联值等于 596 ，所以答案就是 596 。
 *
 * 示例 2：
 * 输入：nums = [5,14,13,8,12]
 * 输出：673
 * 解释：在执行任一步操作前，nums 为 [5,14,13,8,12] ，串联值为 0 。
 * - 在第一步操作中：
 * 我们选中第一个元素 5 和最后一个元素 12 。
 * 二者的串联是 512 ，将其加到串联值上，所以串联值等于 512 。
 * 接着我们从 nums 中移除这两个元素，所以 nums 变为 [14,13,8] 。
 * - 在第二步操作中：
 * 我们选中第一个元素 14 和最后一个元素 8 。
 * 二者的串联是 148 ，将其加到串联值上，所以串联值等于 660 。
 * 接着我们从 nums 中移除这两个元素，所以 nums 变为 [13] 。
 * - 在第三步操作中：
 * nums 只有一个元素，所以我们选中 13 并将其加到串联值上，所以串联值等于 673 。
 * 接着我们从 nums 中移除这个元素，所以 nums 变为空。
 * 由于串联值等于 673 ，所以答案就是 673 。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 */
public class FindTheArrayConcVal {

    public long findTheArrayConcVal(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        long sum = 0;
        for (int i = 0; i < n / 2; i++) {
            sum += buildNum(nums[i], nums[n - 1 - i]);
        }
        if (n % 2 == 1) {
            sum += nums[n / 2];
        }
        return sum;
    }

    private long buildNum(int start, int end) {
        //1，计算start的倍数
        long multiple = 1;
        int num = end;
        while (num > 0) {
            num /= 10;
            multiple *= 10;
        }
        return start * multiple + end;
    }
}
