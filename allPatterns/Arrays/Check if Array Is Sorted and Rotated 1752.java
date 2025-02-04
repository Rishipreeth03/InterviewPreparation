/*
    Link:
 */


//Approach -1( Compare the original array with the sorted array, considering all possible rotations)
//TC:O(n^2)
//SC:O(n)
class Solution {
    public boolean check(int[] nums) {
        int count=0;
        for(int i=0;i<nums.length;i++){
        if(nums[i]>nums[(i+1)%nums.length]){
            count++;
        }
        if(count>1){
            return false;
        }
        }
        return true;
    }
}

 //Approach-2(inversionCount keeps track of the number of times an element is smaller than the previous element.)
 //TC:O(n)
//SC:O(1)
 class Solution {
    public boolean check(int[] nums) {
        int n=nums.length;
        if(n<=1)return true;
        int inversioncnt=0;
        // For every pair, count the number of inversions.
        for(int i=1;i<n;i++){
            if(nums[i]<nums[i-1]){
                ++inversioncnt;
            }
        }
           // Also check between the last and the first element due to rotation
        if(nums[0]<nums[n-1]){
            ++inversioncnt;
        }
        return inversioncnt<=1;
    }
}
/*
 * This function is an efficient way to check whether an array is a 
rotated sorted array by counting at most one inversion. If the inversion 
count exceeds 1, then the array is not a rotated sorted array.
 */