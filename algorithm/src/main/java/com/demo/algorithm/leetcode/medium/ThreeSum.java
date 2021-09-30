package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create on 9/30/21
 * @author chenglong
 * description : 三数之和
 *
 *  给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *  注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *  
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        int length = nums.length;
        //1，拆分数据并排序
        //存放零
        List<Integer> zero = new ArrayList<>(1);
        //存放正数
        List<Integer> positive = new ArrayList<>();
        //存放负数
        List<Integer> negative = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                zero.add(0);
            } else if (nums[i] > 0) {
                insertNum(positive, nums[i]);
            } else {
                insertNum(negative, nums[i]);
            }
        }
        //2，三元素求和的方式：3个0 ； 1个0 + 1个正数+1个负数 ； 1个正数+2个负数 ； 2个正数 + 1个负数
        if (zero.size() >= 3) {
            List<Integer> data = new ArrayList<>();
            data.add(0);
            data.add(0);
            data.add(0);
            result.add(data);
        }
        if (positive.size() == 0 || negative.size() == 0) {
            return result;
        }
        //1个0 + 1个正数 + 1个负数
        if (zero.size() > 0) {
            if (positive.size() > negative.size()) {
                int checkLength = negative.size();
                int pre = 0;
                for (int i = 0; i < checkLength; i++) {
                    if (negative.get(i) != pre) {
                        if (findNum(positive, -negative.get(i), 0) != -1) {
                            List<Integer> data = new ArrayList<>();
                            data.add(negative.get(i));
                            data.add(0);
                            data.add(-negative.get(i));
                            result.add(data);
                        }
                    }
                    pre = negative.get(i);
                }
            } else {
                int checkLength = positive.size();
                int pre = 0;
                for (int i = 0; i < checkLength; i++) {
                    if (positive.get(i) != pre) {
                        if (findNum(negative, -positive.get(i), 0) != -1) {
                            List<Integer> data = new ArrayList<>();
                            data.add(-positive.get(i));
                            data.add(0);
                            data.add(positive.get(i));
                            result.add(data);
                        }
                    }
                    pre = positive.get(i);
                }
            }
        }
        //1个正数+2个负数
        if (negative.size() >= 2) {
            int checkLength = positive.size();
            int pre = 0;
            for (int i = 0; i < checkLength; i++) {
                if (pre != positive.get(i)) {
                    List<List<Integer>> datas = findTwoSum(negative, -positive.get(i));
                    if (datas.size() > 0) {
                        for (int j = 0; j < datas.size(); j++) {
                            List<Integer> tem = datas.get(j);
                            tem.add(positive.get(i));
                            result.add(tem);
                        }
                    }
                }
                pre = positive.get(i);
            }
        }
        //1个负数+2个正数
        if (positive.size() >= 2) {
            int checkLength = negative.size();
            int pre = 0;
            for (int i = 0; i < checkLength; i++) {
                if (pre != negative.get(i)) {
                    List<List<Integer>> datas = findTwoSum(positive, -negative.get(i));
                    if (datas.size() > 0) {
                        for (int j = 0; j < datas.size(); j++) {
                            List<Integer> tem = datas.get(j);
                            tem.add(0, negative.get(i));
                            result.add(tem);
                        }
                    }
                }
                pre = negative.get(i);
            }
        }
        return result;
    }

    //查找所有符合条件的两数和
    private List<List<Integer>> findTwoSum(List<Integer> data, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (sum > 0 && sum <= data.get(0)) {
            return result;
        }
        if (sum < 0 && sum >= data.get(data.size() - 1)) {
            return result;
        }
        int length = data.size();
        int pre = 0;
        for (int i = 0; i < length - 1; i++) {
            if (pre != data.get(i)) {
                int findIndex = findNum(data, sum - data.get(i), i + 1);
                if (findIndex != -1) {
                    List<Integer> tems = new ArrayList<>();
                    tems.add(data.get(i));
                    tems.add(data.get(findIndex));
                    result.add(tems);
                }
            }
            pre = data.get(i);
        }
        return result;
    }

    //二分查找指定数
    private int findNum(List<Integer> datas, int num, int start) {
        if (datas.size() == 1) {
            return datas.get(0) == num ? 0 : -1;
        }
        int end = datas.size() - 1;
        if (num < datas.get(start) || num > datas.get(end)) {
            return -1;
        }
        if (num == datas.get(start)) {
            return start;
        }
        if (num == datas.get(end)) {
            return end;
        }
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (datas.get(middle) == num) {
                return middle;
            } else if (datas.get(middle) > num) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    //插入数据时排序(插入排序)
    private void insertNum(List<Integer> datas, int num) {
        if (datas.size() == 0) {
            datas.add(num);
        } else if (datas.size() == 1) {
            if (num < datas.get(0)) {
                datas.add(0, num);
            } else {
                datas.add(num);
            }
        } else {
            int start = 0;
            int end = datas.size() - 1;
            if (num <= datas.get(start)) {
                datas.add(0, num);
            } else if (num >= datas.get(end)) {
                datas.add(num);
            } else {
                //二分查找插入位置
                int middle;
                while (start < end) {
                    middle = (start + end) / 2;
                    if (num == datas.get(middle)) {
                        datas.add(middle, num);
                        start = end = 1;
                    } else if (num > datas.get(middle)) {
                        if (end - middle == 1) {
                            datas.add(middle + 1, num);
                            start = end + 1;
                        } else {
                            start = middle;
                        }
                    } else {
                        if (middle - start == 1) {
                            datas.add(middle, num);
                            start = end + 1;
                        } else {
                            end = middle;
                        }
                    }
                }
            }
        }
    }

}
