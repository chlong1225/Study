package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2022/7/18
 * @author chenglong
 * description :  隔离病毒
 *
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 * 假设世界由m x n的二维矩阵isInfected组成，isInfected[i][j] == 0表示该区域未感染病毒，而isInfected[i][j]==1表示该区域已感染病毒。可以在任意2个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一。
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 *
 * 示例 1：
 * 输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * 输出: 10
 * 解释:一共有两块被病毒感染的区域。
 * 在第一天，添加5墙隔离病毒区域的左侧。病毒传播后的状态是:
 * 第二天，在右侧添加5个墙来隔离病毒区域。此时病毒已经被完全控制住了。
 *
 * 示例 2：
 * 输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出: 4
 * 解释: 虽然只保存了一个小区域，但却有四面墙。
 * 注意，防火墙只建立在两个不同区域的共享边界上。
 *
 * 示例3:
 * 输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * 输出: 13
 * 解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
 *
 * 提示:
 * m ==isInfected.length
 * n ==isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j]is either 0 or 1
 * 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块
 */
public class ContainVirus {

    private static final int MOD = 51;

    //对应左上右下的偏移
    private final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private boolean[][] marks;
    private int m;
    private int n;

    public int containVirus(int[][] isInfected) {
        m = isInfected.length;
        n = isInfected[0].length;
        int count = 0;
        List<List<int[]>> totals = new ArrayList<>();
        int[] max = new int[2];
        int index = 0;
        while (true) {
            totals.clear();
            marks = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 0 || isInfected[i][j] == -1 || marks[i][j]) {
                        continue;
                    }
                    List<int[]> path = findPath(i, j, isInfected);
                    totals.add(path);
                    int[] tem = findCount(path, isInfected);
                    if (totals.size() == 1) {
                        max = tem;
                        index = 0;
                    } else {
                        if (tem[1] > max[1]) {
                            max = tem;
                            index = totals.size() - 1;
                        }
                    }
                }
            }
            if (totals.size() == 0) {
                break;
            }
            count += max[0];
            //最大区间安装隔离
            List<int[]> dates = totals.get(index);
            for (int i = 0; i < dates.size(); i++) {
                isInfected[dates.get(i)[0]][dates.get(i)[1]] = -1;
            }
            //其它区间扩散
            if (totals.size() == 1) {
                break;
            }
            for (int i = 0; i < totals.size(); i++) {
                if (i == index) {
                    continue;
                }
                //此时扩散
                diffusion(totals.get(i), isInfected);
            }
        }
        return count;
    }

    private void diffusion(List<int[]> date, int[][] isInfected) {
        for (int i = 0; i < date.size(); i++) {
            for (int j = 0; j < offsets.length; j++) {
                int nx = date.get(i)[0] + offsets[j][0];
                int ny = date.get(i)[1] + offsets[j][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (isInfected[nx][ny] == 0) {
                        isInfected[nx][ny] = 1;
                    }
                }
            }
        }
    }

    private int[] findCount(List<int[]> dates, int[][] isInfected) {
        int count = 0;
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < dates.size(); i++) {
            for (int j = 0; j < offsets.length; j++) {
                int nx = dates.get(i)[0] + offsets[j][0];
                int ny = dates.get(i)[1] + offsets[j][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (isInfected[nx][ny] == 0) {
                        count++;
                        nums.add(nx * MOD + ny);
                    }
                }
            }
        }
        return new int[]{count,nums.size()};
    }

    //从位置(x,y)查找连成一片的病毒
    private List<int[]> findPath(int x, int y, int[][] isInfected) {
        List<int[]> result = new ArrayList<>();
        marks[x][y] = true;
        result.add(new int[]{x, y});
        List<int[]> dates = new ArrayList<>();
        dates.add(new int[]{x, y});
        List<int[]> next = new ArrayList<>();
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                for (int j = 0; j < offsets.length; j++) {
                    int nx = dates.get(i)[0] + offsets[j][0];
                    int ny = dates.get(i)[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        //防止越界
                        if (isInfected[nx][ny] == 1 && !marks[nx][ny]) {
                            marks[nx][ny] = true;
                            next.add(new int[]{nx, ny});
                            result.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return result;
    }
}
