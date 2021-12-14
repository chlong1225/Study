package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/14.
 * description : 课程表
 *
 * 你这个学期必须选修numCourses门课程，记为0到numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程ai则必须先学习课程bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *  
 * 提示：
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i]中的所有课程对互不相同
 */
public class CourseSchedule {

    List<List<Integer>> edges;
    private int[] marks;
    private boolean result = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int length = prerequisites.length;
        edges = new ArrayList<>(numCourses);
        marks = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < length; i++) {
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (marks[i] == 0) {
                dfs(i);
                if (!result) {
                    return result;
                }
            }
        }
        return result;
    }

    private void dfs(int courseId) {
        //1：代表当前正在搜索
        marks[courseId] = 1;
        List<Integer> datas = edges.get(courseId);
        int size = datas.size();
        for (int i = 0; i < size; i++) {
            if (marks[datas.get(i)] == 0) {
                dfs(datas.get(i));
                if (!result) {
                    return;
                }
            } else if (marks[datas.get(i)] == 1) {
                result = false;
                return;
            }
        }
        //2：代表当前节点已经搜索完成
        marks[courseId] = 2;
    }
}
