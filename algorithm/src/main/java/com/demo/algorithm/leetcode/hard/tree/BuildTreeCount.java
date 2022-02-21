package com.demo.algorithm.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chl on 2022/2/19.
 * description : 重构一棵树的方案数
 *
 * 给你一个数组pairs，其中pairs[i]=[xi, yi]，并且满足：
 * pairs中没有重复元素
 * xi < yi
 * 令ways为满足下面条件的有根树的方案数：
 * 树所包含的所有节点值都在pairs中。
 * 一个数对[xi, yi]出现在pairs中当且仅当xi是yi的祖先或者yi是xi的祖先。
 * 注意：构造出来的树不一定是二叉树。
 * 两棵树被视为不同的方案当存在至少一个节点在两棵树中有不同的父节点。
 * 请你返回：
 * 如果ways == 0，返回0。
 * 如果ways == 1，返回1。
 * 如果ways>1，返回2。
 * 一棵有根树指的是只有一个根节点的树，所有边都是从根往外的方向。
 * 我们称从根到一个节点路径上的任意一个节点（除去节点本身）都是该节点的祖先。根节点没有祖先
 *
 * 示例 1：
 * 输入：pairs = [[1,2],[2,3]]
 * 输出：1
 * 解释：如上图所示，有且只有一个符合规定的有根树。
 *
 * 示例 2：
 * 输入：pairs = [[1,2],[2,3],[1,3]]
 * 输出：2
 * 解释：有多个符合规定的有根树，其中三个如上图所示。
 *
 * 示例 3：
 * 输入：pairs = [[1,2],[2,3],[2,4],[1,5]]
 * 输出：0
 * 解释：没有符合规定的有根树。
 *
 * 提示：
 * 1 <= pairs.length <= 10^5
 * 1 <= xi < yi <= 500
 * pairs中的元素互不相同。
 */
public class BuildTreeCount {

    public int checkWays(int[][] pairs) {
        int length = pairs.length;
        //1，构建图的数据结构
        Map<Integer, Set<Integer>> marks = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (marks.get(pairs[i][0]) == null) {
                Set<Integer> item = new HashSet<>();
                item.add(pairs[i][1]);
                marks.put(pairs[i][0], item);
                keys.add(pairs[i][0]);
            } else {
                marks.get(pairs[i][0]).add(pairs[i][1]);
            }
            if (marks.get(pairs[i][1]) == null) {
                Set<Integer> item = new HashSet<>();
                item.add(pairs[i][0]);
                marks.put(pairs[i][1], item);
                keys.add(pairs[i][1]);
            } else {
                marks.get(pairs[i][1]).add(pairs[i][0]);
            }
        }
        //2，对比节点数量与数对。最少数对情况时：一个根节点+其它叶子节点。此时：数对 = 节点-1
        int n = keys.size();
        if (length < n - 1) {
            return 0;
        }
        //3，根据节点的度进行排序，从大到小
        Collections.sort(keys, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return marks.get(o2).size() - marks.get(o1).size();
            }
        });
        //4，判断是否存在根节点。根节点的特点时。度为：节点数量-1.即与其它所有的节点都相连
        int max = marks.get(keys.get(0)).size();
        if (max < keys.size() - 1) {
            return 0;
        }
        //5，遍历验证其它节点是否能够构建成树
        int count = 1;
        for (int i = 1; i < n; i++) {
            //当前节点
            int cur = keys.get(i);
            Set<Integer> curDatas = marks.get(cur);
            //6，查找是否存在父节点，需要遍历i-1~0之间的key。同时父节点肯定在cur的连接元素中
            for (int j = i - 1; j >= 0; j--) {
                int parent = keys.get(j);
                if (curDatas.contains(parent)) {
                    //只要包含在curDatas中的节点,必须是当前cur的子节点或父节点,如果能构成树则略大的肯定是父节点
                    if (isContain(parent, cur, marks)) {
                        if (marks.get(cur).size() == marks.get(parent).size()) {
                            count = 2;
                        }
                        break;
                    } else {
                        return 0;
                    }
                }
            }
        }
        return count;
    }

    //查找的parent节点对应元素是否包含cur节点对应的元素
    private boolean isContain(int parent, int cur, Map<Integer, Set<Integer>> marks) {
        Set<Integer> compare = marks.get(parent);
        Set<Integer> check = marks.get(cur);
        for (int key : check) {
            if (key == parent) {
                continue;
            }
            if (!compare.contains(key)) {
                return false;
            }
        }
        return true;
    }
}
