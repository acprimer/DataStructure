package interview;

public class MedianOfTwoSortedArray {

    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int leftA = 0, rightA = A.length - 1;
        int leftB = 0, rightB = B.length - 1;
        int small = 0, large = 0;
        int half = (n+m-1)/2;
        int ansSmall = 0, ansLarge= 0;
        while (small <= half && large <= half) {
            int midA = (leftA + rightA) >>> 1;
            int midB = (leftB + rightB) >>> 1;
            if (A[midA] < B[midB]) {
                small += midA - leftA;
                large += rightB - midB;
                leftA = midA;
                rightB = midB;
            } else {
                small += rightA - midA;
                large += midB - leftB;
                rightA = midA;
                leftB = midB;
            }
        }
        return 0;
    }
}
