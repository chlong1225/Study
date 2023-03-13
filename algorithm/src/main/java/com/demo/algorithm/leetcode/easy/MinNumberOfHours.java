package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/3/13
 * @author chenglong
 * description : 赢得比赛需要的最少训练时长
 *
 * 你正在参加一场比赛，给你两个正整数initialEnergy和initialExperience分别表示你的初始精力和初始经验。
 * 另给你两个下标从0开始的整数数组energy和experience，长度均为n。
 * 你将会依次对上n个对手。第i个对手的精力和经验分别用energy[i]和experience[i]表示。当你对上对手时，需要在经验和精力上都严格超过对手才能击败他们，然后在可能的情况下继续对上下一个对手。
 * 击败第i个对手会使你的经验增加experience[i]，但会将你的精力减少energy[i]。
 * 在开始比赛前，你可以训练几个小时。每训练一个小时，你可以选择将增加经验增加1或者将精力增加1。
 * 返回击败全部n个对手需要训练的最少小时数目。
 *
 * 示例 1：
 * 输入：initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
 * 输出：8
 * 解释：在 6 小时训练后，你可以将精力提高到 11 ，并且再训练 2 个小时将经验提高到 5 。
 * 按以下顺序与对手比赛：
 * - 你的精力与经验都超过第 0 个对手，所以获胜。
 *   精力变为：11 - 1 = 10 ，经验变为：5 + 2 = 7 。
 * - 你的精力与经验都超过第 1 个对手，所以获胜。
 *   精力变为：10 - 4 = 6 ，经验变为：7 + 6 = 13 。
 * - 你的精力与经验都超过第 2 个对手，所以获胜。
 *   精力变为：6 - 3 = 3 ，经验变为：13 + 3 = 16 。
 * - 你的精力与经验都超过第 3 个对手，所以获胜。
 *   精力变为：3 - 2 = 1 ，经验变为：16 + 1 = 17 。
 * 在比赛前进行了 8 小时训练，所以返回 8 。
 * 可以证明不存在更小的答案。
 *
 * 示例 2：
 * 输入：initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
 * 输出：0
 * 解释：你不需要额外的精力和经验就可以赢得比赛，所以返回 0 。
 *
 * 提示：
 * n == energy.length == experience.length
 * 1 <= n <= 100
 * 1 <= initialEnergy, initialExperience, energy[i], experience[i] <= 100
 */
public class MinNumberOfHours {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int length = energy.length;
        //倒推模拟获取最小初始精力和初始经验
        int minEnergy = 0;
        int minExperience = 0;
        for (int i = length - 1; i >= 0; i--) {
            //当前获胜最小的精力与经验
            int curMinEnergy = energy[i] + 1;
            int curMinExperience = experience[i] + 1;
            minEnergy += energy[i];
            minExperience -= experience[i];
            if (minEnergy < curMinEnergy) {
                minEnergy = curMinEnergy;
            }
            if (minExperience < curMinExperience) {
                minExperience = curMinExperience;
            }
        }
        int count = 0;
        if (initialEnergy < minEnergy) {
            count += (minEnergy - initialEnergy);
        }
        if (initialExperience < minExperience) {
            count += (minExperience - initialExperience);
        }
        return count;
    }
}
