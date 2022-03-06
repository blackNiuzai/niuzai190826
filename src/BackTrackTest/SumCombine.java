package BackTrackTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 *
 */
public class SumCombine {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        blackTrack(candidates, new LinkedList<>(), 0, target);
        return result;
    }

    public void blackTrack(int[] candidates, LinkedList<Integer> combine, int sum, int target){
        if (sum == target){
            result.add(new ArrayList<>(combine));
            return;
        }

        // 这里去重的思路为只能加入大于等于自身最后一个元素的的值
        for (int i = 0; i < candidates.length; i++) {
            int need = target - sum;
            if (candidates[i] > need) continue;
            if (combine.size() > 0){
                if (combine.getLast()> candidates[i]){
                    continue;
                }
            }
            combine.add(candidates[i]);
            blackTrack(candidates, combine, sum+candidates[i], target);
            combine.removeLast();
        }
    }




}
