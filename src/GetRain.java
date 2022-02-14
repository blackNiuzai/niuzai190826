public class GetRain {

    // 42. 接雨水
    public int trap(int[] height) {

        int len = height.length;
        // 记录每根柱子左边最高的柱子
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++){
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        // 记录每根柱子右边最高的柱子
        int[] rightMax = new int[len];
        rightMax[height.length-1] = height[height.length-1];
        for(int i = height.length-2; i>=0; i--){
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;

    }




}
