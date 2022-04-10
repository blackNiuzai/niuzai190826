import java.util.PriorityQueue;

public class PriorityQTest {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,4,5,6};
        PriorityQTest instance = new PriorityQTest();
        instance.findKthLargest(nums, 2);

    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minTree = new PriorityQueue<>();

        for(int i = 0; i < nums.length; i++){
            minTree.add(nums[i]);
            if (minTree.size() > k){
                minTree.poll();
            }
        }
        return minTree.peek();
    }


}
