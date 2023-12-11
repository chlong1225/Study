package com.demo.algorithm.leetcode.easy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * create by chenglong on 2023/12/10
 * description : 数据流中的第K大元素
 *
 * 设计一个找到数据流中第k大元素的类（class）。注意是排序后的第k大元素，不是第k个不同的元素。
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数k和整数流nums初始化对象。
 * int add(int val) 将 val 插入数据流nums后，返回当前数据流中第k大的元素。
 *
 * 示例：
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 * 提示：
 * 1 <= k <= 10^4
 * 0 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * -10^4 <= val <= 104
 * 最多调用add方法10^4次
 * 题目数据保证，在查找第k大元素时，数组中至少有k个元素
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class KthLargest {

    private int maxSize = 0;
    private PriorityQueue<Integer> dates = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public KthLargest(int k, int[] nums) {
        maxSize = k;
        dates.clear();
        int n = nums.length;
        if (n <= k) {
            for (int i = 0; i < n; i++) {
                dates.offer(nums[i]);
            }
        } else {
            for (int i = 0; i < k; i++) {
                dates.offer(nums[i]);
            }
            for (int i = k; i < n; i++) {
                if (dates.peek() < nums[i]) {
                    dates.poll();
                    dates.offer(nums[i]);
                }
            }
        }
    }

    public int add(int val) {
        if (dates.size() < maxSize) {
            dates.offer(val);
        } else {
            if (dates.peek() < val) {
                dates.poll();
                dates.offer(val);
            }
        }
        return dates.peek();
    }
}
