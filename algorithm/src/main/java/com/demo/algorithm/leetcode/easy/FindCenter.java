package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/2/18
 * @author chenglong
 * description : 找出星型图的中心节点
 *
 * 有一个无向的星型图，由n个编号从1到n的节点组成。星型图有一个中心节点，并且恰有n-1条边将中心节点与其他每个节点连接起来。
 * 给你一个二维整数数组edges，其中edges[i] = [ui, vi] 表示在节点ui和vi之间存在一条边。请你找出并返回edges所表示星型图的中心节点。
 *
 * 示例 1：
 * 输入：edges = [[1,2],[2,3],[4,2]]
 * 输出：2
 * 解释：如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
 *
 * 示例 2：
 * 输入：edges = [[1,2],[5,1],[1,3],[1,4]]
 * 输出：1
 *
 * 提示
 * 3 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 题目数据给出的 edges 表示一个有效的星型图
 */
public class FindCenter {

    public int findCenter(int[][] edges) {
        /**
         * 经分析：edges中包含的所有数字数量为：2*(n-1)。其中中心节点count = n-1。
         * 剩余节点数量为：n-1。星型图有n个节点组合，除去中心节点，剩余n-1个节点。
         * 经对比发现非中心节点必须只出现一次，只有中心节点才能出现多次(n-1)
         */
        int[] data1 = edges[0];
        int[] data2 = edges[1];
        if (data1[0] == data2[0] || data1[0] == data2[1]) {
            return data1[0];
        }
        return data1[1];
    }
}
