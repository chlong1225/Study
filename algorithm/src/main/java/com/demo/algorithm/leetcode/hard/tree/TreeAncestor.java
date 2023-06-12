package com.demo.algorithm.leetcode.hard.tree;

/**
 * create on 2023/6/12
 * @author chenglong
 * description : 树节点的第K个祖先
 *
 * 给你一棵树，树上有n个节点，按从0到n-1编号。树以父节点数组的形式给出，其中parent[i]是节点i的父节点。树的根节点是编号为0的节点。
 * 树节点的第k个祖先节点是从该节点到根节点路径上的第k个节点。
 *
 * 实现 TreeAncestor 类：
 * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 * getKthAncestor(int node, int k)返回节点node的第k个祖先节点。如果不存在这样的祖先节点，返回-1。
 *
 * 示例 1：
 * 输入：
 * ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 * [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
 * 输出：
 * [null,1,0,-1]
 *
 * 解释：
 * TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 * treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
 * treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
 * treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
 *
 * 提示：
 * 1 <= k <= n <= 5 * 10^4
 * parent[0] == -1 表示编号为0的节点是根节点。
 * 对于所有的0 <i < n ，0 <= parent[i] < n 总成立
 * 0 <= node < n
 * 至多查询5 * 10^4次
 */
public class TreeAncestor {

    private final int[] steps = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
    private int[][] marks;

    public TreeAncestor(int n, int[] parent) {
        marks = new int[n][17];
        for (int i = 0; i < n; i++) {
            marks[i][0] = parent[i];
        }
        for (int i = 1; i <= 16; i++) {
            for (int j = 0; j < n; j++) {
                if (marks[j][i - 1] == -1) {
                    marks[j][i] = -1;
                } else {
                    marks[j][i] = marks[marks[j][i - 1]][i - 1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        while (k > 0 && node > -1) {
            //跳跃次数
            int count = 0;
            int step = k;
            while (step >= 2) {
                step /= 2;
                count++;
            }
            node = marks[node][count];
            k -= steps[count];
        }
        return node;
    }
}
