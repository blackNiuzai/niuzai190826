package Sort;

public class QuickSort {

    /**
     * 分析
     * 1、选定一个基准值， array[low]
     * 2、右指针从右向左遍历high--，查找比基准值小的数据，左指针从左向右遍历low++，查找比基准值大的数据
     * 3、如果指针未相遇，交换左右两值的位置，如果指针相遇，则替换基准值的位置
     * 4、左递归，右递归
     */

    public static void quickSort(int[] array, int low, int high){
        if(low >= high){
            return;
        }

        //1、指定基准值和左右指针的位置
        int basic = array[low];
        int l = low;
        int r = high;

        int temp = 0;

        //2、遍历条件，左右指针位置
        while(l < r){
            while (l< r && array[r] >= basic){
                r--;
            }
            while (l<r && array[l]<= basic){
                l++;
            }

            //如果指针没有相遇则交换数字位置
            if (l < r){
                temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
        }
        // 左右指针相遇 交换基准值位置
        array[low] = array[l];
        array[l] = basic;

        //递归
        if(low < l){
            quickSort(array, low, l-1);
        }
        if(r < high){
            quickSort(array, r + 1, high);
        }
    }


}
