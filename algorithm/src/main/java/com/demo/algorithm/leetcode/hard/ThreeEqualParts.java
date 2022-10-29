package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/10/6
 * @author chenglong
 * description : 三等分
 *
 * 给定一个由0和1组成的数组arr，将数组分成3个非空的部分，使得所有这些部分表示相同的二进制值。
 * 如果可以做到，请返回任何[i,j]，其中i+1<j，这样一来：
 * arr[0], arr[1], ..., arr[i]为第一部分；
 * arr[i + 1], arr[i + 2], ..., arr[j - 1]为第二部分；
 * arr[j], arr[j + 1], ..., arr[arr.length - 1]为第三部分。
 * 这三个部分所表示的二进制值相等。
 * 如果无法做到，就返回[-1, -1]。
 *
 * 注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，[1,1,0]表示十进制中的6，而不会是3。此外，前导零也是被允许的，所以[0,1,1]和[1,1]表示相同的值。
 *
 * 示例 1：
 * 输入：arr = [1,0,1,0,1]
 * 输出：[0,3]
 *
 * 示例 2：
 * 输入：arr = [1,1,0,1,1]
 * 输出：[-1,-1]
 *
 * 示例 3:
 * 输入：arr = [1,1,0,0,1]
 * 输出：[0,2]
 *
 * 提示：
 * 3 <= arr.length <= 3 * 10^4
 * arr[i]是0或1
 */
public class ThreeEqualParts {

    public int[] threeEqualParts(int[] arr) {
        int length = arr.length;
        //1，统计出现1的数量
        int count = 0;
        for (int i = 0; i < length; i++) {
            count += arr[i];
        }
        //1的个数必须是3的倍数
        if (count % 3 != 0) {
            return new int[]{-1, -1};
        }
        //特殊场景
        if (count == 0) {
            return new int[]{0, 2};
        }
        int[] result = new int[2];
        //每段包含1的数量
        int target = count / 3;
        //记录每段1的起始位置，前导0是无效的
        count = 0;
        int firstIndex = -1;
        int secondIndex = -1;
        int threeIndex = -1;
        for (int i = 0; i < length; i++) {
            if (arr[i] == 1) {
                count++;
                if (count == 1) {
                    firstIndex = i;
                } else if (count == target + 1) {
                    secondIndex = i;
                } else if (count == 2 * target + 1) {
                    threeIndex = i;
                    break;
                }
            }
        }
        //三段最小区间：[threeIndex,length-1],[firstIndex,secondIndex-1],[secondIndex,threedIndex-1]
        int minLength = length - threeIndex;
        if (threeIndex - secondIndex < minLength || secondIndex - firstIndex < minLength) {
            //此时不可能等分
            return new int[]{-1, -1};
        }
        for (int i = threeIndex; i < length; i++) {
            int offset = i - threeIndex;
            if (arr[i] != arr[firstIndex + offset] || arr[i] != arr[secondIndex + offset]) {
                return new int[]{-1, -1};
            }
        }
        //此时区间[threeIndex，length），[secondIndex,secondIndex+minLength),[firstIndex,firstIndex+minLength)相同
        result[0] = firstIndex + minLength - 1;
        result[1] = secondIndex + minLength;
        return result;
    }
}
