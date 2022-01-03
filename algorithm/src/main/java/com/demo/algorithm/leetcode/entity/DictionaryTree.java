package com.demo.algorithm.leetcode.entity;

/**
 * Created by chl on 2022/1/1.
 * description : 字典树,支持单一的小写字母或大写字母
 */
public class DictionaryTree {


    public DictionaryTree[] childrens;
    public boolean isEnd;

    //默认长度26,只支持小写或大写字母。其它的需要自定义长度进行扩展
    public DictionaryTree() {
        this(26);
    }

    public DictionaryTree(int length) {
        childrens = new DictionaryTree[length];
    }
}
