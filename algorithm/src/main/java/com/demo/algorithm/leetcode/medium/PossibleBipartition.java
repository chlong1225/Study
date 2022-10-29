package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2022/10/17
 * @author chenglong
 * description : 可能的二分法
 *
 * 给定一组n人（编号为1, 2, ..., n），我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数n和数组dislikes，其中dislikes[i] = [ai, bi]，表示不允许将编号为ai和bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 *
 * 示例 2：
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 *
 * 示例 3：
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 *
 * 提示：
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 10^4
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai< bi
 * dislikes中每一组都不同
 */
public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> dates = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            dates.add(new ArrayList<>());
        }
        int length = dislikes.length;
        for (int i = 0; i < length; i++) {
            int a = dislikes[i][0];
            int b = dislikes[i][1];
            dates.get(a).add(b);
            dates.get(b).add(a);
        }
        boolean[] group1 = new boolean[n + 1];
        boolean[] group2 = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (group1[i] || group2[i] || dates.get(i).size() == 0) {
                //i已经存在被分组或i没有任何限制
                continue;
            }
            List<Integer> curs = new ArrayList<>();
            curs.add(i);
            Set<Integer> next = new HashSet<>();
            boolean isFirst = true;
            while (curs.size() > 0) {
                if (isFirst) {
                    //cur中的数放在group1中
                    for (int j = 0; j < curs.size(); j++) {
                        if (group2[curs.get(j)]) {
                            return false;
                        }
                        group1[curs.get(j)] = true;
                        List<Integer> items = dates.get(curs.get(j));
                        if (items.size() > 0) {
                            for (int k = 0; k < items.size(); k++) {
                                if (group1[items.get(k)] || group2[items.get(k)]) {
                                    continue;
                                }
                                next.add(items.get(k));
                            }
                        }
                    }
                } else {
                    //cur中的数放在group2中
                    for (int j = 0; j < curs.size(); j++) {
                        if (group1[curs.get(j)]) {
                            return false;
                        }
                        group2[curs.get(j)] = true;
                        List<Integer> items = dates.get(curs.get(j));
                        if (items.size() > 0) {
                            for (int k = 0; k < items.size(); k++) {
                                if (group1[items.get(k)] || group2[items.get(k)]) {
                                    continue;
                                }
                                next.add(items.get(k));
                            }
                        }
                    }
                }
                curs.clear();
                curs.addAll(next);
                next.clear();
                isFirst = !isFirst;
            }
        }
        return true;
    }
}
