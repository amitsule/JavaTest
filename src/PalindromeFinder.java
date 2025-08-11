import java.util.HashSet;
import java.util.Set;

public class PalindromeFinder {

    /**
     * Finds all palindromic substrings within a given text string.
     *
     * @param text The input string to search for palindromes.
     * @return A Set of unique palindromic substrings.
     */
    public Set<String> findAllPalindromes(String text) {
        Set<String> palindromes = new HashSet<>();
        if (text == null || text.length() == 0) {
            return palindromes;
        }

        for (int i = 0; i < text.length(); i++) {
            // Find odd-length palindromes (centered at i)
            expandAroundCenter(text, i, i, palindromes);
            // Find even-length palindromes (centered between i and i+1)
            expandAroundCenter(text, i, i + 1, palindromes);
        }

        return palindromes;
    }

    /**
     * Expands outwards from a given center to find palindromes.
     *
     * @param text       The input string.
     * @param left       The left pointer, initially the center or left part of the center pair.
     * @param right      The right pointer, initially the center or right part of the center pair.
     * @param palindromes The Set to add found palindromes to.
     */
    private void expandAroundCenter(String text, int left, int right, Set<String> palindromes) {
        while (left >= 0 && right < text.length() && text.charAt(left) == text.charAt(right)) {
            // If characters match, it's a palindrome. Add it to the set.
            String str = text.substring(left, right + 1);
            if (str.length() > 1)
                palindromes.add(str);
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        PalindromeFinder finder = new PalindromeFinder();

        String text1 = "madam";
        Set<String> result1 = finder.findAllPalindromes(text1);
        System.out.println("Palindromes in \"" + text1 + "\": " + result1);
        // Expected: [m, a, d, ad, ada, madam] (order might vary due to HashSet)

        String text2 = "racecar";
        Set<String> result2 = finder.findAllPalindromes(text2);
        System.out.println("Palindromes in \"" + text2 + "\": " + result2);
        // Expected: [r, a, c, e, aceca, racecar] (and single chars)

        String text3 = "abccba";
        Set<String> result3 = finder.findAllPalindromes(text3);
        System.out.println("Palindromes in \"" + text3 + "\": " + result3);
        // Expected: [a, b, c, cc, bccb, abccba] (and single chars)

        String text4 = "hello";
        Set<String> result4 = finder.findAllPalindromes(text4);
        System.out.println("Palindromes in \"" + text4 + "\": " + result4);
        // Expected: [h, e, l, o] (and single chars)

        String text5 = "";
        Set<String> result5 = finder.findAllPalindromes(text5);
        System.out.println("Palindromes in \"" + text5 + "\": " + result5);
        // Expected: []

        String text6 = "a";
        Set<String> result6 = finder.findAllPalindromes(text6);
        System.out.println("Palindromes in \"" + text6 + "\": " + result6);
        // Expected: [a]
    }
}