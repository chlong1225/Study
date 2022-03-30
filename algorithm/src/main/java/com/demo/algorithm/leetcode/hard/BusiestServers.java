package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * create on 2022/3/30
 * @author chenglong
 * description : 找到处理最多请求的服务器
 *
 * 你有k个服务器，编号为0到k-1，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是不能同时处理超过一个请求。请求分配到服务器的规则如下：
 * 第i（序号从0开始个请求到达。
 * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
 * 如果第(i%k)个服务器空闲，那么对应服务器会处理该请求。
 * 否则将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第0个服务器开始继续找下一个空闲的服务器）。比方说，如果第i个服务器在忙，那么会查看第(i+1)个服务器，第(i+2)个服务器等等。
 * 给你一个严格递增的正整数数组arrival，表示第i个任务的到达时间，和另一个数组load，其中load[i]表示第i个请求的工作量（也就是服务器完成它所需要的时间）。
 * 你的任务是找到最繁忙的服务器。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
 *
 * 请你返回包含所有最繁忙服务器序号的列表，你可以以任意顺序返回这个列表。
 *
 * 示例 1：
 * 输入：k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
 * 输出：[1]
 * 解释：
 * 所有服务器一开始都是空闲的。
 * 前 3 个请求分别由前 3 台服务器依次处理。
 * 请求 3 进来的时候，服务器 0 被占据，所以它呗安排到下一台空闲的服务器，也就是服务器 1 。
 * 请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
 * 服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
 *
 * 示例 2：
 * 输入：k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
 * 输出：[0]
 * 解释：
 * 前 3 个请求分别被前 3 个服务器处理。
 * 请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
 * 服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
 *
 * 示例 3：
 * 输入：k = 3, arrival = [1,2,3], load = [10,12,11]
 * 输出：[0,1,2]
 * 解释：每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
 *
 * 示例 4：
 * 输入：k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
 * 输出：[1]
 *
 * 示例 5：
 * 输入：k = 1, arrival = [1], load = [1]
 * 输出：[0]
 *
 * 提示：
 * 1 <= k <= 10^5
 * 1 <= arrival.length, load.length <= 10^5
 * arrival.length == load.length
 * 1 <= arrival[i], load[i] <= 10^9
 * arrival保证严格递增。
 */
public class BusiestServers {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> result = new ArrayList<>();
        //1，统计每个服务器处理任务的数量，同时记录最大处理数量
        int max = 0;
        int[] counts = new int[k];
        int length = arrival.length;
        if (length <= k) {
            //此时刚好可以每个服务器安排一个任务
            for (int i = 0; i < length; i++) {
                result.add(i);
            }
            return result;
        }
        //2，前k个任务可以直接执行，记录服务器位置以及需要执行最晚的时间
        max = 1;
        //使用优先队列存储当前服务器状态。包含：任务截止时间，服务器编号
        PriorityQueue<int[]> dates = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < k; i++) {
            counts[i]++;
            int end = arrival[i] + load[i];
            int[] item = {end, i};
            dates.offer(item);
        }
        //记录空闲的服务器编号
        TreeSet<Integer> frees = new TreeSet<>();
        for (int i = k; i < length; i++) {
            //当前任务到达的时间
            int time = arrival[i];
            while (!dates.isEmpty()) {
                if (dates.peek()[0] <= time) {
                    frees.add(dates.peek()[1]);
                    dates.poll();
                } else {
                    break;
                }
            }
            if (frees.isEmpty()) {
                //没有空闲服务器
                continue;
            }
            if (frees.ceiling(i % k) != null) {
                int index = frees.ceiling(i % k);
                counts[index]++;
                int end = time + load[i];
                dates.offer(new int[]{end, i});
                if (counts[index] > max) {
                    max = counts[index];
                }
            }
        }
        //统计所有最大执行次数的服务器id
        for (int i = 0; i < k; i++) {
            if (counts[i] == max) {
                result.add(i);
            }
        }
        return result;
    }
}
