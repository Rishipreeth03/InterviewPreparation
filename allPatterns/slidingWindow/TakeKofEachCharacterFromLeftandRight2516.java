/*  Sliding window approach
TC:O(2^n)
SC:O(1)
*/
class TakeKofeachCharacterFromLeftandright2516 {
    public int takeCharacters(String s, int k) {
        int n=s.length();
        int countA=0,countB=0,countC=0;
        for(char c:s.toCharArray()){
            if(c=='a')countA++;
            else if(c=='b')countB++;
            else countC++;
        }
        if(countA<k || countB<k||countC<k){
            return -1;
        }
        int i=0,j=0;
        int numberOfDelNodes=0;
        while(j<n){
            //Reduce count for char at j index
            if(s.charAt(j)=='a')countA--;
            else if(s.charAt(j)=='b')countB--;
            else countC--;
            //if any aount falls below k ,shrink the window from left
            while(i<=j && (countA<k||countB<k || countC<k)){
                if(s.charAt(i)=='a'){
                    countA++;
                }else if(s.charAt(i)=='b')countB++;
                else countC++;
                i++;
            }
            //update maximum size of window
            numberOfDelNodes=Math.max(j-i+1,numberOfDelNodes);
            j++;
        }
        //min minutes required(total -max window size)
        return n-numberOfDelNodes;
    }
}

/*  Brute Force Recursive 
TC:O(2^n)
SC:O(1)-Recursion stack space we are taking O(n) in worst case
class Solution{
    private int result=Integer.MAX_VALUE;
    public void solve(String s,int k,int i,int j,int minutes,int[] freq){
        if(freq[0]>=k && freq[1]>=k && freq[2]>=k){
            result=math.min(result,minutes);
            return;
        }
        if(i>j)return;

        int[] tempFreqLeft=freq.clone();
        tempFreqLeft[s.charAt(i)-'a']++;
        solve(s,k,i+1,j,minutes+1,tempFreqLeft);

        int[] tempFreqRight=freq.clone();
        tempFreqRight[s.charAt(i)-'a']++;
        solve(s,k,i,j-1,minutes+1,tempFreqRight);
    }
    public int takeCharacter(String s, int k){
        int[] freq=new int[3];
        int i=0,j=s.length()-1;
        int minutes=0;
        solve(s,k,i,j,minutes,freq);
        return result==Integer.MAX_VALUE?-1:result;
    }
}

*/

