/*  link: https://leetcode.com/problems/count-special-subsequences/description/
    Typical target sum problem
    Time : O(N.N)
    Space : O(N.N)
*/ 
import java.util.*;
class Solution {
        long numberOfSubsequences(int[] nums) {
        Map<Double, Integer> freq = new HashMap<>();
        long ans = 0;
        for(int r = 4; r < nums.length; ++r){
            for(int p = 0, q = r - 2; p < q-1; ++p) {
                Double key = (double)nums[p]/nums[q];
                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }
            for(int s = r + 2; s < nums.length; ++s){
                ans += freq.getOrDefault((double)nums[s]/nums[r], 0);
            }
        }
        return ans;
    }
}

/*
  Intuition
When ever thre is some type of equation is given with multiple indices involved then try to rearrange the equation in 
such a way that the LHS and RHS have some similirity and also the indices sequences involved are in same side 
( LHS have the starting indices and RHS will have ending indices). This technique works well in most of similar cases.

Here the indices sequences are not in proper order ( as 0 & 2 on LHS and 1 & 3 are on RHS).
 So lets re-write the operation in such a way that the LHS side have indices 0 & 1 and the RHS side 
 have indices 2,3 => nums[p]/nums[q] == nums[s]/nums[r];

Now if you observe this operation then you can find similarity with the clasical target sum problem.

Also we use the technique of fixing middle items and let vary the left and right. So here we 
take posible cases of q , r and then keep check all p and all s possible for that combination of q and r. 
This is possible in our case because in the LHS we have (p,q) where we are fixing q and in RHS we have (r,s) 
where we are fixing r. Also we are binding value of r and q by setting q = r - 2.
 This way we can able track all possible cases.

Note:
To avoid taking double as key we can create a hash key from the two int and then add it to map like this :

string getHashKey(int a, int b){
  int g = gcd(a, b);
  return to_string(a/g) + "#" + to_string(b/g);
}
Approach :
For simplicity, we need to first get all the pairs of r and s. So for this lets write two for loops.

Then for each case of r, before we check all occurance of (r,s) paris, we need to update the map. 
So add all the (p,q) pairs with q = r -2 and p can take all possible value from 0 to q - 2 and
 update the map with (p,q) pair.
*/