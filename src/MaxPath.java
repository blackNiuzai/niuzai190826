public class MaxPath {

    int maxRes = Integer.MIN_VALUE;

    // 124 最大路径
    public int maxPathSum(TreeNode node){
        maxGain(node);
        return maxRes;
    }

    private int maxGain(TreeNode node) {
        if (node==null) return 0;
        // 递归获取子树的最大贡献值
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);

        maxRes = Math.max(maxRes, left+right+node.val);

        return Math.max(left,right) + node.val;

    }

}
