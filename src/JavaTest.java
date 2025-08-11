import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaTest
{
    private static String timeConversion(String s)
    {
        // Write your code here
        if (s == null || s.length() < 10)
        {
            return "NULL";
        }

        char timeChar = s.charAt(8);
        String hour = s.substring(0, 2);
        String time = s.substring(2, s.length() - 2);
        int temp = Integer.parseInt(hour);
        System.out.println(temp);
        if (timeChar == 'P')
        {
            if (temp != 12)
            {
                temp += 12;
            }
            if (temp >= 24)
            {
                hour = "00";
            }
            else
            {
                hour = Integer.toString(temp);
            }
        }
        else if (temp == 12)
        {
            hour = "00";
        }
        System.out.println("Hour : " + hour);
        return hour + time;
    }

    static void minMaxSum(List<Integer> arr)
    {
        // Write your code here
        if (arr == null)
        {
            return;
        }
        Collections.sort(arr);
        System.out.println(arr);
        long min = 0;
        long max = 0;
        for (int idx = 0; idx < arr.size(); idx++)
        {
            int num = arr.get(idx);
            if (idx != arr.size() - 1)
            {
                min += num;
            }
            if (idx != 0)
            {
                max += num;
            }
        }
        System.out.println("Min : " + min);
        System.out.println("Max : " + max);
    }

    static String checkBrackets(String s)
    {
        Stack<Character> stack = new Stack<>();

        //Traversing the expression
        for (int i = 0; i < s.length(); i++)
        {
            char x = s.charAt(i);

            if (x == '(' || x == '[' || x == '{')
            {
                //Push the element in the stack
                stack.push(x);
                continue;
            }

            //If current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty())
            {
                return "NO";
            }

            char check;
            switch (x)
            {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                    {
                        return "NO";
                    }
                    break;
                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                    {
                        return "NO";
                    }
                    break;
                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                    {
                        return "NO";
                    }
                    break;
            }
        }
        //Check empty stack

        StringBuilder response = new StringBuilder();

        if (stack.isEmpty())
        {
            response.append("YES");
        }
        else
        {
            response.append("NO");
        }

        return response.toString();
    }

    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2)
    {
        SinglyLinkedListNode head = new SinglyLinkedListNode();
        SinglyLinkedListNode ans = head;

        while (head1 != null && head2 != null)
        {
            if (head1.data <= head2.data)
            {
                ans.next = head1;

                head1 = head1.next;
            }
            else
            {
                ans.next = head2;
                head2 = head2.next;
            }
            ans = ans.next;
        }

        if (head1 != null)
        {
            ans.next = head1;
        }
        if (head2 != null)
        {
            ans.next = head2;
        }

        return head.next;
    }

    static void noPrefix(List<String> words)
    {
        // Write your code here
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < words.size(); i++)
        {
            String word = words.get(i);
            String next = treeSet.ceiling(word);
            String prev = treeSet.floor(word);

            if ((next != null && next.startsWith(word)) || (prev != null && word.startsWith(prev)))
            {
                System.out.println("BAD SET");
                System.out.println(word);
                return;
            }

            treeSet.add(word);
        }

        System.out.println("GOOD SET");
    }

    static void checkTreeset()
    {
        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.add("aab");
        treeSet.add("aac");
        treeSet.add("aacghgh");
        treeSet.add("abbcghgh");
        treeSet.add("acaghgh");

        System.out.println(treeSet.ceiling("aca"));
    }

    static void checkHashMap()
    {
        Map<String, String> books = new HashMap<>();
        books.put(
                "978-0201633610", "Design patterns : elements of reusable object-oriented software");
        books.put(
                "978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
        books.put("978-0134685991", "Effective Java");
        books.put("978-0321356680", "Effective Java: Second Edition");

        Optional<String> optionalIsbn = books.entrySet().stream()
                .filter(e -> "Effective Java".equals(e.getValue()))
                .map(e -> e.getKey())
                .findFirst();
        System.out.println(optionalIsbn.get());
        List<String> test = books.values().stream()
                .map(Function.identity())
                .collect(Collectors.toList());
    }

    static void caesarCipher(String string, int num)
    {
        StringBuilder sb = new StringBuilder();
        IntStream stream = sb.chars();
        for (char cChar : string.toCharArray())
        {
            if (Character.isLetter(cChar))
            {
                int aNumberValue = (Character.isUpperCase(cChar) ? 'A' : 'a');
                int value = (cChar + num - aNumberValue) % 26;
                sb.append((char) (value + aNumberValue));
            }
            else
            {
                sb.append(cChar);
            }
        }
        ;
        System.out.println(sb.toString());
    }

    static void checkMedian(List<Integer> arr)
    {
        Collections.sort(arr);
        System.out.println(arr + "      " + arr.size());
        System.out.println(arr.get((int) arr.size() / 2));
    }

    static int maxMin(int k, List<Integer> arr)
    {
        Collections.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= arr.size() - k; i++)
        {
            int x = arr.get(i + (k - 1));
            int y = arr.get(i);
            int a = x - y;
            if (min > a)
            {
                min = a;
            }
        }
        return min;
    }

//    static char[] alphabet = "areallylongword".toCharArray();
    static char[] alphabet = "are".toCharArray();
    static ArrayList<String> processed = new ArrayList<>();

    static void generateCombinations(StringBuilder sb, int n, int pos) {
        if (n == sb.length()) {
            if (!processed.contains(sb.toString()))
            {
                System.out.println(sb.toString());
                processed.add(sb.toString());
            }
            return;
        }
        for (int index = 0; index < alphabet.length; index++)
        {
            char letter = alphabet[index];
            sb.setCharAt(n, letter);
            generateCombinations(sb, n + 1, index);
        }
    }

    static void generateAlphaNumeric(int length)
    {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        Random random = new Random();
        String result = random.ints(leftLimit, rightLimit)
//                        .filter(num -> (num <= 57 || num >= 65) && (num <= 90 || num >= 97))
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
        System.out.println("Generated : " + result);
    }

    static void anagrams()
    {
        List<String> words = Arrays.asList("listen", "silent", "enlist", "rat", "tar", "god", "dog", "evil", "vile", "veil");

        Map<String, List<String>> anagrams =  words.stream()
                .collect(Collectors.groupingBy(word -> {
                    char[] charArray = word.toCharArray();
                    Arrays.sort(charArray); // Sort the characters
                    return new String(charArray); // Return the sorted string
                }));

        anagrams.values().stream()
                .filter(group -> group.size() > 1) // Filter only groups with more than one anagram
                .forEach(System.out::println); // Output the anagram groups

// Output
// [silent, listen, enlist]
// [tar, rat]
// [dog, god]
// [vile, evil, veil]


    }

    public static void main(String[] args)
    {
//        String result = timeConversion("12:45:54PM");
//        System.out.println("Result : " + result);
//        List<Integer> arr = new ArrayList<>(Arrays.asList(256741038, 623958417, 467905213, 714532089, 938071625));
//        minMaxSum(arr);
//        testMyCode();
//        noPrefix(List.of("aab", "aac", "aacghgh", "aacghgh"));
//        noPrefix(List.of("ab", "ac", "aacghgh", "adc", "dce", "aabghgh"));
//        checkTreeset();
        checkHashMap();

//        Scanner s = new Scanner(System.in);
//        String string = s.nextLine();
//        int num = s.nextInt();
//        caeserCipher(string, num);
//
//        checkMedian(Arrays.asList(1, 5, 4, 6, 8, 9, 3, 2));
//        System.out.println(maxMin(2, Arrays.asList(1, 5, 4, 6, 8, 9, 3, 2)));

//        StringBuilder sb = new StringBuilder();
//        int pos = 0;
//        for (int length = 2; length <= 3; length++) {
//            sb.setLength(length);
//            generateCombinations(sb, 0, 0);
//        }

//        String sTest = "This is a test string";
//        String result = reverse(sTest);
//        System.out.println(result);

//        int a = 10;
//        int b = 20;
//        swapNumbers(a, b);

//        String myString = "   I    am a    wonderful String     !   ";
////        String result = myString.replaceAll("\\s+", " ");
////        System.out.println(result);
//        System.out.println(myString.toLowerCase().matches(".*[aeiou].*"));

//        printFibonacciSeries(10);

//        generateAlphaNumeric(20);
        anagrams();
    }

    private static void printFibonacciSeries(int number)
    {
        int a = 0;
        int b = 1;
        int c = 1;
        for (int idx = 1; idx <= number; idx++)
        {
            System.out.println(a + ", ");
            a = b;
            b = c;
            c = a + b;
        }
    }

    private static int fibonacciSeries(int number)
    {
        if (number <= 1)
        {
            return number;
        }
        return fibonacciSeries(number - 1) + fibonacciSeries(number - 2);
    }

    private static void swapNumbers(int a, int b)
    {
        b = b + a;
        a = b - a;
        b = b - a;
        System.out.printf("a : %d and b : %d", a, b);
    }

    private static String reverse(String sTest)
    {
        if (sTest == null || sTest.isEmpty())
        {
            return sTest;
        }
        StringBuilder result = new StringBuilder();
//        return reverse(sTest.substring(1)) + sTest.charAt(0);
        for (int idx = sTest.length() - 1; idx >= 0; idx--)
        {
            result.append(sTest.charAt(idx));
        }
        return result.toString();
    }

    public void testMore()
    {
        List<String> people = List.of("Amit", "mayura", "inika", "Someone", "Test");
        people.stream().filter(person -> person.startsWith("s")).forEach(System.out::println);
    }
}

class SinglyLinkedListNode
{
    int data;
    SinglyLinkedListNode next;
}