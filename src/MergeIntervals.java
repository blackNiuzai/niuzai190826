import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56题 merge intervals
 *
 *
 */


public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];
        Arrays.sort(intervals, (v1, v2) -> v1[0]-v2[0]);

        List<int[]> mergeList = new ArrayList<>();
        mergeList.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++){
            int min = intervals[i][0];
            int max = intervals[i][1];
            int[] lastInterval = mergeList.get(mergeList.size() - 1);
            if (lastInterval[1] >= min){
                // need merge
                int right = Math.max(max, lastInterval[1]);
                lastInterval[1] = right;
            }else {
                mergeList.add(intervals[i]);
            }
        }

        return mergeList.toArray(new int[mergeList.size()][2]);

    }



}
