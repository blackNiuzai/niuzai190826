package TreeProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * 114题
 * 先序遍历组合
 *
 */
public class Flatten {

    public void flatten(TreeNode root) {
        List<TreeNode> readRes = new ArrayList<>();
        preOrderRead(root, readRes);

        if (readRes.size() == 0) return;
        TreeNode res = readRes.get(0);
        TreeNode cur = res;
        for(int i = 1; i < readRes.size(); i++){
            cur.left = null;
            cur.right = readRes.get(i);
            cur = cur.right;
        }
    }


    private void preOrderRead(TreeNode root, List<TreeNode> readRes){
        if (root == null) return;
        readRes.add(root);
        preOrderRead(root.left, readRes);
        preOrderRead(root.right, readRes);
    }

}
