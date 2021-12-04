package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/1.
 * description : 大礼包
 *
 * 在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。
 * 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
 * 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。
 *
 * 示例 1：
 * 输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
 * 输出：14
 * 解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
 * 大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
 * 大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
 * 需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。
 *
 * 示例 2：
 * 输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * 输出：11
 * 解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
 * 可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
 * 需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
 * 不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。
 *  
 * 提示：
 * n == price.length
 * n == needs.length
 * 1 <= n <= 6
 * 0 <= price[i] <= 10
 * 0 <= needs[i] <= 10
 * 1 <= special.length <= 100
 * special[i].length == n + 1
 * 0 <= special[i][j] <= 50
 */
public class BigGiftPack {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //1，检查是否有礼包没有优惠
        List<List<Integer>> newSpecial = new ArrayList<>();
        for (int i = 0; i < special.size(); i++) {
            List<Integer> data = special.get(i);
            int sum = 0;
            for (int j = 0; j < data.size() - 1; j++) {
                sum += (data.get(j) * price.get(j));
            }
            if (sum > data.get(data.size() - 1)) {
                //有优惠
                newSpecial.add(data);
            }
        }
        //2，深度遍历dfs
        return dfs(price, newSpecial, 0, needs);
    }


    private int dfs(List<Integer> price, List<List<Integer>> special, int index, List<Integer> needs) {
        if (special.size() == index) {
            //礼包都用完了,此时价格固定了
            int sum = 0;
            for (int i = 0; i < needs.size(); i++) {
                sum += (needs.get(i) * price.get(i));
            }
            return sum;
        }
        //拿最后一个礼包
        List<Integer> data = special.get(index);
        //最大能够买data礼包的数量
        int count = 100;
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i) > 0) {
                count = Math.min(count, needs.get(i) / data.get(i));
                if (count == 0) {
                    break;
                }
            }
        }
        //所有的情况是：data礼包可以购买0~count次。默认比较的是不购买礼包
        int result = dfs(price, special, index + 1, needs);
        for (int i = 1; i <= count; i++) {
            //购买i+1个礼包
            List<Integer> newNeeds = new ArrayList<>();
            for (int j = 0; j < needs.size(); j++) {
                newNeeds.add(needs.get(j) - i * data.get(j));
            }
            int tem = dfs(price, special, index + 1, newNeeds) + i * data.get(data.size() - 1);
            if (tem < result) {
                result = tem;
            }
        }
        return result;
    }
}
