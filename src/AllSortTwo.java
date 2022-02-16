import java.util.*;

public class AllSortTwo {



    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length == 0) return result;

        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] used = new boolean[length];
        Arrays.fill(used,false);
        dfs(result, length, length, nums ,stack, used);

        return result;
    }


    private void dfs(List<List<Integer>> result, int len, int depth, int[] nums, Deque<Integer> stack, boolean[] used){
        // 终止条件， 递归深度达到数量
        if (depth == len){
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < len; i++) {
            if(used[i]) continue;
            stack.addLast(i);
            used[i] = true;
            dfs(result, len, depth + 1, nums, stack, used);
            stack.removeLast();
            used[i] = false;
        }
















    }


}
