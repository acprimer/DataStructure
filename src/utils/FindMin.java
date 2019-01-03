package utils;

public class FindMin {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] < nums[right]) {
                return nums[left];
            } else {
                int mid = (left + right) / 2;
                if (nums[left] > nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(new FindMin().findMin(new int[]{1, 2, 3}));
    }
}
