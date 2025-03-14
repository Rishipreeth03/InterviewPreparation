import java.util.*;
/*
    Yt reference  : https://www.youtube.com/watch?v=9wrKWB1xDoY
    Leetcode Link               : https://leetcode.com/problems/minimize-xor
*/
//Approach-1 (Starting from num1 and then forming result)
//T.C : O(log(n))
//S.C : O(1)
class Solution {

    public boolean isSet(int x, int bit) {
        return (x & (1 << bit)) != 0;
    }

    public int setBit(int x, int bit) {
        return x | (1 << bit);
    }

    public int unsetBit(int x, int bit) {
        return x & ~(1 << bit);
    }

    public int minimizeXor(int num1, int num2) {
        int x = num1;

        int requiredSetBitCount = Integer.bitCount(num2);
        int currSetBitCount = Integer.bitCount(x);

        int bit = 0;

        if(currSetBitCount < requiredSetBitCount) {
            while(currSetBitCount < requiredSetBitCount) {
                if(!isSet(x, bit)) {
                    x = setBit(x, bit);
                    currSetBitCount++;
                }
                bit++;
            }
        } else if(currSetBitCount > requiredSetBitCount) {
            while(currSetBitCount > requiredSetBitCount) {
                if(isSet(x, bit)) {
                    x = unsetBit(x, bit);
                    currSetBitCount--;
                }
                bit++;
            }
        }

        return x;
    }
}


//Approach-2 (Directly build result)
//T.C : O(log(n))
//S.C : O(1)
class Solution {

    public boolean isSet(int x, int bit) {
        return (x & (1 << bit)) != 0;
    }

    public int setBit(int x, int bit) {
        return x | (1 << bit);
    }

    public int unsetBit(int x, int bit) {
        return x & ~(1 << bit);
    }

    public boolean isUnSet(int x, int bit) {
        return (x & (1 << bit)) == 0;
    }

    public int minimizeXor(int num1, int num2) {
        int x = 0;

        int requiredSetBitCount = Integer.bitCount(num2);
        
        for(int bit = 31; bit >= 0 && requiredSetBitCount > 0; bit--) {
            if(isSet(num1, bit)) {
                x |= (1 << bit);
                requiredSetBitCount--;
            }
        }

        for(int bit = 0; bit < 32 && requiredSetBitCount > 0; bit++) {
            if(isUnSet(num1, bit)) {
                x |= (1 << bit);
                requiredSetBitCount--;
            }
        }
        
        return x;
    }
}