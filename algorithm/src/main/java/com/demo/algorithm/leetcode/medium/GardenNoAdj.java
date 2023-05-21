package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/4/17
 * @author chenglong
 * description : 不邻接植花
 *
 * 有n个花园，按从1到n标记。另有数组paths，其中paths[i]=[xi, yi]描述了花园xi到花园yi的双向路径。在每个花园中，你打算种下四种花之一。
 * 另外所有花园最多有3条路径可以进入或离开.
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * 以数组形式返回任一可行的方案作为答案answer，其中answer[i]为在第(i+1)个花园中种植的花的种类。花的种类用1、2、3、4表示。保证存在答案。
 *
 * 示例 1：
 * 输入：n = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 解释：
 * 花园 1 和 2 花的种类不同。
 * 花园 2 和 3 花的种类不同。
 * 花园 3 和 1 花的种类不同。
 * 因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
 *
 * 示例 2：
 * 输入：n = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 *
 * 示例 3：
 * 输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= n <= 10^4
 * 0 <= paths.length <= 2 * 10^4
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 每个花园最多有3条路径可以进入或离开
 */
public class GardenNoAdj {

    private int[] result;
    private List<List<Integer>> marks;
    private boolean isFind = false;

    public int[] gardenNoAdj(int n, int[][] paths) {
        result = new int[n];
        marks = new ArrayList<>(n + 1);
        isFind = false;
        //1，构建数据
        for (int i = 0; i <= n; i++) {
            marks.add(new ArrayList<>());
        }
        for (int i = 0; i < paths.length; i++) {
            marks.get(paths[i][0]).add(paths[i][1]);
            marks.get(paths[i][1]).add(paths[i][0]);
        }
        //2，深度遍历dfs
        dfs(1);
        return result;
    }

    private void dfs(int start) {
        if (start == marks.size()) {
            isFind = true;
            return;
        }
        //检查相邻已选花的种类
        boolean[] dates = new boolean[5];
        //获取相邻花园
        List<Integer> nums = marks.get(start);
        if (nums.size() > 0) {
            for (int i = 0; i < nums.size(); i++) {
                int index = nums.get(i);
                if (result[index - 1] != 0) {
                    //当前花园已经种花了
                    dates[result[index - 1]] = true;
                }
            }
        }
        for (int i = 1; i <= 4; i++) {
            if (dates[i]) {
                continue;
            }
            if (isFind) {
                return;
            }
            result[start - 1] = i;
            dfs(start + 1);
            if (isFind) {
                return;
            }
            result[start - 1] = 0;
        }
    }
}
