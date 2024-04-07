package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2024/4/7
 *
 * @author chenglong
 * description : 王位继承顺序
 * <p>
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
 * 这个王国有一个明确规定的王位继承顺序，第一继承人总是国王自己。我们定义递归函数Successor(x,curOrder)，给定一个人x和当前的继承顺序，该函数返回x的下一继承人。
 * Successor(x, curOrder):
 * 如果x没有孩子或者所有x的孩子都在curOrder中：
 * 如果x是国王，那么返回null
 * 否则，返回Successor(x的父亲,curOrder)
 * 否则，返回x不在curOrder中最年长的孩子
 * 比方说，假设王国由国王，他的孩子Alice和Bob（Alice比Bob年长）和Alice的孩子Jack组成。
 * 一开始，curOrder为["king"].
 * 调用 Successor(king, curOrder) ，返回 Alice ，所以我们将 Alice 放入 curOrder 中，得到 ["king", "Alice"] 。
 * 调用 Successor(Alice, curOrder) ，返回 Jack ，所以我们将 Jack 放入 curOrder 中，得到 ["king", "Alice", "Jack"] 。
 * 调用 Successor(Jack, curOrder) ，返回 Bob ，所以我们将 Bob 放入 curOrder 中，得到 ["king", "Alice", "Jack", "Bob"] 。
 * 调用 Successor(Bob, curOrder) ，返回 null 。最终得到继承顺序为 ["king", "Alice", "Jack", "Bob"] 。
 * 通过以上的函数，我们总是能得到一个唯一的继承顺序。
 * 请你实现ThroneInheritance类：
 * ThroneInheritance(string kingName)初始化一个ThroneInheritance类的对象。国王的名字作为构造函数的参数传入。
 * void birth(string parentName, string childName)表示parentName新拥有了一个名为childName的孩子。
 * void death(string name)表示名为name的人死亡。一个人的死亡不会影响Successor函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
 * string[] getInheritanceOrder()返回除去死亡人员的当前继承顺序列表。
 * <p>
 * 示例：
 * 输入：
 * ["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
 * [["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
 * 输出：
 * [null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]
 * 解释：
 * ThroneInheritance t= new ThroneInheritance("king"); // 继承顺序：king
 * t.birth("king", "andy"); // 继承顺序：king > andy
 * t.birth("king", "bob"); // 继承顺序：king > andy > bob
 * t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
 * t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
 * t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
 * t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
 * t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
 * t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
 * t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]
 * <p>
 * 提示：
 * 1 <= kingName.length, parentName.length, childName.length, name.length <= 15
 * kingName，parentName， childName 和 name 仅包含小写英文字母。
 * 所有的参数 childName 和 kingName 互不相同。
 * 所有 death 函数中的死亡名字 name 要么是国王，要么是已经出生了的人员名字。
 * 每次调用birth(parentName, childName) 时，测试用例都保证 parentName 对应的人员是活着的。
 * 最多调用10^5次birth和death。
 * 最多调用10次getInheritanceOrder。
 */
public class ThroneInheritance {

    private Node root;
    private final Map<String, Node> marks = new HashMap();

    public ThroneInheritance(String kingName) {
        root = new Node(kingName);
        marks.clear();
        marks.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        Node node = marks.get(parentName);
        if (node == null) {
            return;
        }
        Node child = new Node(childName);
        marks.put(childName, child);
        node.childs.add(child);
    }

    public void death(String name) {
        Node node = marks.get(name);
        if (node == null) {
            return;
        }
        node.isDeath = true;
    }

    public List<String> getInheritanceOrder() {
        List<String> result = new ArrayList<>();
        dfs(root,result);
        return result;
    }

    private void dfs(Node root, List<String> result) {
        if (!root.isDeath) {
            result.add(root.name);
        }
        for (int i = 0; i < root.childs.size(); i++) {
            dfs(root.childs.get(i), result);
        }
    }

    public static class Node {

        private String name;
        private boolean isDeath = false;
        private List<Node> childs = new ArrayList<>();

        public Node(String name) {
            this.name = name;
        }
    }
}
