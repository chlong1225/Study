package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/30.
 * description : Bigram 分词
 *
 * 给出第一个词first和第二个词second，考虑在某些文本text中可能以 "first second third" 形式出现的情况，
 * 其中second紧随first出现，third紧随second出现。
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 *
 * 示例 1：
 * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
 * 输出：["girl","student"]
 *
 * 示例 2：
 * 输入：text = "we will we will rock you", first = "we", second = "will"
 * 输出：["we","rock"]
 *  
 * 提示：
 * 1 <= text.length <= 1000
 * text 由小写英文字母和空格组成
 * text 中的所有单词之间都由 单个空格字符 分隔
 * 1 <= first.length, second.length <= 10
 * first 和 second 由小写英文字母组成
 */
public class BigramWord {

    public String[] findOcurrences(String text, String first, String second) {
        List<String> result = new ArrayList<>();
        int length = text.length();
        StringBuilder builder = new StringBuilder();
        int findCount = 0;
        boolean isSame = first.equals(second);
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                String tem = builder.toString();
                //1，找到的数量为0，与first比较
                if (findCount == 0) {
                    if (first.equals(tem)) {
                        findCount = 1;
                    }
                } else if (findCount == 1) {
                    //2，找到1个后，与second比较。相同找到两个。
                    if (second.equals(tem)) {
                        findCount = 2;
                    } else if (!first.equals(tem)) {
                        //3，如果找到1个后还是与first相同，状态还是找到1个，否则没有找到，重新开始
                        findCount = 0;
                    }
                } else {
                    //4，找到两个后直接添加tem，然后重置找到的状态为0。但此时tem需要跟first比较
                    result.add(tem);
                    if (first.equals(tem)) {
                        if (isSame) {
                            findCount = 2;
                        } else {
                            findCount = 1;
                        }
                    } else {
                        if (isSame) {
                            findCount = 1;
                        } else {
                            findCount = 0;
                        }
                    }
                }
                builder.delete(0, builder.length());
            } else {
                builder.append(text.charAt(i));
            }
        }
        if (findCount == 2 && builder.length() > 0) {
            result.add(builder.toString());
        }
        int size = result.size();
        String[] words = new String[size];
        for (int i = 0; i < size; i++) {
            words[i] = result.get(i);
        }
        return words;
    }
}
