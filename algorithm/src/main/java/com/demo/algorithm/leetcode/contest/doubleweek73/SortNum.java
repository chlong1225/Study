package com.demo.algorithm.leetcode.contest.doubleweek73;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/3/7.
 * description : 将杂乱无章的数字排序
 *
 * 给你一个下标从0开始的整数数组mapping，它表示一个十进制数的映射规则，mapping[i] = j表示这个规则下将数位i映射为数位j。
 * 一个整数映射后的值为将原数字每一个数位i（0<=i<=9）映射为mapping[i]。
 * 另外给你一个整数数组nums，请你将数组nums中每个数按照它们映射后对应数字非递减顺序排序后返回。
 * 注意：
 * 如果两个数字映射后对应的数字大小相同，则将它们按照输入中的相对顺序排序。
 * nums中的元素只有在排序的时候需要按照映射后的值进行比较，返回的值应该是输入的元素本身。
 *
 * 示例 1：
 * 输入：mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
 * 输出：[338,38,991]
 * 解释：
 * 将数字 991 按照如下规则映射：
 * 1. mapping[9] = 6 ，所有数位 9 都会变成 6 。
 * 2. mapping[1] = 9 ，所有数位 1 都会变成 8 。
 * 所以，991 映射的值为 669 。
 * 338 映射为 007 ，去掉前导 0 后得到 7 。
 * 38 映射为 07 ，去掉前导 0 后得到 7 。
 * 由于 338 和 38 映射后的值相同，所以它们的前后顺序保留原数组中的相对位置关系，338 在 38 的前面。
 * 所以，排序后的数组为 [338,38,991] 。
 *
 * 示例 2：
 * 输入：mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
 * 输出：[123,456,789]
 * 解释：789 映射为 789 ，456 映射为 456 ，123 映射为 123 。所以排序后数组为 [123,456,789] 。
 *
 * 提示：
 * mapping.length == 10
 * 0 <= mapping[i] <= 9
 * mapping[i]的值互不相同。
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] < 10^9
 *
 */
public class SortNum {

    public int[] sortJumbled(int[] mapping, int[] nums) {
        //记录映射关系，防止重复计算
        Map<Integer, Integer> marks = new HashMap<>();
        int length = nums.length;
        List<Integer> dates = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            dates.add(nums[i]);
            if (marks.get(nums[i]) == null) {
                marks.put(nums[i], replaceNum(nums[i], mapping));
            }
        }
        Collections.sort(dates, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return marks.get(o1) - marks.get(o2);
            }
        });
        for (int i = 0; i < length; i++) {
            nums[i] = dates.get(i);
        }
        return nums;
    }

    private int replaceNum(int num, int[] mapping) {
        int sum = 0;
        int modulus = 1;
        while (num >= 10) {
            int tem = mapping[num % 10];
            num /= 10;
            sum += tem * modulus;
            modulus *= 10;
        }
        sum += (mapping[num] * modulus);
        return sum;
    }
}
