import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution
{
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<>();
        int maxLength=0;
        int left=0;
        for(int right=0;right<s.length();right++){

            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                maxLength=Math.max(maxLength,right-left+1);

            }else{
                while(s.charAt(left)!=s.charAt(right)){
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));left++;
                set.add(s.charAt(right));
            }

        }
        return maxLength;
    }

    //---------------------------------------------------------------------------------------------
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        String result = s.substring(0, 1); // Start with 1st char as the longest

        for (int center = 0; center < n; center++) {
            // Check for odd-length palindromes (single center)
            result = expandAndUpdate(s, center, center, result);
            // Check for even-length palindromes (two centers)
            result = expandAndUpdate(s, center, center + 1, result);
        }

        return result;
    }

    // Helper function to expand around center and update result
    private String expandAndUpdate(String s, int left, int right, String currentBest) {
        int n = s.length();

        // Expand as long as characters match and within bounds
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // Substring from left+1 to right-1 is the palindrome
        String newPalindrome = s.substring(left + 1, right);

        // Update if new one is longer
        if (newPalindrome.length() > currentBest.length()) {
            return newPalindrome;
        } else {
            return currentBest;
        }
    }

    //---------------------------------------------------------------------------------------------
    public String convert(String s, int numRows) {
        List<List<Character>> mat = new ArrayList<>();
        for(int r = 0;r<numRows;r++)
        {
            mat.add(new ArrayList<>());
        }
        int n = s.length();
        int i = 0;
        while(i<n)
        {
            for(int down = 0;down<numRows && i<n;down++)
            {
                mat.get(down).add(s.charAt(i++));
            }
            for(int up = numRows-2;up>0  && i<n;up--)
            {
                mat.get(up).add(s.charAt(i++));
            }
        }
        String ans = "";
        for(i=0;i<numRows;i++)
        {
            for(int j=0;j<mat.get(i).size();j++)
                ans += mat.get(i).get(j);
        }
        return ans;
    }

    public static void main(String[] args)
    {
//        String test = "abcabcdebb";
//        System.out.println(lengthOfLongestSubstring(test));

        Solution sol = new Solution();
//        System.out.println(sol.longestPalindrome("babad"));
        System.out.println(sol.convert("PAYPALISHIRING", 4));
    }
}
