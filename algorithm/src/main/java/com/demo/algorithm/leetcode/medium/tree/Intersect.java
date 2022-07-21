package com.demo.algorithm.leetcode.medium.tree;

/**
 * create on 2022/7/21
 * @author chenglong
 * description : 四叉树交集
 *
 * 二进制矩阵中的所有元素不是0就是1。
 * 给你两个四叉树，quadTree1和quadTree2。其中quadTree1表示一个n * n二进制矩阵，而quadTree2表示另一个n * n二进制矩阵。
 * 请你返回一个表示n * n二进制矩阵的四叉树，它是quadTree1和quadTree2所表示的两个二进制矩阵进行按位逻辑或运算的结果。
 * 注意，当isLeaf为False时，你可以把True或者False赋值给节点，两种值都会被判题机制接受。
 *
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * val：储存叶子结点所代表的区域的值。1 对应True，0对应False；
 * isLeaf: 当这个节点是一个叶子结点时为True，如果它有4个子节点则为False 。
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 * 我们可以按以下步骤为二维区域构建四叉树：
 * 如果当前网格的值相同（即，全为0或者全为1），将isLeaf设为True ，将val设为网格相应的值，并将四个子节点都设为Null然后停止。
 * 如果当前网格的值不同，将isLeaf设为False， 将val设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 *
 * 四叉树格式：
 * 输出为使用层序遍历后四叉树的序列化形式，其中null表示路径终止符，其下面不存在节点。
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * 如果isLeaf或者val的值为True ，则表示它在列表[isLeaf, val]中的值为 1 ；如果isLeaf或者val的值为False，则表示值为0。
 *
 * 示例 1：
 * 输入：quadTree1 = [[0,1],[1,1],[1,1],[1,0],[1,0]]
 * , quadTree2 = [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * 输出：[[0,0],[1,1],[1,1],[1,1],[1,0]]
 * 解释：quadTree1 和 quadTree2 如上所示。由四叉树所表示的二进制矩阵也已经给出。
 * 如果我们对这两个矩阵进行按位逻辑或运算，则可以得到下面的二进制矩阵，由一个作为结果的四叉树表示。
 * 注意，我们展示的二进制矩阵仅仅是为了更好地说明题意，你无需构造二进制矩阵来获得结果四叉树。
 *
 * 示例 2：
 * 输入：quadTree1 = [[1,0]]
 * , quadTree2 = [[1,0]]
 * 输出：[[1,0]]
 * 解释：两个数所表示的矩阵大小都为 1*1，值全为 0
 * 结果矩阵大小为 1*1，值全为 0 。
 *
 * 示例 3：
 * 输入：quadTree1 = [[0,0],[1,0],[1,0],[1,1],[1,1]]
 * , quadTree2 = [[0,0],[1,1],[1,1],[1,0],[1,1]]
 * 输出：[[1,1]]
 *
 * 示例 4：
 * 输入：quadTree1 = [[0,0],[1,1],[1,0],[1,1],[1,1]]
 * , quadTree2 = [[0,0],[1,1],[0,1],[1,1],[1,1],null,null,null,null,[1,1],[1,0],[1,0],[1,1]]
 * 输出：[[0,0],[1,1],[0,1],[1,1],[1,1],null,null,null,null,[1,1],[1,0],[1,0],[1,1]]
 *
 * 示例 5：
 * 输入：quadTree1 = [[0,1],[1,0],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * , quadTree2 = [[0,1],[0,1],[1,0],[1,1],[1,0],[1,0],[1,0],[1,1],[1,1]]
 * 输出：[[0,0],[0,1],[0,1],[1,1],[1,0],[1,0],[1,0],[1,1],[1,1],[1,0],[1,0],[1,1],[1,1]]
 *
 * 提示：
 * quadTree1 和 quadTree2 都是符合题目要求的四叉树，每个都代表一个 n * n 的矩阵。
 * n == 2^x ，其中 0 <= x <= 9.
 */
public class Intersect {

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            //四个网格值相同
            if (quadTree1.val) {
                //全部为1，或任意值都是1
                return quadTree1;
            } else {
                //全部为0，或任意值都是对应的值
                return quadTree2;
            }
        }
        if (quadTree2.isLeaf) {
            //四个网格相同
            if (quadTree2.val) {
                return quadTree2;
            } else {
                return quadTree1;
            }
        }
        //此时quadTree1与quadTree2的四个都不相同
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true, null, null, null, null);
        }
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };
}
