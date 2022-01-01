package com.demo.algorithm.leetcode.entity;

/**
 * Created by chl on 2022/1/1.
 * description : 字典树,支持单一的小写字母或大写字母
 */
public class DictionaryTree {

    //长度26对应小写字母或大写字母的数量
    public DictionaryTree[] childrens = new DictionaryTree[26];
    public boolean isEnd;
}
