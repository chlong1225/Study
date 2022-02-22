package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/1/28.
 * description : 游戏中弱角色的数量
 *
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击和防御。
 * 给你一个二维整数数组properties，其中properties[i]=[attacki, defensei]表示游戏中第i个角色的属性。
 * 如果存在一个其他角色的攻击和防御等级都严格高于该角色的攻击和防御等级，则认为该角色为弱角色。
 * 更正式地，如果认为角色i弱于存在的另一个角色j，那么attackj>attacki且defensej>defensei 。
 * 返回弱角色的数量。

 * 示例 1：
 * 输入：properties = [[5,5],[6,3],[3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 *
 * 示例 2：
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 *
 * 示例 3：
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 *
 * 提示：
 * 2 <= properties.length <= 10^5
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 10^5
 */
public class WeakRole {

    public int numberOfWeakCharacters(int[][] properties) {
        int length = properties.length;
        //记录所有出现过的攻击值
        List<Integer> attacks = new ArrayList<>();
        //记录相同攻击值对应的最大防御值
        Map<Integer, Integer> datas = new HashMap<>();
        //1，遍历记录数据
        for (int i = 0; i < length; i++) {
            int attack = properties[i][0];
            int defense = properties[i][1];
            if (datas.get(attack) == null) {
                attacks.add(attack);
                datas.put(attack, defense);
            } else {
                if (defense > datas.get(attack)) {
                    datas.put(attack, defense);
                }
            }
        }
        if (attacks.size() == 1) {
            //特殊场景：所有的角色攻击值一样
            return 0;
        }
        //2，对记录的攻击值数据进行排序
        Collections.sort(attacks);
        int n = attacks.size();
        int tem = 0;
        for (int i = n - 2; i >= 0; i--) {
            int attack = attacks.get(i);
            //比attack大的最大防御值
            int maxDefense = Math.max(tem, datas.get(attacks.get(i + 1)));
            //赋值前先用tem临时变量记录
            tem = Math.max(datas.get(attack), maxDefense);
            datas.put(attack, maxDefense);
        }
        datas.put(attacks.get(n - 1), 0);
        int count = 0;
        //3，遍历数据判断是否为弱角色
        for (int i = 0; i < length; i++) {
            int attack = properties[i][0];
            int defense = properties[i][1];
            if (datas.get(attack) > defense) {
                count++;
            }
        }
        return count;
    }

    //将map统计改为桶排序
    public int numberOfWeakCharacters2(int[][] properties) {
        int maxAttacks = properties[0][0];
        int length = properties.length;
        //1，遍历获取最大攻击值，便于缩小桶的范围，不然可以设置最大桶10^5
        for (int i = 1; i < length; i++) {
            if (properties[i][0] > maxAttacks) {
                maxAttacks = properties[i][0];
            }
        }
        //2，创建桶，统计当前攻击值对应的最大防御
        int[] maxDefs = new int[maxAttacks + 1];
        for (int i = 0; i < length; i++) {
            if (maxDefs[properties[i][0]] < properties[i][1]) {
                maxDefs[properties[i][0]] = properties[i][1];
            }
        }
        //3，倒着遍历攻击值，将当前攻击值的最大改为大于当前攻击值的最大防御值
        int pre = 0;
        for (int i = maxAttacks; i >= 0; i--) {
            int curMaxDef = maxDefs[i];
            maxDefs[i] = pre;
            pre = Math.max(curMaxDef, pre);
        }
        int count = 0;
        //4，判断当前是否为弱角色
        for (int i = 0; i < length; i++) {
            if (maxDefs[properties[i][0]] > properties[i][1]) {
                count++;
            }
        }
        return count;
    }

}
