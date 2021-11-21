package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/21.
 * description : N叉树的最大深度
 *
 * 给定一个N叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 *  
 * 提示：
 * 树的深度不会超过 1000 。
 * 树的节点数目位于 [0, 104] 之间。
 */
public class NTree {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        List<Node> datas = new ArrayList<>();
        List<Node> next = new ArrayList<>();
        datas.add(root);
        while (datas.size() > 0) {
            depth++;
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).children != null && datas.get(i).children.size() > 0) {
                    next.addAll(datas.get(i).children);
                }
            }
            datas.clear();
            datas.addAll(next);
            next.clear();
        }
        return depth;
    }
}
