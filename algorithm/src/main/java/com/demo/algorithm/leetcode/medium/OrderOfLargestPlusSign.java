package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/11/9
 * @author chenglong
 * description : 最大加号标志
 *
 * 在一个n x n的矩阵grid中，除了在数组mines中给出的元素为0，其他每个元素都为1。mines[i]=[xi, yi]表示grid[xi][yi]==0
 * 返回grid中包含1的最大的轴对齐加号标志的阶数。如果未找到加号标志则返回0。
 * 一个k阶由1组成的“轴对称”加号标志具有中心网格grid[r][c]==1，以及4个从中心向上、向下、向左、向右延伸，长度为k-1，由1组成的臂。注意，只有加号标志的所有网格要求为1 ，别的网格可能为0也可能为1。
 *
 * 示例 1：
 * 输入: n = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 *
 * 示例 2：
 * 输入: n = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释: 没有加号标志，返回 0 。
 *
 * 提示：
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi< n
 * 每一对(xi, yi)都不重复
 */
public class OrderOfLargestPlusSign {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        //1，构建矩阵数据源没
        boolean[][] dates = new boolean[n][n];
        int length = mines.length;
        for (int i = 0; i < length; i++) {
            dates[mines[i][0]][mines[i][1]] = true;
        }
        //2，计算前缀和
        //记录行的前缀和
        int[][] rows = new int[n][n];
        //记录列的前缀和
        int[][] columns = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = dates[i][j] ? 0 : 1;
                if (j == 0) {
                    rows[i][j] = cur;
                } else {
                    rows[i][j] = rows[i][j - 1] + cur;
                }
                if (i == 0) {
                    columns[i][j] = cur;
                } else {
                    columns[i][j] = columns[i - 1][j] + cur;
                }
            }
        }
        //3，遍历查找最大加号
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dates[i][j]) {
                    continue;
                }
                for (int k = max + 1; k <= n; k++) {
                    //先查找横轴方向
                    int left = j - k + 1;
                    int right = j + k - 1;
                    if (left < 0 || right >= n) {
                        //长度区间越界，当前搜索无效
                        break;
                    }
                    int compare = right - left + 1;
                    boolean isFind = false;
                    if (left == 0) {
                        if (rows[i][right] == compare) {
                            //此时从left到right之间都是1
                            isFind = true;
                        }
                    } else {
                        if (rows[i][right] - rows[i][left - 1] == compare) {
                            isFind = true;
                        }
                    }
                    if (!isFind) {
                        break;
                    }
                    //查找纵轴方向
                    int top = i - k + 1;
                    int bottom = i + k - 1;
                    if (top < 0 || bottom >= n) {
                        break;
                    }
                    if (top == 0) {
                        if (columns[bottom][j] == compare) {
                            max = k;
                        }
                    } else {
                        if (columns[bottom][j] - columns[top - 1][j] == compare) {
                            max = k;
                        }
                    }
                }
            }
        }
        return max;
    }
}
