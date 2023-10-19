package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/10/19
 * @author chenglong
 * description : 同积元组
 *
 * 给你一个由不同正整数组成的数组nums，请你返回满足a * b = c * d的元组(a,b,c,d)的数量。其中a、b、c 和d都是nums中的元素，且a != b != c != d 。
 *
 * 示例 1：
 * 输入：nums = [2,3,4,6]
 * 输出：8
 * 解释：存在 8 个满足题意的元组：
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 *
 * 示例 2：
 * 输入：nums = [1,2,4,5,10]
 * 输出：16
 * 解释：存在 16 个满足题意的元组：
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 * nums 中的所有元素 互不相同
 */
public class TupleSameProduct {

    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        if (n < 4) {
            return 0;
        }
        Map<Integer, Integer> marks = new HashMap<>();
        //统计两两相乘的数量
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tem = nums[i] * nums[j];
                if (marks.containsKey(tem)) {
                    marks.put(tem, marks.get(tem) + 1);
                } else {
                    marks.put(tem, 1);
                }
            }
        }
        int sum = 0;
        //遍历map查找乘积相同的数量
        for (int key : marks.keySet()) {
            int count = marks.get(key);
            if (count >= 2) {
                sum += (count - 1) * count / 2;
            }
        }
        return sum * 8;
    }
}
