package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/3/20.
 * description : 网络空闲的时刻
 *
 * 给你一个有n个服务器的计算机网络，服务器编号为0到n-1。同时给你一个二维整数数组edges，
 * 其中edges[i]=[ui, vi]表示服务器ui和vi之间有一条信息线路，在一秒内它们之间可以传输任意数目的信息。
 * 再给你一个长度为n且下标从0开始的整数数组patience。
 * 题目保证所有服务器都是相通的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。
 * 编号为0的服务器是主服务器，其他服务器为数据服务器。每个数据服务器都要向主服务器发送信息，并等待回复。信息在服务器之间按最优线路传输，
 * 也就是说每个信息都会以最少时间到达主服务器。主服务器会处理所有新到达的信息并立即按照每条信息来时的路线反方向发送回复信息。
 * 在0秒的开始，所有数据服务器都会发送各自需要处理的信息。从第1秒开始，每一秒最开始时，每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：
 * 如果还没收到任何回复信息，那么该服务器会周期性重发信息。数据服务器i每patience[i]秒都会重发一条信息，也就是说，数据服务器i在上一次发送信息给主服务器后的 patience[i]秒后会重发一条信息给主服务器。
 * 否则，该数据服务器不会重发信息。
 * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为空闲状态。
 * 请返回计算机网络变为空闲状态的最早秒数。
 *
 * 示例 1：
 * 输入：edges = [[0,1],[1,2]], patience = [0,2,1]
 * 输出：8
 * 解释：
 * 0 秒最开始时，
 * - 数据服务器 1 给主服务器发出信息（用 1A 表示）。
 * - 数据服务器 2 给主服务器发出信息（用 2A 表示）。
 *
 * 1 秒时，
 * - 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
 * - 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 < patience[1] = 2），所以不会重发信息。
 * - 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。
 *
 * 2 秒时，
 * - 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
 * - 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
 * - 服务器 2 重发一条信息（用 2C 表示）。
 * ...
 * 4 秒时，
 * - 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
 * ...
 * 7 秒时，回复信息 2D 到达服务器 2 。
 *
 * 从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
 * 所以第 8 秒是网络变空闲的最早时刻。
 *
 * 示例 2：
 * 输入：edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
 * 输出：3
 * 解释：数据服务器 1 和 2 第 2 秒初收到回复信息。
 * 从第 3 秒开始，网络变空闲。
 *
 * 提示：
 * n == patience.length
 * 2 <= n <= 10^5
 * patience[0] == 0
 * 对于1<=i<n ，满足1<=patience[i]<=10^5
 * 1 <= edges.length <= min(10^5, n*(n-1)/2)
 * edges[i].length == 2
 * 0 <= ui, vi < n
 * ui != vi
 * 不会有重边。
 * 每个服务器都直接或间接与别的服务器相连。
 */
public class NetFreeTime {

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        //1，构建图的数据结构
        int n = patience.length;
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //2，统计每个服务器到主服务器需要的时间
        int[] times = new int[n];
        times[0] = -1;
        List<Integer> nums = new ArrayList<>();
        nums.addAll(dates.get(0));
        Set<Integer> next = new HashSet<>();
        int step = 1;
        while (nums.size() > 0) {
            for (int i = 0; i < nums.size(); i++) {
                if (times[nums.get(i)] == 0) {
                    times[nums.get(i)] = step;
                    List<Integer> tem = dates.get(nums.get(i));
                    if (tem.size() > 0) {
                        for (int j = 0; j < tem.size(); j++) {
                            if (times[tem.get(j)] == 0) {
                                next.add(tem.get(j));
                            }
                        }
                    }
                }
            }
            step++;
            nums.clear();
            nums.addAll(next);
            next.clear();
        }
        //3，计算最多消费的时间
        int max = 0;
        for (int i = 1; i < n; i++) {
            //数据服务器发送到主服务器并收到回复需要的时间
            int spend = times[i] * 2;
            if (spend > patience[i]) {
                //存在重复发送消息
                if (spend % patience[i] == 0) {
                    spend += (spend - patience[i]);
                } else {
                    spend += (spend - (spend % patience[i]));
                }
            }
            if (max < spend) {
                max = spend;
            }
        }

        return max + 1;
    }
}
