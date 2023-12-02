package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2023/11/17
 * @author chenglong
 * description : 最大和查询
 *
 * 给你两个长度为n、下标从0开始的整数数组nums1和nums2，另给你一个下标从1开始的二维数组queries，其中queries[i]=[xi, yi]。
 * 对于第i个查询，在所有满足nums1[j]>=xi且nums2[j]>=yi的下标j(0<=j<n)中，找出nums1[j]+nums2[j]的最大值，如果不存在满足条件的j则返回-1。
 * 返回数组answer，其中answer[i]是第i个查询的答案。
 *
 * 示例 1：
 * 输入：nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
 * 输出：[6,10,7]
 * 解释：
 * 对于第 1 个查询：xi = 4 且 yi = 1 ，可以选择下标 j = 0 ，此时 nums1[j] >= 4 且 nums2[j] >= 1 。nums1[j] + nums2[j] 等于 6 ，可以证明 6 是可以获得的最大值。
 * 对于第 2 个查询：xi = 1 且 yi = 3 ，可以选择下标 j = 2 ，此时 nums1[j] >= 1 且 nums2[j] >= 3 。nums1[j] + nums2[j] 等于 10 ，可以证明 10 是可以获得的最大值。
 * 对于第 3 个查询：xi = 2 且 yi = 5 ，可以选择下标 j = 3 ，此时 nums1[j] >= 2 且 nums2[j] >= 5 。nums1[j] + nums2[j] 等于 7 ，可以证明 7 是可以获得的最大值。
 * 因此，我们返回 [6,10,7] 。
 *
 * 示例 2：
 * 输入：nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
 * 输出：[9,9,9]
 * 解释：对于这个示例，我们可以选择下标 j = 2 ，该下标可以满足每个查询的限制。
 *
 * 示例 3：
 * 输入：nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
 * 输出：[-1]
 * 解释：示例中的查询 xi = 3 且 yi = 3 。对于每个下标 j ，都只满足 nums1[j] < xi 或者 nums2[j] < yi 。因此，不存在答案。
 *
 * 提示：
 * nums1.length == nums2.length
 * n == nums1.length
 * 1 <= n <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^9
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * xi == queries[i][1]
 * yi == queries[i][2]
 * 1 <= xi, yi <= 10^9
 */
public class MaximumSumQueries {

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        //1，合并数据源，并降序处理nums1
        int n = nums1.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = nums1[i];
            nums[i][1] = nums2[i];
        }
        //将nums1进行降序处理
        Arrays.sort(nums, (o1, o2) -> o2[0] - o1[0]);
        //2，包装查询数据，添加index并降序处理x
        int[][] dates = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            dates[i][0] = i;
            dates[i][1] = queries[i][0];
            dates[i][2] = queries[i][1];
        }
        Arrays.sort(dates, (o1, o2) -> o2[1] - o1[1]);
        //3，依次查询满足条件的最大值
        int[] answers = new int[queries.length];
        List<int[]> marks = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < queries.length; i++) {
            int index = dates[i][0];
            int x = dates[i][1];
            int y = dates[i][2];
            while (j < n && nums[j][0] >= x) {
                while (marks.size() > 0 && marks.get(marks.size() - 1)[1] <= nums[j][0] + nums[j][1]) {
                    marks.remove(marks.size() - 1);
                }
                if (marks.isEmpty() || marks.get(marks.size() - 1)[0] < nums[j][1]) {
                    marks.add(new int[]{nums[j][1], nums[j][0] + nums[j][1]});
                }
                j++;
            }
            /**
             * 此时marks中marks[i][0]对应y升序排列，marks[i][1]对应nums1[i]+nums2[i]求和的降序排列
             * 从数据找到刚好大于y的marks[i][0]，此时marks[i][1]对应最大值
             */
            int findIndex = findIndex(y, marks);
            if (findIndex == -1) {
                answers[index] = -1;
            } else {
                answers[index] = marks.get(findIndex)[1];
            }
        }
        return answers;
    }

    private int findIndex(int target, List<int[]> dates) {
        if (dates == null || dates.isEmpty()) {
            return -1;
        }
        if (dates.size() == 1) {
            if (dates.get(0)[0] >= target) {
                return 0;
            }
            return -1;
        }
        int start = 0;
        int end = dates.size() - 1;
        if (dates.get(start)[0] >= target) {
            return 0;
        }
        if (dates.get(end)[0] < target) {
            return -1;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (dates.get(middle)[0] < target) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return start;
    }
}
