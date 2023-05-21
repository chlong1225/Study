package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/23
 * @author chenglong
 * description : 填充书架
 *
 * 给定一个数组books，其中books[i] = [thicknessi, heighti]表示第i本书的厚度和高度。你也会得到一个整数shelfWidth。
 * 按顺序将这些书摆放到总宽度为shelfWidth的书架上。
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度shelfWidth），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
 * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 *
 * 示例 1：
 * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
 * 输出：6
 * 解释：
 * 3 层书架的高度和为 1 + 3 + 2 = 6 。
 * 第 2 本书不必放在第一层书架上。
 *
 * 示例 2:
 * 输入: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
 * 输出: 4
 *
 * 提示：
 * 1 <= books.length <= 1000
 * 1 <= thicknessi<= shelfWidth <= 1000
 * 1 <= heighti<= 1000
 */
public class MinHeightShelves {

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] marks = new int[n + 1];
        marks[0] = 0;
        for (int i = 1; i <= n; i++) {
            //此时存在场景：i-1单独一层，i-1，i-2一层，i-1，i-2...i-k一层。取最小值
            int maxHeight = books[i - 1][1];
            int width = books[i - 1][0];
            marks[i] = marks[i - 1] + maxHeight;
            for (int j = i - 1; j >= 1; j--) {
                width += books[j - 1][0];
                if (width > shelfWidth) {
                    break;
                }
                maxHeight = Math.max(maxHeight, books[j - 1][1]);
                marks[i] = Math.min(marks[i], marks[j-1] + maxHeight);
            }
        }
        return marks[n];
    }
}
