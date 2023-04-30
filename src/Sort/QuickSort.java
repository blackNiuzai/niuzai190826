package Sort;

import java.util.Arrays;

public class QuickSort {


    public static void main(String[] args) {
        QuickSort instance = new QuickSort();
        int[] array = new int[]{3,2,2,6,5,8,4};
        instance.quickSort(array, 0, 6);
        System.out.println(Arrays.toString(array));
    }


    public void quickSort(int[] array, int low, int high) {
        //递归结束条件
        if(low >= high) return;

        //走一趟并找到分割点
        int split = partition(array, low, high);
        quickSort(array, low, split-1);
        quickSort(array, split+1, high);
    }

    public int partition(int[] arr, int low, int high){
        int x = arr[high];
        int i = low;
        int temp;
        for (int j = low; j < high; j++){
            if (arr[j] <= x){
                //那么这个值就可一放到前面，由i指针控制
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        temp = arr[i];
        arr[i] = x;
        arr[high] = temp;

        return i;
    }




}
