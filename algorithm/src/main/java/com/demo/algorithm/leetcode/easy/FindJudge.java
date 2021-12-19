package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/12/19.
 * description : 找到小镇的法官
 *
 * 在一个小镇里，按从1到n为n个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
 * 如果小镇的法官真的存在，那么：
 * 小镇的法官不相信任何人。
 * 每个人（除了小镇法官外）都信任小镇的法官。
 * 只有一个人同时满足条件 1 和条件 2 。
 * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示编号为 a 的人信任编号为 b 的人。
 * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
 *
 * 示例 1：
 * 输入：n = 2, trust = [[1,2]]
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 3, trust = [[1,3],[2,3]]
 * 输出：3
 *
 * 示例 3：
 * 输入：n = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 *
 * 示例 4：
 * 输入：n = 3, trust = [[1,2],[2,3]]
 * 输出：-1
 *
 * 示例 5：
 * 输入：n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * 输出：3
 *  
 * 提示：
 * 1 <= n <= 1000
 * 0 <= trust.length <= 104
 * trust[i].length == 2
 * trust[i] 互不相同
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= n
 */
public class FindJudge {

    public int findJudge(int n, int[][] trust) {
        int length = trust.length;
        if (length == 0) {
            if (n == 1) {
                return n;
            }
            return -1;
        }
        int[] trustCount = new int[n + 1];
        int maxCount = 0;
        int maxIndex = 0;
        for (int i = 0; i < length; i++) {
            //信任别人的肯定不是法官,信任人数直接设置为-1
            trustCount[trust[i][0]] = -1;
            //被信任人的编号
            int index = trust[i][1];
            if (trustCount[index] != -1) {
                trustCount[index]++;
                if (trustCount[index] > maxCount) {
                    maxCount = trustCount[index];
                    maxIndex = index;
                }
            }
        }
        if (trustCount[maxIndex] == n - 1) {
            return maxIndex;
        }
        return -1;
    }
}
