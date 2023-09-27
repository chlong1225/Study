package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * create on 2023/9/27
 * @author chenglong
 * description : 餐厅过滤器
 *
 * 给你一个餐馆信息数组restaurants，其中restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。你必须使用以下三个过滤器来过滤这些餐馆信息。
 * 其中素食者友好过滤器veganFriendly的值可以为true或者false，如果为true就意味着你应该只包括veganFriendlyi为true的餐馆，为false则意味着可以包括任何餐馆。
 * 此外，我们还有最大价格maxPrice和最大距离maxDistance两个过滤器，它们分别考虑餐厅的价格因素和距离因素的最大值。
 * 过滤后返回餐馆的id，按照rating从高到低排序。如果rating相同，那么按id从高到低排序。简单起见，veganFriendlyi和veganFriendly为true时取值为1，为false时，取值为0。
 *
 * 示例 1：
 * 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
 * 输出：[3,1,5]
 * 解释：
 * 这些餐馆为：
 * 餐馆 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
 * 餐馆 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
 * 餐馆 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
 * 餐馆 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
 * 餐馆 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1]
 * 在按照 veganFriendly = 1, maxPrice = 50 和 maxDistance = 10 进行过滤后，我们得到了餐馆 3, 餐馆 1 和 餐馆 5（按评分从高到低排序）。
 *
 *  示例 2：
 * 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
 * 输出：[4,3,2,1,5]
 * 解释：餐馆与示例 1 相同，但在 veganFriendly = 0 的过滤条件下，应该考虑所有餐馆。
 *
 *  示例 3：
 * 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
 * 输出：[4,5]
 *
 * 提示：
 * 1 <= restaurants.length <= 10^4
 * restaurants[i].length == 5
 * 1 <= idi, ratingi, pricei, distancei <= 10^5
 * 1 <= maxPrice, maxDistance <= 10^5
 * veganFriendlyi 和 veganFriendly 的值为 0 或 1 。
 * 所有 idi 各不相同。
 */
public class FilterRestaurants {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> dates = new ArrayList<>();
        //1，根据条件过滤数据
        for (int i = 0; i < restaurants.length; i++) {
            int[] cur = restaurants[i];
            if (cur[3] > maxPrice) {
                continue;
            }
            if (cur[4] > maxDistance) {
                continue;
            }
            if (veganFriendly == 1) {
                if (cur[2] == 1) {
                    dates.add(cur);
                }
            } else {
                dates.add(cur);
            }
        }
        //2，过滤后的数据进行排序
        Collections.sort(dates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
        //3，提取id
        List<Integer> result = new ArrayList<>(dates.size());
        for (int i = 0; i < dates.size(); i++) {
            result.add(dates.get(i)[0]);
        }
        return result;
    }
}
