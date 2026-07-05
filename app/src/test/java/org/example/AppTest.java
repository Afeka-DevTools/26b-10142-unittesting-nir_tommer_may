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
