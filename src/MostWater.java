public class MostWater {

    public static void main(String[] args) {
        MostWater water = new MostWater();
        int res = water.maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        System.out.println(res);

    }


    public int maxArea(int[] height) {
        int len = height.length;
        int start = 0;
        int end = len - 1;
        int maxWater = 0;
        while (start < end){
            int currentWater = (end - start) * Math.min(height[start], height[end]);
            maxWater = Math.max(maxWater,currentWater);

            if(height[start]<height[end]){
                start++;
            }else {
                end--;
            }

        }
        return maxWater;

    }


}
