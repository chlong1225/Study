package com.demo.algorithm.leetcode.contest.week299;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/6/26.
 * description : 从树中删除边的最小分数
 * <p>
 * 存在一棵无向连通树，树中有编号从0到n-1的n个节点，以及n-1条边。
 * 给你一个下标从0开始的整数数组nums ，长度为n ，其中nums[i] 表示第i个节点的值。
 * 另给你一个二维整数数组edges ，长度为n-1 ，其中edges[i]=[ai, bi]表示树中存在一条位于节点ai和bi之间的边。
 * 删除树中两条不同的边以形成三个连通组件。对于一种删除边方案，定义如下步骤以计算其分数：
 * 分别获取三个组件每个组件中所有节点值的异或值。
 * 最大异或值和最小异或值的差值就是这一种删除边方案的分数。
 * 例如，三个组件的节点值分别是：[4,5,7]、[1,9] 和 [3,3,3] 。
 * 三个异或值分别是 4 ^ 5 ^ 7 = 6、1 ^ 9 = 8 和 3 ^ 3 ^ 3 = 3 。最大异或值是 8 ，最小异或值是 3 ，分数是 8 - 3 = 5 。
 * 返回在给定树上执行任意删除边方案可能的最小分数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
 * 输出：9
 * 解释：上图展示了一种删除边方案。
 * - 第 1 个组件的节点是 [1,3,4] ，值是 [5,4,11] 。异或值是 5 ^ 4 ^ 11 = 10 。
 * - 第 2 个组件的节点是 [0] ，值是 [1] 。异或值是 1 = 1 。
 * - 第 3 个组件的节点是 [2] ，值是 [5] 。异或值是 5 = 5 。
 * 分数是最大异或值和最小异或值的差值，10 - 1 = 9 。
 * 可以证明不存在分数比 9 小的删除边方案。
 * <p>
 * 示例 2：
 * 输入：nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
 * 输出：0
 * 解释：上图展示了一种删除边方案。
 * - 第 1 个组件的节点是 [3,4] ，值是 [4,4] 。异或值是 4 ^ 4 = 0 。
 * - 第 2 个组件的节点是 [1,0] ，值是 [5,5] 。异或值是 5 ^ 5 = 0 。
 * - 第 3 个组件的节点是 [2,5] ，值是 [2,2] 。异或值是 2 ^ 2 = 0 。
 * 分数是最大异或值和最小异或值的差值，0 - 0 = 0 。
 * 无法获得比 0 更小的分数 0 。
 * <p>
 * 提示：
 * n == nums.length
 * 3 <= n <= 1000
 * 1 <= nums[i] <= 10^8
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 */
public class MinimumScore {

    private List<Integer>[] graph;
    private int[] dates;
    private int[] xor;
    private int[] in;
    private int[] out;
    private int clock;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        dates = nums;
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        //1，构建图的数据
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        //2，遍历节点的异或值
        dfs(0, -1);
        int min = Integer.MAX_VALUE;
        int x = 0;
        int y = 0;
        int z = 0;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                if (in[i] < in[j] && in[j] <= out[i]) {
                    x = xor[j];
                    y = xor[i] ^ x;
                    z = xor[0] ^ xor[i];
                } else if (in[j] < in[i] && in[i] <= out[j]) {
                    x = xor[i];
                    y = xor[j] ^ x;
                    z = xor[0] ^ xor[j];
                } else {
                    x = xor[i];
                    y = xor[j];
                    z = xor[0] ^ x ^ y;
                }
                int tem = Math.max(x, Math.max(y, z)) - Math.min(x, Math.min(y, z));
                if (tem < min) {
                    min = tem;
                }
                if (min == 0) {
                    return 0;
                }
            }
        }
        return min;
    }

    private void dfs(int x, int f) {
        in[x] = ++clock;
        xor[x] = dates[x];
        List<Integer> items = graph[x];
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != f) {
                dfs(items.get(i), x);
                xor[x] ^= xor[items.get(i)];
            }
        }
        out[x] = clock;
    }
}
