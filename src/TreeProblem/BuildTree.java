package TreeProblem;

import java.util.HashMap;
import java.util.Map;

/**
 * 前序遍历和中序遍历恢复一棵树
 *
 * 前序遍历总是
 * [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
 * 中序遍历总是
 * [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 *
 * 这道题的关键在于没有重复元素
 * 前序遍历的第一个读到的是根节点root, 根据root数值在中序遍历中找到对应位置，其左边即该root的左子树所有值，右边即该root的右子树所有值
 *
 *
 */
public class BuildTree {

    static Map<Integer, Integer> indexMap = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = new TreeNode(4, new TreeNode(3), new TreeNode(5));
        TreeNode right1 = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        root.left = left1;
        root.right = right1;

        ReadTreeNode.preOrderRead(root);
        System.out.println("=====================");
        ReadTreeNode.inOrderRead(root);

        System.out.println("=====================");

        int[] preOrder = new int[]{10,4,3,5,7,6,9 };
        int[] inOrder = new int[]{3,4,5,10,6,7,9};
        TreeNode res = buildTree(preOrder, inOrder);
        ReadTreeNode.preOrderRead(res);

//        前序 10 4 3 5 7 6 9
//        中序 3 4 5 10 6 7 9

    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 将中序遍历的下标用map存起来 方便寻找root位
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        TreeNode root = recurrenceBuildTree(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
        return root;

    }

    public static TreeNode recurrenceBuildTree(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd){
        if (preOrder.length != inOrder.length) return null;
        if (preStart > preEnd) return null;

        TreeNode root = new TreeNode(preOrder[preStart]);

        int current_root = preOrder[preStart];
        int splitIndex = indexMap.get(current_root);

        root.left = recurrenceBuildTree(
                preOrder, inOrder,
                preStart + 1,
                splitIndex - inStart + preStart,
                inStart,
                splitIndex - 1);

        root.right = recurrenceBuildTree(preOrder, inOrder,
                splitIndex - inStart + preStart + 1,
                preEnd,
                splitIndex + 1,
                inEnd);


        return root;

    }





}
