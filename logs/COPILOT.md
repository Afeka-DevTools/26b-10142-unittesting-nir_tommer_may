I have a Java class called App.java with the following functions that I need to write unit tests for using JUnit 5.
    Here is the complete App.java code:

    package org.example;

    import java.util.*;

    public class App {

        public static int add(int a, int b) {
            return a + b;
        }

        public static boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        }

        public static String reverse(String s) {
            return new StringBuilder(s).reverse().toString();
        }

        public static int factorial(int n) {
            if (n < 0) throw new IllegalArgumentException("Negative number");
            int result = 1;
            for (int i = 2; i <= n; i++) result *= i;
            return result;
        }

        public static boolean isPalindrome(String s) {
            String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            return clean.equals(reverse(clean));
        }

        public static List<Integer> fibonacciUpTo(int n) {
            List<Integer> result = new ArrayList<>();
            if (n < 0) throw new IllegalArgumentException("Negative input");
            int a = 0, b = 1;
            while (a <= n) {
                result.add(a);
                int temp = a + b;
                a = b;
                b = temp;
            }
            return result;
        }

        public static Map<Character, Integer> charFrequency(String input) {
            Map<Character, Integer> freq = new HashMap<>();
            for (char c : input.toCharArray()) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
            return freq;
        }

        public static boolean isAnagram(String s1, String s2) {
            char[] a1 = s1.replaceAll("\\s", "").toLowerCase().toCharArray();
            char[] a2 = s2.replaceAll("\\s", "").toLowerCase().toCharArray();
            Arrays.sort(a1);
            Arrays.sort(a2);
            return Arrays.equals(a1, a2);
        }

        public static double average(int[] arr) {
            if (arr.length == 0) throw new IllegalArgumentException("Empty array");
            double sum = 0;
            for (int num : arr) sum += num;
            return sum / arr.length;
        }

        public static List<Integer> filterEvens(List<Integer> list) {
            List<Integer> evens = new ArrayList<>();
            for (int n : list) {
                if (n % 2 == 0) evens.add(n);
            }
            return evens;
        }

        public static String mostCommonWord(String text) {
            String[] words = text.toLowerCase().split("\\W+");
            Map<String, Integer> count = new HashMap<>();
            for (String word : words) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
            return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
        }
    }

    Please write a complete AppTest.java file with JUnit 5 unit tests for ALL functions above.
    Requirements:
    - Use @Test annotation for each test
    - Cover normal cases, edge cases, and boundary values for every function
    - Test all exception cases with assertThrows
    - Use assertEquals, assertTrue, assertFalse, assertThrows, assertNotNull where appropriate
    - Make sure all code paths (branches) are covered

● Here's a complete test suite covering all 12 functions, organized with @Nested classes so it's easy to navigate.

  package org.example;

  import org.junit.jupiter.api.DisplayName;
  import org.junit.jupiter.api.Nested;
  import org.junit.jupiter.api.Test;

  import java.util.List;
  import java.util.Map;

  import static org.junit.jupiter.api.Assertions.*;

  class AppTest {

      @Nested
      @DisplayName("add")
      class AddTests {

          @Test
          void addsTwoPositiveNumbers() {
              assertEquals(5, App.add(2, 3));
          }

          @Test
          void addsNegativeNumbers() {
              assertEquals(-5, App.add(-2, -3));
          }

          @Test
          void addsPositiveAndNegative() {
              assertEquals(1, App.add(-2, 3));
          }

          @Test
          void addsZero() {
              assertEquals(0, App.add(0, 0));
          }
      }

      @Nested
      @DisplayName("isPrime")
      class IsPrimeTests {

          @Test
          void negativeNumbersAreNotPrime() {
              assertFalse(App.isPrime(-5));
          }

          @Test
          void zeroIsNotPrime() {
              assertFalse(App.isPrime(0));
          }

          @Test
          void oneIsNotPrime() {
              assertFalse(App.isPrime(1));
          }

          @Test
          void twoIsPrime() {
              // smallest prime; loop body never executes since sqrt(2) < 2
              assertTrue(App.isPrime(2));
          }

          @Test
          void threeIsPrime() {
              assertTrue(App.isPrime(3));
          }

          @Test
          void fourIsNotPrime() {
              // smallest even composite, divisible by 2
              assertFalse(App.isPrime(4));
          }

          @Test
          void nineIsNotPrime() {
              // odd perfect square, tests divisor found mid-loop
              assertFalse(App.isPrime(9));
          }

          @Test
          void seventeenIsPrime() {
              // larger prime, exercises full loop without finding a divisor
              assertTrue(App.isPrime(17));
          }
      }

      @Nested
      @DisplayName("reverse")
      class ReverseTests {

          @Test
          void reversesNormalString() {
              assertEquals("olleh", App.reverse("hello"));
          }

          @Test
          void reversesEmptyString() {
              assertEquals("", App.reverse(""));
          }

          @Test
          void reversesSingleCharacter() {
              assertEquals("a", App.reverse("a"));
          }
      }

      @Nested
      @DisplayName("factorial")
      class FactorialTests {

          @Test
          void factorialOfFive() {
              assertEquals(120, App.factorial(5));
          }

          @Test
          void factorialOfZero() {
              assertEquals(1, App.factorial(0));
          }

          @Test
          void factorialOfOne() {
              assertEquals(1, App.factorial(1));
          }

          @Test
          void negativeThrowsException() {
              assertThrows(IllegalArgumentException.class, () -> App.factorial(-1));
          }
      }

      @Nested
      @DisplayName("isPalindrome")
      class IsPalindromeTests {

          @Test
          void simplePalindrome() {
              assertTrue(App.isPalindrome("racecar"));
          }

          @Test
          void nonPalindrome() {
              assertFalse(App.isPalindrome("hello"));
          }

          @Test
          void palindromeWithSpacesPunctuationAndCase() {
              assertTrue(App.isPalindrome("A man a plan a canal Panama"));
          }

          @Test
          void emptyStringIsPalindrome() {
              assertTrue(App.isPalindrome(""));
          }

          @Test
          void singleCharacterIsPalindrome() {
              assertTrue(App.isPalindrome("a"));
          }

          @Test
          void twoDifferentCharactersIsNotPalindrome() {
              assertFalse(App.isPalindrome("ab"));
          }
      }

      @Nested
      @DisplayName("fibonacciUpTo")
      class FibonacciUpToTests {

          @Test
          void zeroReturnsOnlyZero() {
              assertEquals(List.of(0), App.fibonacciUpTo(0));
          }

          @Test
          void oneReturnsZeroOneOne() {
              // Fibonacci sequence has two consecutive 1s (F1=1, F2=1)
              assertEquals(List.of(0, 1, 1), App.fibonacciUpTo(1));
          }

          @Test
          void fiveReturnsFullSequenceUpToFive() {
              assertEquals(List.of(0, 1, 1, 2, 3, 5), App.fibonacciUpTo(5));
          }

          @Test
          void negativeThrowsException() {
              assertThrows(IllegalArgumentException.class, () -> App.fibonacciUpTo(-1));
          }
      }

      @Nested
      @DisplayName("charFrequency")
      class CharFrequencyTests {

          @Test
          void countsRepeatedAndUniqueCharacters() {
              Map<Character, Integer> freq = App.charFrequency("hello");
              assertEquals(1, freq.get('h'));
              assertEquals(1, freq.get('e'));
              assertEquals(2, freq.get('l'));
              assertEquals(1, freq.get('o'));
          }

          @Test
          void emptyStringReturnsEmptyMap() {
              Map<Character, Integer> freq = App.charFrequency("");
              assertTrue(freq.isEmpty());
          }

          @Test
          void allSameCharacterCountsCorrectly() {
              Map<Character, Integer> freq = App.charFrequency("aaa");
              assertEquals(3, freq.get('a'));
              assertEquals(1, freq.size());
          }
      }

      @Nested
      @DisplayName("isAnagram")
      class IsAnagramTests {

          @Test
          void simpleAnagram() {
              assertTrue(App.isAnagram("listen", "silent"));
          }

          @Test
          void notAnAnagram() {
              assertFalse(App.isAnagram("hello", "world"));
          }

          @Test
          void anagramIgnoringCaseAndSpaces() {
              assertTrue(App.isAnagram("Dormitory", "Dirty Room"));
          }

          @Test
          void emptyStringsAreAnagrams() {
              assertTrue(App.isAnagram("", ""));
          }

          @Test
          void differentLengthsAreNotAnagrams() {
              assertFalse(App.isAnagram("a", "ab"));
          }
      }

      @Nested
      @DisplayName("average")
      class AverageTests {

          @Test
          void averageOfMultipleNumbers() {
              assertEquals(2.0, App.average(new int[]{1, 2, 3}));
          }

          @Test
          void averageOfSingleElement() {
              assertEquals(5.0, App.average(new int[]{5}));
          }

          @Test
          void averageOfNegativeAndPositiveCancelsOut() {
              assertEquals(0.0, App.average(new int[]{-2, 2}));
          }

          @Test
          void emptyArrayThrowsException() {
              assertThrows(IllegalArgumentException.class, () -> App.average(new int[]{}));
          }
      }

      @Nested
      @DisplayName("filterEvens")
      class FilterEvensTests {

          @Test
          void filtersOutOddNumbers() {
              assertEquals(List.of(2, 4, 6), App.filterEvens(List.of(1, 2, 3, 4, 5, 6)));
          }

          @Test
          void allOddsReturnsEmptyList() {
              assertTrue(App.filterEvens(List.of(1, 3, 5)).isEmpty());
          }

          @Test
          void allEvensReturnsAllOfThem() {
              assertEquals(List.of(2, 4), App.filterEvens(List.of(2, 4)));
          }

          @Test
          void emptyListReturnsEmptyList() {
              assertTrue(App.filterEvens(List.of()).isEmpty());
          }
      }

      @Nested
      @DisplayName("mostCommonWord")
      class MostCommonWordTests {

          @Test
          void findsMostFrequentWord() {
              assertEquals("the", App.mostCommonWord("the quick brown fox the lazy the dog"));
          }

          @Test
          void singleWordIsMostCommon() {
              assertEquals("hello", App.mostCommonWord("hello"));
          }

          @Test
          void isCaseInsensitiveAndIgnoresPunctuation() {
              assertEquals("hello", App.mostCommonWord("Hello, hello! HELLO."));
          }

          @Test
          void emptyStringReturnsEmptyToken() {
              // "".split("\\W+") yields a single empty-string token, not an empty array
              assertEquals("", App.mostCommonWord(""));
          }
      }
  }

  A few things worth flagging about decisions I made:

  - fibonacciUpTo(1) returns [0, 1, 1], not [0, 1] — the true Fibonacci sequence has two consecutive 1s (F1 and F2), and this implementation collects all of them. I traced the loop by hand to confirm this
  before writing the assertion; if you assumed [0, 1] you'd get a confusing test failure that's actually correct behavior, not a bug.
  - mostCommonWord("") doesn't throw — "".split("\\W+") returns [""] (one empty-string element), so the method returns "". That's a real edge case I'd flag to you as possibly unintended behavior in App, even
  though the test documents the current behavior correctly.
  - I avoided testing tie-breaking in mostCommonWord (e.g., two words with equal frequency) because it relies on HashMap's iteration order via Collections.max, which is unspecified — such a test would be
  flaky.
  - Every method with a thrown exception (factorial, fibonacciUpTo, average) has both the exception-triggering case and normal cases, giving full branch coverage on those if checks.

  Want me to add JaCoCo to your build.gradle and walk through what the coverage report shows for this exact test suite?

✻ Brewed for 54s

❯  Are the tests you wrote sufficient?
How do we know all code paths (branches) in App.java are covered?
Can you go through each function and confirm which branches are tested?

● Good instinct to ask — I reasoned through the branches when writing the tests, but manual reasoning is exactly the kind of thing that should be verified, not just trusted. Let me walk through each function's
  actual decision points, then tell you how to confirm it for real instead of taking my word for it.

  Branch-by-branch confirmation

  add — no if/loop, single expression. Nothing to branch on; any test gives 100%.

  isPrime — three decision points: n<2, the loop entry condition (i <= sqrt(n)), and n%i==0 inside the loop.

  ┌──────────────────────────────────────┬────────────────────────────────────────────────────────────────┐
  │                Branch                │                           Covered by                           │
  ├──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
  │ n<2 → true                           │ n=-5, 0, 1                                                     │
  ├──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
  │ n<2 → false                          │ n=2, 3, 4, 9, 17                                               │
  ├──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
  │ loop body never runs                 │ n=2, 3 (sqrt < 2)                                              │
  ├──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
  │ loop body runs                       │ n=4, 9, 17                                                     │
  ├──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
  │ n%i==0 → true (divisor found)        │ n=4 (found immediately), n=9 (found after one false iteration) │
  ├──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
  │ loop completes with no divisor found │ n=17                                                           │
  └──────────────────────────────────────┴────────────────────────────────────────────────────────────────┘

  All branches hit. ✅

  reverse — no branches. ✅ trivially.

  factorial — n<0, and the loop (i<=n).
  - n<0 true → n=-1. n<0 false → n=0,1,5.
  - Loop skipped → n=0,1 (2<=0 and 2<=1 both false). Loop runs → n=5.

  All branches hit. ✅

  isPalindrome — no if/loop; the only decision is the boolean result of .equals(...). Both outcomes (true/false) are hit by "racecar"/"hello", plus edge inputs. ✅

  fibonacciUpTo — n<0, and the while(a<=n) loop.
  - n<0 true → n=-1.
  - n<0 false → n=0,1,5.
  - Worth noting: for any valid (non-negative) n, the loop always runs at least once, since a starts at 0 and 0<=n whenever n>=0. There is no reachable "loop skipped" path for valid input — I didn't miss a
  case, that branch is genuinely unreachable.

  ✅

  charFrequency — no if; only a foreach loop. Branch-equivalent concern is iteration count: zero (""), one distinct char repeated ("aaa"), multiple distinct chars ("hello"). ✅

  isAnagram — no if/loop, just sort + Arrays.equals. Both outcomes (true/false) hit. ✅

  average — arr.length==0, and the foreach loop.
  - ==0 true → {} throws.
  - ==0 false → {1,2,3}, {5}, {-2,2}.
  - Same as fibonacciUpTo: once past the guard, the loop must run at least once (array is non-empty). No reachable zero-iteration path. ✅

  filterEvens — foreach loop, and n%2==0 inside it.

  ┌────────────────────────────────┬───────────────┐
  │             Branch             │  Covered by   │
  ├────────────────────────────────┼───────────────┤
  │ loop runs zero times           │ []            │
  ├────────────────────────────────┼───────────────┤
  │ n%2==0 true only               │ [2,4]         │
  ├────────────────────────────────┼───────────────┤
  │ n%2==0 false only              │ [1,3,5]       │
  ├────────────────────────────────┼───────────────┤
  │ both true and false in one run │ [1,2,3,4,5,6] │
  └────────────────────────────────┴───────────────┘

  ✅

  mostCommonWord — no if/loop branches in source, just a split + foreach + map update + Collections.max. The only real "path" variation is token count (one word vs. many) and the empty-string quirk, both
  tested. ✅

  So — are the tests sufficient?

  For branch coverage, yes: I traced every if and loop-entry condition by hand and each has a test hitting both outcomes (or I noted where an outcome is provably unreachable, like the loop-skip cases in
  fibonacciUpTo/average).

  But I want to be honest about the limits of this: I did this by reading the source and reasoning about it, which is exactly the kind of manual process that's easy to get subtly wrong (miscounting a loop
  iteration, missing a compound condition, etc.). "I traced it by hand and it looks right" is not the same guarantee as a tool measuring actual executed bytecode.

  Don't just take my analysis as gospel — verify it. Add JaCoCo (from the earlier build.gradle snippet) and run:

  ./gradlew test jacocoTestReport

  Then open build/reports/jacoco/test/html/index.html. For App.java, check specifically:
  - Any yellow highlighted if lines → means only one branch outcome was hit (I'd expect none here, but confirm).
  - Any red lines → dead code from the tests' perspective.

  If JaCoCo's branch-coverage percentage for App comes back at 100%, that confirms my manual analysis. If it doesn't, trust the tool over my reasoning — it's measuring reality, I was predicting it.

  One caveat for your assignment: even at 100% branch coverage, coverage doesn't prove correctness (recall from earlier — it only proves the code ran, not that the right assertions were made). I'd say these
  tests are solid on both fronts here since every test does assert a specific expected value/exception, not just "call it and see if it crashes."



   Please help me write a README.md file for our unit testing assignment.

    Team members: Nir Dor, Tommer Elankri, May Shehory

    The README should include:
    1. Team members names
    2. Project description - unit tests for App.java using JUnit 5 and Gradle
    3. Step by step instructions from cloning the repository to running the tests, for both Windows and Mac/Linux users
    4. A table of all the functions that were tested

    Repository URL: https://github.com/Afeka-DevTools/26b-10142-unittesting-nir_tommer_may.git

    # Unit Testing Assignment - Dev Tools Course

  ## Team Members
  - Nir Dor
  - Tommer Elankri
  - May Shehory

  ## Project Description
  This project contains unit tests for the `App.java` class, written using JUnit 5 and Gradle.

  ## How to Run the Tests

  ### Step 1 - Clone the repository
  ```bash
  git clone https://github.com/Afeka-DevTools/26b-10142-unittesting-nir_tommer_may.git
  cd 26b-10142-unittesting-nir_tommer_may

  Step 2 - Run the tests

  On Mac/Linux:
  ./gradlew test
  On Windows:
  gradlew.bat test

  Step 3 - View the results

  After running, open the test report:
  app/build/reports/tests/test/index.html

  Functions Tested

  ┌────────────────┬────────────────────────────────────────────────┐
  │    Function    │                  Description                   │
  ├────────────────┼────────────────────────────────────────────────┤
  │ add            │ Adds two integers                              │
  ├────────────────┼────────────────────────────────────────────────┤
  │ isPrime        │ Checks if a number is prime                    │
  ├────────────────┼────────────────────────────────────────────────┤
  │ reverse        │ Reverses a string                              │
  ├────────────────┼────────────────────────────────────────────────┤
  │ factorial      │ Calculates factorial, throws on negative input │
  ├────────────────┼────────────────────────────────────────────────┤
  │ isPalindrome   │ Checks if a string is a palindrome             │
  ├────────────────┼────────────────────────────────────────────────┤
  │ fibonacciUpTo  │ Returns Fibonacci numbers up to n              │
  ├────────────────┼────────────────────────────────────────────────┤
  │ charFrequency  │ Returns character frequency map                │
  ├────────────────┼────────────────────────────────────────────────┤
  │ isAnagram      │ Checks if two strings are anagrams             │
  ├────────────────┼────────────────────────────────────────────────┤
  │ average        │ Calculates average, throws on empty array      │
  ├────────────────┼────────────────────────────────────────────────┤
  │ filterEvens    │ Filters even numbers from a list               │
  ├────────────────┼────────────────────────────────────────────────┤
  │ mostCommonWord │ Finds the most common word in a text           │
  └────────────────┴────────────────────────────────────────────────┘