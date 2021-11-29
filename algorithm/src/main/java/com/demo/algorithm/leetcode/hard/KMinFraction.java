package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * create on 11/29/21
 * @author chenglong
 * description : 第K个最小的素数分数
 *
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 *
 * 示例 1：
 * 输入：arr = [1,2,3,5], k = 3
 * 输出：[2,5]
 * 解释：已构造好的分数,排序后如下所示:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * 很明显第三个最小的分数是 2/5
 *
 * 示例 2：
 * 输入：arr = [1,7], k = 1
 * 输出：[1,7]
 *  
 * 提示：
 * 2 <= arr.length <= 1000
 * 1 <= arr[i] <= 3 * 10^4
 * arr[0] == 1
 * arr[i] 是一个 素数 ，i > 0
 * arr 中的所有数字 互不相同 ，且按 严格递增 排序
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 */
public class KMinFraction {

    //此方案超出时间限制
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int length = arr.length;
        //最多分数数量,不在取值范围时返回：{0,0}
        int max = (length - 1) * length >> 1;
        if (k < 1 || k > max) {
            return new int[]{0, 0};
        }
        //1，定义二维数组存放前面k个最小的分数
        int[][] fractions = new int[k][2];
        //2，存分子的index，对应index = 分母index-1
        List<Integer> datas = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            datas.add(0);
        }
        int size = datas.size();
        int index = 0;
        int[] min = new int[2];
        int minIndex = 0;
        while (index < k) {
            for (int i = 0; i < size; i++) {
                //a,b分别为分子，分母对应arr中的index
                int a = datas.get(i);
                int b = i + 1;
                if (a >= b) {
                    continue;
                }
                if (min[0] == 0) {
                    min[0] = arr[a];
                    min[1] = arr[b];
                    minIndex = i;
                } else {
                    if (arr[a] * min[1] < arr[b] * min[0]) {
                        min[0] = arr[a];
                        min[1] = arr[b];
                        minIndex = i;
                    }
                }
            }
            //4，将最小分数存放到fractions中，重置min数组
            fractions[index] = new int[]{min[0], min[1]};
            index++;
            min[0] = 0;
            min[1] = 0;
            //5，更新datas的数据
            datas.set(minIndex, datas.get(minIndex) + 1);
        }
        return fractions[k - 1];
    }

    //使用多路归并
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        int length = arr.length;

        PriorityQueue<int[]> datas = new PriorityQueue<>((o1, o2) -> arr[o1[0]] * arr[o2[1]] - arr[o1[1]] * arr[o2[0]]);

        for (int i = 1; i < length; i++) {
            datas.offer(new int[]{0, i});
        }
        while (--k > 0) {
            int[] poll = datas.poll();
            if (poll[0] + 1 < poll[1]) {
                datas.offer(new int[]{poll[0] + 1, poll[1]});
            }
        }
        int[] poll = datas.poll();
        return new int[]{arr[poll[0]], arr[poll[1]]};
    }
}
