package interview;

import java.util.*;

public class TopK {
    // 解法1：使用优先队列
    public List<Integer> topK_1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int x : nums) {
            if (queue.size() < k || x > queue.peek()) {
                queue.offer(x);
            }
            if (queue.size() > k) queue.poll();
        }
        List<Integer> ans = new ArrayList<>(k);
        while (!queue.isEmpty()) {
            ans.add(0, queue.poll());
        }
        return ans;
    }

    // 解法2：使用小根堆
    public List<Integer> topK_2(int[] nums, int k) {
        int[] heap = Arrays.copyOf(nums, k);
        int n = nums.length;
        adjust(heap, k, 0);
        for (int i = k; i < n; i++) {
            if (nums[i] > heap[0]) {
                heap[0] = nums[i];
                adjust(heap, k, 0);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(0, heap[0]);
            swap(heap, 0, k - i - 1);
            adjust(heap, k - i - 1, 0);
        }
        return ans;
    }

    private void adjust(int[] heap, int size, int root) {
        int left = root * 2 + 1;
        int right = root * 2 + 2;
        int smallest = root;
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != root) {
            swap(heap, smallest, root);
            adjust(heap, size, smallest);
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    // 解法3：使用快排的思想
    public int topK_3(int[] nums, int k) {
        return find(nums, k, 0, nums.length - 1);
    }

    private int find(int[] A, int k, int start, int end) {
        int index = partition(A, start, end);
        int large = end - index + 1;
        if (large == k) {
            return A[index];
        } else if (large < k) {
            return find(A, k - large, start, index - 1);
        } else {
            return find(A, k, index + 1, end);
        }
    }

    private int partition(int[] A, int start, int end) {
        int pivot = A[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (A[j] < pivot) swap(A, ++i, j);
        }
        swap(A, ++i, end);
        return i;
    }

    public static void main(String[] args) {
        List<Integer> ans = new TopK().topK_2(
                new int[]{1, 1, 1, 2, 2, 3}, 5
        );
        for (int x : ans) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(new TopK().topK_3(
                new int[]{1, 1, 1, 2, 2, 3}, 5
        ));
    }
}
