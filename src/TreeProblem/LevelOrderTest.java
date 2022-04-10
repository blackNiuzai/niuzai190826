package TreeProblem;

import java.util.*;

/*
 102 层序遍历
 */
public class LevelOrderTest {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if(root == null){
            return levelOrder;
        }
        Deque<TreeNode> curLevel =  new LinkedList<>();
        curLevel.add(root);

        while (curLevel.size() > 0){
            List<Integer> curOrder = new ArrayList<>();
            int size = curLevel.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = curLevel.poll();
                curOrder.add(node.val);
                if (node.left != null){
                    curLevel.addLast(node.left);
                }
                if(node.right != null){
                    curLevel.addLast(node.right);
                }

            }
            levelOrder.add(curOrder);
        }

        return levelOrder;
    }
}
