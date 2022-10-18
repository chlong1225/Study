package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * create on 2022/9/12
 * @author chenglong
 * description : 雇佣K名工人的最低成本
 *
 * 有n名工人。给定两个数组quality和wage，其中，quality[i]表示第i名工人的工作质量，其最低期望工资为wage[i]。
 * 现在我们想雇佣k名工人组成一个工资组。在雇佣一组k名工人时，我们必须按照下述规则向他们支付工资：
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 给定整数k，返回组成满足上述条件的付费群体所需的最小金额。在实际答案的10^-5以内的答案将被接受。
 *
 * 示例 1：
 * 输入： quality = [10,20,5], wage = [70,50,30], k = 2
 * 输出： 105.00000
 * 解释： 我们向0号工人支付70，向2号工人支付35。
 *
 * 示例 2：
 * 输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * 输出： 30.66667
 * 解释： 我们向0号工人支付4，向2号和3号分别支付13.33333。
 *
 * 提示：
 * n == quality.length == wage.length
 * 1 <= k <= n <= 10^4
 * 1 <= quality[i], wage[i] <= 10^4
 */
public class MincostToHireWorkers {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int length = quality.length;
        double[][] marks = new double[length][2];
        for (int i = 0; i < length; i++) {
            marks[i][0] = wage[i] * 1.0 / quality[i];
            marks[i][1] = i;
        }
        Arrays.sort(marks, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[0], o2[0]);
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        double min = Integer.MAX_VALUE;
        int total = 0;
        for (int i = 0; i < length; i++) {
            int cur = quality[(int) marks[i][1]];
            total += cur;
            queue.add(cur);
            if (queue.size() > k) {
                total -= queue.poll();
            }
            if (queue.size() == k) {
                double tem = marks[i][0] * total;
                if (min > tem) {
                    min = tem;
                }
            }
        }
        return min;
    }
}