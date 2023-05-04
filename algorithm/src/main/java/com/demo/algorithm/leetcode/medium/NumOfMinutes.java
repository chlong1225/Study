package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/5/4
 * @author chenglong
 * description : 通知所有员工所需的时间
 *
 * 公司里有n名员工，每个员工的ID都是独一无二的，编号从0到n-1。公司的总负责人通过headID进行标识。
 * 在manager数组中，每个员工都有一个直属负责人，其中manager[i]是第i名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。
 * 公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
 * 第i名员工需要informTime[i]分钟来通知它的所有直属下属（也就是说在informTime[i]分钟后，他的所有直属下属都可以开始传播这一消息）。
 * 返回通知所有员工这一紧急消息所需要的分钟数 。
 *
 * 示例 1：
 * 输入：n = 1, headID = 0, manager = [-1], informTime = [0]
 * 输出：0
 * 解释：公司总负责人是该公司的唯一一名员工。
 *
 * 示例 2：
 * 输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
 * 输出：1
 * 解释：id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
 * 上图显示了公司员工的树结构。
 *
 * 提示：
 * 1 <= n <= 10^5
 * 0 <= headID < n
 * manager.length == n
 * 0 <= manager[i] < n
 * manager[headID] == -1
 * informTime.length== n
 * 0 <= informTime[i] <= 1000
 * 如果员工i没有下属，informTime[i] == 0 。
 * 题目保证所有员工都可以收到通知。
 */
public class NumOfMinutes {

    private int max = 0;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n == 1) {
            return 0;
        }
        //1，构建员工关系结构，index代表当前领导id，List<Integer>:当前领导的下属
        List<List<Integer>> dates = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (i == headID) {
                continue;
            }
            dates.get(manager[i]).add(i);
        }
        //2，深度优先遍历
        max = 0;
        dfs(headID, dates, informTime, 0);
        return max;
    }

    private void dfs(int id, List<List<Integer>> dates, int[] informTime, int total) {
        List<Integer> nexts = dates.get(id);
        if (nexts.isEmpty()) {
            if (total > max) {
                max = total;
            }
            return;
        }
        for (int i = 0; i < nexts.size(); i++) {
            dfs(nexts.get(i), dates, informTime, total + informTime[id]);
        }
    }
}
