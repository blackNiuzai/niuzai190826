package TreeProblem;

import TreeProblem.TreeNode;

/**
 * 二叉树的三种遍历方式
 *1、先序遍历： 先访问根节点， 然后访问左孩子， 最后访问右孩子
 *2、中序遍历： 先访问左孩子， 然后访问根节点， 最后访问右孩子
 *3、后序遍历： 先访问左孩子， 然后访问右孩子， 最后访问根节点
 */
public class ReadTreeNode {

    // 先序遍历
    public static void preOrderRead(TreeNode node){
        if(node==null) return;

        System.out.println(node.val);
        preOrderRead(node.left);
        preOrderRead(node.right);
    }


    // 中序遍历
    public static void inOrderRead(TreeNode node){
        if(node==null) return;

        inOrderRead(node.left);
        System.out.println(node.val);
        inOrderRead(node.right);
    }


    // 后序遍历
    public static void postOrderRead(TreeNode node){
        if(node==null) return;

        postOrderRead(node.left);
        preOrderRead(node.right);
        System.out.println(node.val);
    }


}
