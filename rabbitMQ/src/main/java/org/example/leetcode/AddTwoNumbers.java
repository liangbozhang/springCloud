package org.example.leetcode;

import java.util.*;

public class AddTwoNumbers {

    public static void main(String[] args) {

        /*ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(3);
        l1.next = l2;

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        l5.next = new ListNode(4);
        l4.next = l5;

        Solution solution = new Solution();
        ListNode listNode = solution.addTwoNumbers(l1, l4);*/

        Solution solution = new Solution();
        /*int[][] nums = {{7,2,1},{6,4,2},{6,5,3},{3,2,1}};
        solution.matrixSum(nums);*/
        //solution.kItemsWithMaximumSum(3, 2, 0, 4);
        /*int[] nums = {1,3,5,6};
        int insert = solution.searchInsert(nums, 2);
        System.out.println(insert);*/
        List<Long> list = solution.maximumEvenSplit(12);
        System.out.println(list.toString());


    }

}

class Solution {

    public boolean isCircularSentence(String sentence) {
        long l = System.currentTimeMillis();
        int n = sentence.length();
        for(int i = 0;i < n;i++){
            if(sentence.charAt(i) == ' '){
                if(sentence.charAt(i + 1) != sentence.charAt(i - 1)){
                    return false;
                }
            }
        }
        boolean b = sentence.charAt(0) == sentence.charAt(n - 1);
        long l1 = System.currentTimeMillis();
        System.out.println(l - l1);
        return b;
    }

    public boolean canChange(String start, String target) {
        char l = 'L';
        char r = 'R';
        List sl = new ArrayList();
        List tl = new ArrayList();
        for (int i = 0; i < target.length(); i ++){
            if (target.charAt(i) == 'L'){
                sl.add(i);
            }else if (target.charAt(i) == 'R'){
                tl.add(i);
            }
        }


        String s1 = start.replaceAll("_", "");
        String s2 = target.replaceAll("_", "");
        if (!s1.equals(s2)){
            return false;
        }
        for (int i = 0; i < target.length(); i ++){
            char s = start.charAt(i);
            char t = target.charAt(i);
            if (t != s){
                if (i == 0 && s == l){
                    return false;
                }else if (i == target.length()-1 && s == r){
                    return false;
                }else if (s == l){

                }else if (s == r){
                    ThreadLocal threadLocal = new ThreadLocal();
                    threadLocal.set("");
                }
            }
        }
        return true;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null;
        ListNode wei = null;
        int incr = 0;
        while (l1 != null || l2 != null || incr > 0){
            int sum = incr;
            sum += l1 != null ? l1.val : 0;
            sum += l2 != null ? l2.val : 0;
            ListNode listNode = new ListNode(sum % 10);
            if (head == null){
                head = listNode;
            }else {
                if (wei == null){
                    wei = listNode;
                    head.next = wei;
                }else {
                    wei.next = listNode;
                    wei = listNode;
                }
            }
            incr = sum / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return head;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++){
            int tem = target - nums[i];
            if (map.containsKey(tem)){
                res[0] = map.get(tem);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     *  [7,2,1]
     *  [6,4,2]
     *  [6,5,3]
     *  [3,2,1]
     *
     *  7,6,6,3 +7
     *  2,4,5,2 +5
     *  1,2,3,1 +3
     *  nums [m][n]
     */
    public int matrixSum(int[][] nums) {
        int sum = 0;
        int m = nums.length;
        int n = nums[0].length;
        for (int[] num : nums) {
            Arrays.sort(num);
        }
        for (int i = 0; i < n; i ++){
            int max = 0;
            for (int[] num : nums) {
                if (num[i] > max) {
                    max = num[i];
                }
            }
            sum+=max;
        }
        return sum;
    }

    /**
     * 3 2 0
     * 2
     * 3
     */
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int max = 0;
        if (k <= numOnes){
            max = k;
        }else if (k <= (numOnes + numZeros)){
            max = numOnes;
        }else {
            max = numOnes - (k - numZeros - numOnes);
        }
        return max;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) / 2);
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return right + 1;
    }

    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> list = new ArrayList<>();
        if (finalSum % 2 == 0){
            for (int i = 2; true; i+=2){
                finalSum = finalSum - i;
                if (finalSum > i){
                    list.add((long) i);
                }else {
                    list.add(finalSum + i);
                    break;
                }
            }
        }
        return list;
    }

}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }