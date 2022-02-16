import java.util.LinkedList;
import java.util.List;

public class AllSort {


    public static void main(String[] args) {
        AllSort allSort = new AllSort();
        allSort.permute(new int[]{1,2,3});
    }

    public List<List<Integer>> res = new LinkedList<>();

    // 全排列
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> sorts = new LinkedList<>();
        backTrack(nums, sorts);
        return res;
    }

    public void backTrack(int[] nums, LinkedList<Integer> track){
        // 如果此列表已经凑满，那么就结束这个回溯
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        // 做选择
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            backTrack(nums, track);
            //取消选择
            track.removeLast();
        }

    }




}
