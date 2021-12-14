package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by chl on 2021/12/14.
 * description : 课程表II
 *
 * 现在你总共有numCourses门课需要选，记为0到numCourses - 1。给你一个数组 prerequisites ，
 * prerequisites[i] = [ai, bi] ，表示在选修课程ai前必须先选修bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 *
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *  
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
public class CourseSchedule2 {

    private List<List<Integer>> edges;
    private int[] marks;

    //使用广度优先遍历bfs
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 1) {
            return new int[]{0};
        }
        //1,构建图的数据，顶点之间相互记录
        marks = new int[numCourses];
        edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        int length = prerequisites.length;
        for (int i = 0; i < length; i++) {
            //记录以prerequisites[i][1]为前置课程的课程
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
            //记录学习prerequisites[i][0]课程的前置课程数量
            marks[prerequisites[i][0]]++;
        }
        //2，遍历顶点获取顺序
        int count = 0;
        int[] result = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (marks[i] == 0) {
                result[count] = i;
                count++;
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer courseId = queue.poll();
            //courseId课程学习完成后,检查以它为前置课程的课程
            List<Integer> datas = edges.get(courseId);
            int size = datas.size();
            for (int i = 0; i < size; i++) {
                if (marks[datas.get(i)] == 0) {
                    continue;
                }
                marks[datas.get(i)]--;
                if (marks[datas.get(i)] == 0) {
                    result[count] = datas.get(i);
                    count++;
                    queue.offer(datas.get(i));
                }
            }
        }
        return count == numCourses ? result : new int[]{};
    }
}
