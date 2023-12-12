package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * create on 2023/12/12
 * @author chenglong
 * description : 下一个更大元素IV
 *
 * 给你一个下标从0开始的非负整数数组nums。对于nums中每一个整数，你必须找到对应元素的第二大整数。
 * 如果nums[j]满足以下条件，那么我们称它为nums[i]的第二大整数：
 * j > i
 * nums[j] > nums[i]
 * 恰好存在一个k满足i < k < j且nums[k] > nums[i]。
 * 如果不存在nums[j]，那么第二大整数为-1。
 * 比方说，数组 [1, 2, 4, 3] 中，1的第二大整数是4，2的第二大整数是3，3和4的第二大整数是-1。
 * 请你返回一个整数数组answer，其中answer[i]是nums[i]的第二大整数。
 *
 * 示例 1：
 * 输入：nums = [2,4,0,9,6]
 * 输出：[9,6,6,-1,-1]
 * 解释：
 * 下标为 0 处：2 的右边，4 是大于 2 的第一个整数，9 是第二个大于 2 的整数。
 * 下标为 1 处：4 的右边，9 是大于 4 的第一个整数，6 是第二个大于 4 的整数。
 * 下标为 2 处：0 的右边，9 是大于 0 的第一个整数，6 是第二个大于 0 的整数。
 * 下标为 3 处：右边不存在大于 9 的整数，所以第二大整数为 -1 。
 * 下标为 4 处：右边不存在大于 6 的整数，所以第二大整数为 -1 。
 * 所以我们返回 [9,6,6,-1,-1] 。
 *
 * 示例 2：
 * 输入：nums = [3,3]
 * 输出：[-1,-1]
 * 解释：
 * 由于每个数右边都没有更大的数，所以我们返回 [-1,-1] 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class SecondGreaterElement {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] secondGreaterElement(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        PriorityQueue<Integer> indexs = new PriorityQueue<>((o1, o2) -> nums[o1] - nums[o2]);
        int n = nums.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            while (indexs.size() > 0 && nums[indexs.peek()] < nums[i]) {
                //此时nums[i]是indexs.peek()的第二大值
                int index = indexs.poll();
                answer[index] = nums[i];
            }
            while (stack.size() > 0 && nums[stack.peekLast()] < nums[i]) {
                //此时nums[i]是index的第一大值
                int index = stack.pollLast();
                indexs.offer(index);
            }
            stack.offer(i);
        }
        return answer;
    }
}
