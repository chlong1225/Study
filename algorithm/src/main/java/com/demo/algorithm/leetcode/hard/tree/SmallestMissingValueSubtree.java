package com.demo.algorithm.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/10/31
 * @author chenglong
 * description : 每棵子树内缺失的最小基因值
 *
 * 有一棵根节点为0的家族树，总共包含n个节点，节点编号为0到n-1。给你一个下标0开始的整数数组parents，其中parents[i]是节点i的父节点。
 * 由于节点0是根，所以parents[0]==-1。
 * 总共有10^5个基因值，每个基因值都用闭区间[1, 10^5]中的一个整数表示。给你一个下标从0开始的整数数组nums，其中nums[i]是节点i的基因值，且基因值互不相同。
 * 请你返回一个数组ans，长度为n，其中ans[i]是以节点i为根的子树内缺失的最小基因值。
 * 节点x为根的子树包含节点x和它所有的后代节点。
 *
 * 示例 1：
 * 输入：parents = [-1,0,0,2], nums = [1,2,3,4]
 * 输出：[5,1,1,1]
 * 解释：每个子树答案计算结果如下：
 * - 0：子树包含节点 [0,1,2,3] ，基因值分别为 [1,2,3,4] 。5 是缺失的最小基因值。
 * - 1：子树只包含节点 1 ，基因值为 2 。1 是缺失的最小基因值。
 * - 2：子树包含节点 [2,3] ，基因值分别为 [3,4] 。1 是缺失的最小基因值。
 * - 3：子树只包含节点 3 ，基因值为 4 。1是缺失的最小基因值。
 *
 * 示例 2：
 * 输入：parents = [-1,0,1,0,3,3], nums = [5,4,6,2,1,3]
 * 输出：[7,1,1,4,2,1]
 * 解释：每个子树答案计算结果如下：
 * - 0：子树内包含节点 [0,1,2,3,4,5] ，基因值分别为 [5,4,6,2,1,3] 。7 是缺失的最小基因值。
 * - 1：子树内包含节点 [1,2] ，基因值分别为 [4,6] 。 1 是缺失的最小基因值。
 * - 2：子树内只包含节点 2 ，基因值为 6 。1 是缺失的最小基因值。
 * - 3：子树内包含节点 [3,4,5] ，基因值分别为 [2,1,3] 。4 是缺失的最小基因值。
 * - 4：子树内只包含节点 4 ，基因值为 1 。2 是缺失的最小基因值。
 * - 5：子树内只包含节点 5 ，基因值为 3 。1 是缺失的最小基因值。
 *
 * 示例 3：
 * 输入：parents = [-1,2,3,0,2,4,1], nums = [2,3,4,5,6,7,8]
 * 输出：[1,1,1,1,1,1,1]
 * 解释：所有子树都缺失基因值 1 。
 *
 * 提示：
 * n == parents.length == nums.length
 * 2 <= n <= 10^5
 * 对于 i != 0 ，满足 0 <= parents[i] <= n - 1
 * parents[0] == -1
 * parents 表示一棵合法的树。
 * 1 <= nums[i] <= 10^5
 * nums[i] 互不相同。
 */
public class SmallestMissingValueSubtree {

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        int[] answers = new int[n];
        //1，初始最小值1
        for (int i = 0; i < n; i++) {
            answers[i] = 1;
        }
        //2，查找基因值为1的节点
        int findIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                findIndex = i;
                break;
            }
        }
        if (findIndex == -1) {
            //特殊场景，此时整个树都没有基因1，缺失的最小基因值为1
            return answers;
        }
        //3，统计父节点对应的子节点
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            dates.get(parents[i]).add(i);
        }
        //4，findIndex对应的父节点以上需要查找，其它没有关联的节点你统一缺失最小基因值为1
        int min = 2;
        boolean[] marks = new boolean[n + 10];
        int childIndex = -1;
        while (findIndex != -1) {
            dfs(findIndex, childIndex, dates, nums, marks);
            while (marks[min]) {
                min++;
            }
            answers[findIndex] = min;
            childIndex = findIndex;
            findIndex = parents[findIndex];
        }
        return answers;
    }

    private void dfs(int parent, int child, List<List<Integer>> dates, int[] nums, boolean[] marks) {
        int max = marks.length - 1;
        marks[Math.min(nums[parent], max)] = true;
        List<Integer> childs = dates.get(parent);
        if (childs.size() > 0) {
            for (int i = 0; i < childs.size(); i++) {
                if (childs.get(i) == child) {
                    continue;
                }
                dfs(childs.get(i), -1, dates, nums, marks);
            }
        }
    }
}
