package lintcode;

import java.util.Arrays;

public class Solution14 {
    public int lower_bound2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (left < nums.length && nums[left] == target) return left;
        else return -1;
    }

    // C++ STL
    public int lower_bound(int[] arr, int target) {
        int half;
        int len = arr.length;
        int mid;
        int first = 0;
        while (len > 0) {
            half = len >> 1;
            mid = first + half;
            //in the right part
            if (arr[mid] < target) {
                first = mid + 1;
                //因为first=mid+1,所以这里的len需要在减去half的基础之上再减去1
                len = len - half - 1;
            } else {
                //in the left part
                len = half;
            }
        }
        return first;
    }

    public int upper_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (right - left > 1) {
            int mid = (left + right) >>> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.printf("left: %d right %d\n", left, right);
        if (nums[left] == target) return left;
        else return -1;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{},0));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1},0));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1},2));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1},1));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1,2},0));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1,2},1));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1,2},2));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1,2},3));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{2,6,8,13,15,17,17,18,19,20},15));
//        System.out.println(new Solution14()
//                .lower_bound(new int[]{1,1,1,1},1));
        System.out.println(new Solution14()
                .lower_bound2(new int[]{1},2));
    }
}
