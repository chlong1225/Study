package com.demo.algorithm.leetcode.medium;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * create on 2022/1/14
 * @author chenglong
 * description : 查找和最小的K对数字
 *
 * 给定两个以升序排列的整数数组nums1和nums2,以及一个整数k。
 * 定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。
 * 请找到和最小的k个数对(u1,v1),(u2,v2)...(uk,vk)。
 *
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *
 * 提示:
 * 1 <= nums1.length, nums2.length <= 10^4
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 */
public class FindKMinNum {

    //由于数据范围k=1000，可以使用暴力枚举。最大数据范围10^6。
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int length1 = Math.min(nums1.length, k);
        int length2 = Math.min(nums2.length, k);
        int maxCount = length1 * length2;
        if (maxCount <= k) {
            //如果组合数量maxCount少于k，则所有的数对都满足
            for (int i = 0; i < length1; i++) {
                for (int j = 0; j < length2; j++) {
                    List<Integer> tem = new ArrayList<>();
                    tem.add(nums1[i]);
                    tem.add(nums2[j]);
                    result.add(tem);
                }
            }
        } else {
            //如果组合超过了需要对组合进行排序，去前面最小的
            int[][] datas = new int[maxCount][2];
            for (int i = 0; i < length1; i++) {
                for (int j = 0; j < length2; j++) {
                    datas[i * length2 + j][0] = nums1[i];
                    datas[i * length2 + j][1] = nums2[j];
                }
            }
            Arrays.sort(datas, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] + o1[1] - o2[0] - o2[1];
                }
            });
            for (int i = 0; i < k; i++) {
                List<Integer> tem = new ArrayList<>();
                tem.add(datas[i][0]);
                tem.add(datas[i][1]);
                result.add(tem);
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        /**
         * 由于nums1与nums2已经是有序的，可以使用多路归并的方式。由于从两个数组中分别选取元素。
         * 最大筛选长度不能超过k。比如：nums1取0位，nums2最多取k个。数组有效长度可以进行裁剪。
         */
        int length1 = Math.min(nums1.length, k);
        int length2 = Math.min(nums2.length, k);
        //以nums2的位数为基准。初始添加：(0,0),(0,1)....(0,length2-1)，然后nums1的index依次增长
        PriorityQueue<int[]> dates = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        for (int i = 0; i < length2; i++) {
            dates.add(new int[]{0, i});
        }
        List<List<Integer>> result = new ArrayList<>();
        while (k > 0 && dates.size() > 0) {
            int[] poll = dates.poll();
            List<Integer> item = new ArrayList<>();
            item.add(nums1[poll[0]]);
            item.add(nums2[poll[1]]);
            result.add(item);
            if (poll[0] + 1 < length1) {
                dates.offer(new int[]{poll[0] + 1, poll[1]});
            }
            k--;
        }
        return result;
    }
}
