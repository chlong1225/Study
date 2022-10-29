package com.demo.algorithm.leetcode.easy;

import java.util.List;

/**
 * Created by chl on 2022/10/29.
 * description : 统计匹配检索规则的物品数量
 *
 * 给你一个数组items，其中items[i]=[typei, colori, namei]，描述第i件物品的类型、颜色以及名称。
 * 另给你一条由两个字符串ruleKey和ruleValue表示的检索规则。
 * 如果第i件物品能满足下述条件之一，则认为该物品与给定的检索规则匹配 ：
 * ruleKey == "type" 且 ruleValue == typei 。
 * ruleKey == "color" 且 ruleValue == colori 。
 * ruleKey == "name" 且 ruleValue == namei 。
 * 统计并返回匹配检索规则的物品数量 。
 *
 * 示例 1：
 * 输入：items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
 * 输出：1
 * 解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
 *
 * 示例 2：
 * 输入：items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
 * 输出：2
 * 解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。
 *
 * 提示：
 * 1 <= items.length <= 10^4
 * 1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
 * ruleKey 等于 "type"、"color" 或 "name"
 * 所有字符串仅由小写字母组成
 */
public class CountMatches {

    private static final String TYPE = "type";
    private static final String COLOR = "color";
    private static final String NAME = "name";

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        int index = 0;
        if (COLOR.equals(ruleKey)) {
            index = 1;
        } else if (NAME.equals(ruleKey)) {
            index = 2;
        }
        for (int i = 0; i < items.size(); i++) {
            if (ruleValue.equals(items.get(i).get(index))) {
                count++;
            }
        }
        return count;
    }
}
