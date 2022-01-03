package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2021/12/14.
 * description : 课程表IV
 *
 * 你总共需要上n门课，课程编号依次为0到n-1 。
 * 有的课会有直接的先修课程，比如如果想上课程 0 ，你必须先上课程 1 ，那么会以 [1,0] 数对的形式给出先修课程数对。
 * 给你课程总数 n 和一个直接先修课程数对列表 prerequisite 和一个查询对列表 queries 。
 * 对于每个查询对 queries[i] ，请判断 queries[i][0] 是否是 queries[i][1] 的先修课程。
 * 请返回一个布尔值列表，列表中每个元素依次分别对应 queries 每个查询对的判断结果。
 * 注意：如果课程a是课程b的先修课程且课程b是课程c的先修课程，那么课程a也是课程c的先修课程。
 *
 * 示例 1：
 * 输入：n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 *
 * 示例 2：
 * 输入：n = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 *
 * 示例 3：
 * 输入：n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 *
 * 示例 4：
 * 输入：n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
 * 输出：[false,true]
 *
 * 示例 5：
 * 输入：n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
 * 输出：[true,false,true,false]
 *  
 * 提示：
 * 2 <= n <= 100
 * 0 <= prerequisite.length <= (n * (n - 1) / 2)
 * 0 <= prerequisite[i][0], prerequisite[i][1] < n
 * prerequisite[i][0] != prerequisite[i][1]
 * 先修课程图中没有环。
 * 先修课程图中没有重复的边。
 * 1 <= queries.length <= 10^4
 * queries[i][0] != queries[i][1]
 */
public class CourseSchedule4 {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int length = prerequisites.length;
        int n = queries.length;
        List<Boolean> result = new ArrayList<>(n);
        //1，处理没有先修课程对的特殊场景
        if (length == 0) {
            //没有先修课程对时,课程相互独立
            for (int i = 0; i < n; i++) {
                result.add(false);
            }
            return result;
        }
        //2,将课程对信息构建图
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < length; i++) {
            edges.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        //3,暴力循环遍历
        for (int i = 0; i < n; i++) {
            boolean isFind = false;
            int compare = queries[i][1];
            List<Integer> datas = new ArrayList<>(edges.get(queries[i][0]));
            //用于记录已经被搜索的顶点,可以用于去重
            Map<Integer, Integer> marks = new HashMap<>();
            while (datas.size() > 0 && !isFind) {
                List<Integer> next = new ArrayList<>();
                int size = datas.size();
                for (int j = 0; j < size; j++) {
                    if (marks.get(datas.get(j)) == null) {
                        marks.put(datas.get(j), 1);
                        if (datas.get(j) == compare) {
                            isFind = true;
                            break;
                        } else {
                            next.addAll(edges.get(datas.get(j)));
                        }
                    }
                }
                datas.clear();
                datas.addAll(next);
                next.clear();
            }
            result.add(isFind);
        }
        return result;
    }

    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        int length = prerequisites.length;
        int n = queries.length;
        List<Boolean> result = new ArrayList<>(n);
        //1，处理没有先修课程对的特殊场景
        if (length == 0) {
            //没有先修课程对时,课程相互独立
            for (int i = 0; i < n; i++) {
                result.add(false);
            }
            return result;
        }
        //2,将课程对信息构建图
        List<HashSet<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new HashSet<>());
        }
        for (int i = 0; i < length; i++) {
            edges.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        //3，统计以当前课程为先修课程的课程
        for (int i = 0; i < numCourses; i++) {
            HashSet<Integer> afterCourses = edges.get(i);
            List<Integer> datas = new ArrayList<>(afterCourses);
            List<Integer> next = new ArrayList<>();
            while (!datas.isEmpty()) {
                int size = datas.size();
                for (int j = 0; j < size; j++) {
                    List<Integer> tem = new ArrayList<>(edges.get(datas.get(j)));
                    int temSize = tem.size();
                    for (int k = 0; k < temSize; k++) {
                        if (!afterCourses.contains(tem.get(k))) {
                            afterCourses.add(tem.get(k));
                            next.add(tem.get(k));
                        }
                    }
                }
                datas.clear();
                datas.addAll(next);
                next.clear();
            }
        }
        //3,遍历判断
        for (int i = 0; i < n; i++) {
            int compare = queries[i][1];
            HashSet<Integer> datas = edges.get(queries[i][0]);
            result.add(datas.contains(compare));
        }
        return result;
    }

    public List<Boolean> checkIfPrerequisite3(int numCourses, int[][] prerequisites, int[][] queries) {
        int length = prerequisites.length;
        int n = queries.length;
        List<Boolean> result = new ArrayList<>(n);
        //1，处理没有先修课程对的特殊场景
        if (length == 0) {
            //没有先修课程对时,课程相互独立
            for (int i = 0; i < n; i++) {
                result.add(false);
            }
            return result;
        }
        //2，使用二维数组统计课程关系信息
        boolean[][] marks = new boolean[numCourses][numCourses];
        for (int i = 0; i < length; i++) {
            //a先修课程,b后修课程
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            marks[a][b] = true;
            //1,循环遍历已有关系进行传递
            for (int j = 0; j < numCourses; j++) {
                if (marks[j][a]) {
                    marks[j][b] = true;
                }
                if (marks[b][j]) {
                    marks[a][j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            result.add(marks[queries[i][0]][queries[i][1]]);
        }
        return result;
    }

}
