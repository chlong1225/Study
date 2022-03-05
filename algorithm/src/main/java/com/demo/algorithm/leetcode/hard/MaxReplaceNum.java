package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/3/1.
 * description : 最多可达成的换楼请求数目
 *
 * 我们有n栋楼，编号从0到n-1。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * 给你一个数组requests，其中requests[i]=[fromi, toi]，表示一个员工请求从编号为fromi的楼搬到编号为toi的楼。
 * 一开始所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足每栋楼员工净变化为0。意思是每栋楼离开的员工数目等于该楼搬入的员工数数目。
 * 比方说n=3且两个员工要离开楼0，一个员工要离开楼1，一个员工要离开楼2，如果该请求列表可行，应该要有两个员工搬入楼0，一个员工搬入楼1，一个员工搬入楼2。
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 *
 * 示例 1：
 * 输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
 * 输出：5
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
 * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
 * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
 * 没有员工从楼 4 离开。
 * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
 * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
 * 所以最多可以满足 5 个请求。
 *
 * 示例 2：
 * 输入：n = 3, requests = [[0,0],[1,2],[2,1]]
 * 输出：3
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
 * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
 * 我们可以满足所有的请求。
 *
 * 示例 3：
 * 输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
 * 输出：4
 *
 * 提示：
 * 1 <= n <= 20
 * 1 <= requests.length <= 16
 * requests[i].length == 2
 * 0 <= fromi, toi < n
 */
public class MaxReplaceNum {

    //记录当前遍历方式的最大有效请求
    private int max = 0;
    private int length = 0;

    public int maximumRequests(int n, int[][] requests) {

        /**
         * 由于这里最多16个请求。使用暴力枚举数据量为2^16=65536<10^5
         */
        max = 0;
        length = requests.length;
        //记录特殊场景：fromi=toi的数量
        int sameCount = 0;
        for (int i = 0; i < length; i++) {
            if (requests[i][0] == requests[i][1]) {
                sameCount++;
            }
        }
        //zero记录大楼人员有变化的数量
        int zero = 0;
        //记录对应楼人数变化情况，+1代表有人搬进来，-1代表有人搬出去
        int[] counts = new int[n];
        dfs(0, 0, zero, requests, counts);
        return max + sameCount;
    }

    private void dfs(int index, int count, int zero, int[][] requests, int[] counts) {
        if (index == length) {
            if (zero == 0) {
                if (count > max) {
                    max = count;
                }
            }
            return;
        }
        //当前的请求
        int[] request = requests[index];
        if (request[0] == request[1]) {
            //=特殊场景fromitoi
            dfs(index + 1, count, zero, requests, counts);
            return;
        }
        //入和出不一致时分类讨论
        //1，不处理当前的请求
        dfs(index + 1, count, zero, requests, counts);
        //2，处理当前的请求
        counts[request[0]]--;
        counts[request[1]]++;
        if (counts[request[0]] == 0) {
            //代表之前楼有1个人，属于不平衡，现在平衡了
            zero--;
        } else if (counts[request[0]] == -1) {
            //代表当前楼之前人员平衡，现在减少了一个
            zero++;
        }
        if (counts[request[1]] == 0) {
            zero--;
        } else if (counts[request[1]] == 1) {
            zero++;
        }
        dfs(index + 1, count + 1, zero, requests, counts);
        counts[request[0]]++;
        counts[request[1]]--;
    }
}
