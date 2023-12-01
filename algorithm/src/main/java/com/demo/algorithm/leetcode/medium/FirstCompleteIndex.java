package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 找出叠涂元素
 *
 * 给你一个下标从0开始的整数数组arr和一个m x n的整数矩阵mat。arr和mat都包含范围[1，m * n]内的所有整数。
 * 从下标0开始遍历arr中的每个下标i，并将包含整数arr[i]的mat单元格涂色。
 * 请你找出arr中在mat的某一行或某一列上都被涂色且下标最小的元素，并返回其下标i。
 *
 * 示例 1：
 * image explanation for example 1
 * 输入：arr = [1,3,4,2], mat = [[1,4],[2,3]]
 * 输出：2
 * 解释：遍历如上图所示，arr[2] 在矩阵中的第一行或第二列上都被涂色。
 *
 * 示例 2：
 * image explanation for example 2
 * 输入：arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
 * 输出：3
 * 解释：遍历如上图所示，arr[3] 在矩阵中的第二列上都被涂色。
 *
 * 提示：
 * m == mat.length
 * n = mat[i].length
 * arr.length == m * n
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 1 <= arr[i], mat[r][c] <= m * n
 * arr中的所有整数互不相同
 * mat中的所有整数互不相同
 */
public class FirstCompleteIndex {

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        //1，记录矩阵中整数与位置的对应关系，便于后续查找
        int[] indexs = new int[m * n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                indexs[mat[i][j]] = index;
            }
        }
        //2，记录每行/列被着色的数量
        int[] columnCounts = new int[n];
        int[] rowCounts = new int[m];
        for (int i = 0; i < arr.length; i++) {
            //矩阵上着色的位置
            int index = indexs[arr[i]];
            int x = index / n;
            int y = index % n;
            rowCounts[x]++;
            columnCounts[y]++;
            if (rowCounts[x] == n || columnCounts[y] == m) {
                return i;
            }
        }
        return 0;
    }
}
