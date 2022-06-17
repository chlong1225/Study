package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/6/17
 * @author chenglong
 * description : 复写零
 *
 * 给你一个长度固定的整数数组arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 注意：请不要在超过该数组长度的位置写入元素。
 * 要求：请对输入的数组就地进行上述修改，不要从函数返回任何东西。
 *
 * 示例 1：
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 *
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 *
 * 提示：
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeros {

    public void duplicateZeros(int[] arr) {
        int length = arr.length;
        if (length == 1) {
            return;
        }
        //1，查找最后一个有效数字的位置，之后的都会被覆盖
        int count = 0;
        int end = 0;
        boolean isDouble = false;
        for (int i = 0; i < length; i++) {
            if (arr[i] == 0) {
                count += 2;
                if (count >= length) {
                    end = i;
                    isDouble = (count == length);
                    break;
                }
            } else {
                count++;
                if (count == length) {
                    end = i;
                    break;
                }
            }
        }
        //2，处理特殊场景
        if (end == length - 1) {
            //此时前面length-1个非0
            return;
        }
        //3，倒序遍历赋值
        int index = length - 1;
        arr[index--] = arr[end];
        if (isDouble) {
            arr[index--] = arr[end];
        }
        for (int i = end - 1; i >= 0; i--) {
            arr[index--] = arr[i];
            if (arr[i] == 0) {
                arr[index--] = arr[i];
            }
        }
    }
}
