package com.demo.algorithm.leetcode.medium.tree;

/**
 * Created by chl on 2022/5/3.
 * description : 建立四叉树
 *
 * 给你一个n*n矩阵grid，矩阵由若干0和1组成。请你用四叉树表示该矩阵grid。
 * 你需要返回能表示矩阵的四叉树的根结点。
 *
 * 注意，当isLeaf为False时，你可以把True或者False赋值给节点，两种值都会被判题机制接受 。
 *
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * val：储存叶子结点所代表的区域的值。1对应True，0对应False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有4个子节点则为False 。
 * 我们可以按以下步骤为二维区域构建四叉树：
 * 如果当前网格的值相同（即，全为 0 或者全为 1），将isLeaf设为True ，将val设为网格相应的值，并将四个子节点都设为Null然后停止。
 * 如果当前网格的值不同，将isLeaf设为 False， 将val设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 *
 * 如果你想了解更多关于四叉树的内容，可以参考 wiki 。
 *
 * 四叉树格式：
 * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表[isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 *
 * 示例 1：
 * 输入：grid = [[0,1],[1,0]]
 * 输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
 * 解释：此示例的解释如下：
 * 请注意，在下面四叉树的图示中，0 表示 false，1 表示 True 。
 *
 * 示例 2：
 * 输入：grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * 输出：[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * 解释：网格中的所有值都不相同。我们将网格划分为四个子网格。
 * topLeft，bottomLeft 和 bottomRight 均具有相同的值。
 * topRight 具有不同的值，因此我们将其再分为 4 个子网格，这样每个子网格都具有相同的值。
 * 解释如下图所示：
 *
 * 示例 3：
 * 输入：grid = [[1,1],[1,1]]
 * 输出：[[1,1]]
 *
 * 示例 4：
 * 输入：grid = [[0]]
 * 输出：[[1,0]]
 *
 * 示例 5：
 * 输入：grid = [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]
 * 输出：[[0,1],[1,1],[1,0],[1,0],[1,1]]
 *
 * 提示：
 * n == grid.length == grid[i].length
 * n == 2^x 其中 0 <= x <= 6
 */
public class Construct {

    public Node construct(int[][] grid) {
        //前缀和，用于快速判断是否都为1或0
        int n = grid.length;
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + grid[j - 1][i - 1];
            }
        }
        return dfs(0, n - 1, 0, n - 1, sum);
    }

    private Node dfs(int startX, int endX, int startY, int endY, int[][] sum) {
        int total = sum[endX + 1][endY + 1] + sum[startX][startY] - sum[endX + 1][startY] - sum[startX][endY + 1];
        int count = (endX - startX + 1) * (endY - startY + 1);
        if (total == 0) {
            return new Node(false, true, null, null, null, null);
        }
        if (total == count) {
            //都是1
            return new Node(true, true, null, null, null, null);
        }
        Node node = new Node(false, false);
        int size = (endX - startX + 1) / 2;
        node.topLeft = dfs(startX, startX + size - 1, startY, startY + size - 1, sum);
        node.topRight = dfs(startX + size, endX, startY, startY + size - 1, sum);
        node.bottomLeft = dfs(startX, startX + size - 1, startY+size, endY, sum);
        node.bottomRight = dfs(startX + size, endX, startY + size, endY, sum);
        return node;
    }

    public static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
