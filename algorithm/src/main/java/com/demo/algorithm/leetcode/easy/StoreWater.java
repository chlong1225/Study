package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2023/5/21.
 * description : 蓄水
 *
 * 给定N个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第i个水缸配备的水桶容量记作bucket[i]。小扣有以下两种操作：
 * 升级水桶：选择任意一个水桶，使其容量增加为bucket[i]+1
 * 蓄水：将全部水桶接满水，倒入各自对应的水缸
 * 每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
 * 注意：实际蓄水量达到或超过最低蓄水量，即完成蓄水要求。
 *
 * 示例 1：
 * 输入：bucket = [1,3], vat = [6,8]
 * 输出：4
 * 解释：
 * 第 1 次操作升级 bucket[0]；
 * 第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
 *
 * 示例 2：
 * 输入：bucket = [9,0,1], vat = [0,2,2]
 *
 * 输出：3
 * 解释：
 * 第 1 次操作均选择升级 bucket[1]
 * 第 2~3 次操作选择蓄水，即可完成蓄水要求。
 *
 * 提示：
 * 1 <= bucket.length == vat.length <= 100
 * 0 <= bucket[i], vat[i] <= 10^4
 */
public class StoreWater {

   public int storeWater(int[] bucket, int[] vat) {
      int count = 0;
      int n = vat.length;
      //最少蓄水次数
      int min = Integer.MAX_VALUE;
      //最大蓄水次数
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
         if (vat[i] != 0) {
            if (bucket[i] == 0) {
               count++;
               bucket[i] = 1;
               //蓄水的次数
               int tem = vat[i];
               if (max < tem) {
                  max = tem;
               }
               if (tem < min) {
                  min = tem;
               }
            } else {
               int tem = vat[i] / bucket[i];
               if (vat[i] % bucket[i] != 0) {
                  tem++;
               }
               if (tem > max) {
                  max = tem;
               }
               if (tem < min) {
                  min = tem;
               }
            }
         }
      }
      if (min == Integer.MAX_VALUE) {
         return 0;
      }
      int minCount = max;
      for (int i = 1; i < max; i++) {
         int sum = i;
         for (int j = 0; j < n; j++) {
            if (vat[j] != 0 && bucket[j] * i < vat[j]) {
               int tem = vat[j] / i - bucket[j];
               if (vat[j] % i != 0) {
                  tem++;
               }
               sum += tem;
            }
         }
         if (sum < minCount) {
            minCount = sum;
         }
      }
      return count + minCount;
   }
}
