package com.demo.algorithm.leetcode.medium;

import android.os.Build;

import com.demo.algorithm.leetcode.entity.DictionaryTree;

import java.util.List;

/**
 * create on 2022/7/7
 * @author chenglong
 * description :  单词替换
 *
 * 在英语中，我们有一个叫做词根(root)的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为继承词(successor)。
 * 例如，词根an，跟随着单词other(其他)，可以形成新的单词another(另一个)。
 * 现在，给定一个由许多词根组成的词典dictionary和一个用空格分隔单词形成的句子sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 *
 * 示例 1：
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 *
 * 示例 2：
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 *
 * 提示：
 * 1 <= dictionary.length<= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i]仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence仅由小写字母和空格组成。
 * sentence中单词的总量在范围[1, 1000]内。
 * sentence中每个单词的长度在范围[1, 1000]内。
 * sentence中单词之间由一个空格隔开。
 * sentence没有前导或尾随空格。
 */
public class ReplaceWords {

    public String replaceWords(List<String> dictionary, String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return "";
        }
        //1，构建字典树
        DictionaryTree root = new DictionaryTree();
        for (int i = 0; i < dictionary.size(); i++) {
            buildTree(root, dictionary.get(i));
        }
        //2，依次查找替换
        StringBuilder builder = new StringBuilder();
        StringBuilder word = new StringBuilder();
        int length = sentence.length();
        for (int i = 0; i < length; i++) {
            if (sentence.charAt(i) == ' ') {
                String tem = findWord(word.toString(), root);
                builder.append(tem);
                builder.append(" ");
                word.delete(0, word.length());
            } else {
                word.append(sentence.charAt(i));
            }
        }
        builder.append(findWord(word.toString(), root));
        return builder.toString();
    }

    private String findWord(String word, DictionaryTree root) {
        DictionaryTree p = root;
        StringBuilder builder = new StringBuilder();
        int length = word.length();
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                break;
            } else {
                p = p.childrens[index];
                builder.append(word.charAt(i));
                if (p.isEnd) {
                    return builder.toString();
                }
            }
        }
        return word;
    }

    private void buildTree(DictionaryTree root, String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        int length = word.length();
        DictionaryTree p = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
            if (i == length - 1) {
                p.isEnd = true;
            }
        }
    }
}
