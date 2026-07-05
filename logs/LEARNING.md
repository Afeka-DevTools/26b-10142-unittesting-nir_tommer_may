I'm a software engineering student learning about unit testing in Java.
I'm working on a university assignment using JUnit 5 and Gradle.
Please explain to me:
1. What are unit tests and why are they important?
2. What is JUnit 5 and how does it work in Java?
3. What are the most common Assert functions in JUnit 5? Give examples for each.
4. What are edge cases and why do we need to test them?
Please explain clearly with simple code examples.

1. What are unit tests and why are they important?

  A unit test is a small, automated piece of code that checks whether one specific unit of your program (usually a single method or class) behaves correctly, in isolation from the rest of the system.

  Example: if you have a method add(2, 3), a unit test verifies that it returns 5 — without needing to run the whole application.

  Why they matter:
  - Catch bugs early — before the code reaches production or even a code reviewer.
  - Enable safe refactoring — you can change implementation details and instantly know if you broke something.
  - Serve as documentation — tests show how a method is supposed to be used.
  - Save time — automated tests run in seconds vs. manually clicking through an app.
  - Build confidence — a green test suite means you can ship changes without fear.

  2. What is JUnit 5 and how does it work in Java?

  JUnit 5 is the standard testing framework for Java. It's made of three main components:
  - JUnit Platform — launches testing frameworks on the JVM.
  - JUnit Jupiter — the new programming model and annotations (what you'll use daily).
  - JUnit Vintage — lets you run older JUnit 3/4 tests.

  How it works with Gradle:

  In build.gradle (Groovy) you need:

  dependencies {
      testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
  }

  test {
      useJUnitPlatform()
  }

  Then Gradle will automatically discover and run any test classes under src/test/java.

  Basic structure of a test:

  import org.junit.jupiter.api.Test;
  import static org.junit.jupiter.api.Assertions.*;

  class CalculatorTest {

      @Test
      void addTwoNumbers() {
          Calculator calc = new Calculator();
          int result = calc.add(2, 3);
          assertEquals(5, result);
      }
  }

  Key annotations:
  - @Test — marks a method as a test case.
  - @BeforeEach / @AfterEach — run before/after every test (good for setup/teardown).
  - @BeforeAll / @AfterAll — run once before/after all tests in the class (must be static).
  - @DisplayName("...") — gives a test a readable name in reports.
  - @Disabled — skips a test temporarily.

  class CalculatorTest {

      Calculator calc;

      @BeforeEach
      void setUp() {
          calc = new Calculator(); // fresh instance before each test
      }

      @Test
      @DisplayName("Adding two positive numbers")
      void addPositiveNumbers() {
          assertEquals(5, calc.add(2, 3));
      }
  }

  Run tests with:
  ./gradlew test

  3. Common Assert functions in JUnit 5

  All come from org.junit.jupiter.api.Assertions. Here are the ones you'll use constantly:

  assertEquals(expected, actual) — checks two values are equal.
  assertEquals(5, calc.add(2, 3));

  assertNotEquals(unexpected, actual) — checks values are NOT equal.
  assertNotEquals(0, calc.add(2, 3));

  assertTrue(condition) / assertFalse(condition) — checks a boolean expression.
  assertTrue(calc.isEven(4));
  assertFalse(calc.isEven(5));

  assertNull(object) / assertNotNull(object) — checks for null.
  assertNull(list.findById(999));   // not found
  assertNotNull(list.findById(1));  // found

  assertThrows(ExceptionClass.class, executable) — checks that code throws an exception.
  assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));

  assertArrayEquals(expectedArray, actualArray) — compares arrays element-by-element.
  assertArrayEquals(new int[]{1, 2, 3}, calc.sortedCopy(new int[]{3, 1, 2}));

  assertAll(...) — groups multiple assertions so ALL run even if one fails (great for reporting multiple failures at once).
  assertAll(
      () -> assertEquals(5, calc.add(2, 3)),
      () -> assertEquals(1, calc.subtract(3, 2))
  );

  assertEquals(expected, actual, delta) — for comparing doubles/floats with tolerance (floating point rounding).
  assertEquals(0.3, calc.add(0.1, 0.2), 0.0001);

  4. What are edge cases and why do we need to test them?

  An edge case is an input at the boundary of what's valid, or an unusual/extreme scenario that's easy to overlook — as opposed to a "normal" happy-path input.

  Why test them: most real bugs hide at boundaries, not in the middle of normal usage. A method might work perfectly for add(2, 3) but crash or misbehave for add(0, 0), negative numbers, or null.

  Common categories of edge cases:
  - Boundary values: 0, negative numbers, Integer.MAX_VALUE, empty string "".
  - Empty/null inputs: empty list, null object, empty array.
  - Division by zero or other invalid math operations.
  - Duplicate or single-element collections.
  - Very large inputs (performance/overflow concerns).

  Example — testing a divide method:

  class CalculatorTest {

      Calculator calc = new Calculator();

      @Test
      void divideNormalCase() {
          assertEquals(2, calc.divide(10, 5));
      }

      @Test
      void divideByZeroThrowsException() {
          // edge case: division by zero
          assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
      }

      @Test
      void divideNegativeNumbers() {
          // edge case: negative operands
          assertEquals(-2, calc.divide(-10, 5));
      }

      @Test
      void divideZeroNumerator() {
          // edge case: 0 as input, not just as divisor
          assertEquals(0, calc.divide(0, 5));
      }
  }

  If you only tested divide(10, 5), you'd never catch the crash on divide(10, 0). Good tests deliberately try to break the code, not just confirm the obvious case works.

  ---
  Want me to help set up an actual test file for your assignment, or review test code you've already written?

✻ Crunched for 24s

❯ Can you show me a complete example of a JUnit 5 test class for a factorial function?
The function signature is: public static int factorial(int n)
It throws IllegalArgumentException for negative numbers.
Show me how to test: normal cases, edge cases (n=0, n=1), and exceptions.

● Here's a complete, runnable example.

  Main class — src/main/java/Factorial.java:

  public class Factorial {

      public static int factorial(int n) {
          if (n < 0) {
              throw new IllegalArgumentException("n must not be negative: " + n);
          }
          int result = 1;
          for (int i = 2; i <= n; i++) {
              result *= i;
          }
          return result;
      }
  }

  Test class — src/test/java/FactorialTest.java:

  import org.junit.jupiter.api.DisplayName;
  import org.junit.jupiter.api.Nested;
  import org.junit.jupiter.api.Test;
  import org.junit.jupiter.params.ParameterizedTest;
  import org.junit.jupiter.params.provider.CsvSource;

  import static org.junit.jupiter.api.Assertions.*;

  class FactorialTest {

      @Nested
      @DisplayName("Normal cases")
      class NormalCases {

          @Test
          @DisplayName("factorial(5) = 120")
          void factorialOfFive() {
              assertEquals(120, Factorial.factorial(5));
          }

          @Test
          @DisplayName("factorial(3) = 6")
          void factorialOfThree() {
              assertEquals(6, Factorial.factorial(3));
          }

          @ParameterizedTest(name = "factorial({0}) = {1}")
          @CsvSource({
              "2, 2",
              "4, 24",
              "6, 720"
          })
          void factorialParameterized(int input, int expected) {
              assertEquals(expected, Factorial.factorial(input));
          }
      }

      @Nested
      @DisplayName("Edge cases")
      class EdgeCases {

          @Test
          @DisplayName("factorial(0) = 1, by mathematical definition")
          void factorialOfZero() {
              assertEquals(1, Factorial.factorial(0));
          }

          @Test
          @DisplayName("factorial(1) = 1, base case of recursion/loop")
          void factorialOfOne() {
              assertEquals(1, Factorial.factorial(1));
          }
      }

      @Nested
      @DisplayName("Exception cases")
      class ExceptionCases {

          @Test
          @DisplayName("Negative input throws IllegalArgumentException")
          void negativeThrowsException() {
              assertThrows(IllegalArgumentException.class,
                  () -> Factorial.factorial(-1));
          }

          @Test
          @DisplayName("Large negative input also throws")
          void largeNegativeThrowsException() {
              assertThrows(IllegalArgumentException.class,
                  () -> Factorial.factorial(-100));
          }

          @Test
          @DisplayName("Exception message contains the invalid value")
          void exceptionMessageIsInformative() {
              IllegalArgumentException ex = assertThrows(
                  IllegalArgumentException.class,
                  () -> Factorial.factorial(-5)
              );
              assertTrue(ex.getMessage().contains("-5"));
          }
      }
  }

  Notes on what's going on:

  - @Nested groups related tests into inner classes — purely organizational, makes the @DisplayName output read like a spec ("Normal cases > factorial(5) = 120"). Not required, but common practice and useful
  to know for your assignment.
  - @ParameterizedTest + @CsvSource lets you run the same test logic against multiple input/expected pairs without copy-pasting test methods. Requires an extra dependency (below) — if you haven't covered
  parameterized tests in class yet, feel free to skip that block and just write more @Test methods instead.
  - assertThrows returns the caught exception, so you can also assert on its message, as shown in exceptionMessageIsInformative.
  - I deliberately didn't test something like factorial(13) (overflow territory for int) — that's actually another real edge case. If your assignment expects overflow handling, let me know and I can show how
  to test/guard for it.

  Gradle dependency (only needed if you use @ParameterizedTest):

  dependencies {
      testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
      testImplementation 'org.junit.jupiter:junit-jupiter-params:5.10.0'
  }

  test {
      useJUnitPlatform()
  }

  Run with ./gradlew test.

✻ Worked for 19s

❯ How do I know if my tests are sufficient?
What is code coverage and how do I check if all code paths are tested?
What is the difference between line coverage and branch coverage?

● How do you know if your tests are "sufficient"?

  Coverage is one signal, but not the whole story. A few practical checklists:

  1. Every public method has at least one test.
  2. Every branch (if/else, loop, switch) is exercised in both directions — not just the "true" path.
  3. Every documented exception is tested (like your IllegalArgumentException for negative factorial input).
  4. Boundary values are tested, not just typical values (0, 1, negative, max values, empty collections).
  5. Mutation check (mental test): if you deliberately introduced a bug (e.g., changed < to <=, or i++ to i--), would at least one test fail? If you can imagine a bug that wouldn't be caught, you're missing a
  test.

  Coverage tools measure #1–2 mechanically. They can't measure #3–5 — that requires you to think like someone trying to break the code (there's a real technique called mutation testing for this, but it's
  likely beyond your assignment scope).

  What is code coverage?

  Code coverage measures how much of your source code gets executed while your test suite runs, expressed as a percentage. It answers "did any test run this code?" — not "does any test verify this code is
  correct." A test with no assertions still gives 100% coverage on the code it touches, while proving nothing. So coverage tells you what's untested, not what's well-tested.

  Line coverage vs. branch coverage

  Line coverage (aka statement coverage): what percentage of source lines were executed at least once.

  Branch coverage: what percentage of possible branches (both the true and false outcome of every conditional) were executed.

  Branch coverage is stricter and catches more. Example:

  public static String classify(int n) {
      if (n < 0) {
          return "negative";
      }
      return "non-negative";
  }

  If you only test classify(5):
  - Line coverage: high — most lines executed (the if condition check, and the return "non-negative" line). Looks deceptively good.
  - Branch coverage: 50% — the n < 0 branch's true path (return "negative") never ran.

  So you could get "80% line coverage" while entirely missing your exception-throwing branch (if (n < 0) throw ... in your Factorial example) if you never test with a negative number. That's exactly why in the
  previous example I made sure to test both factorial(-1) (branch taken) and factorial(5) (branch not taken) — that's branch coverage thinking, not just line coverage thinking.

  Rule of thumb: branch coverage is a much better signal of test quality than line coverage. If your tools/assignment let you choose which to report, prefer branch coverage.

  How to check coverage with Gradle

  Use JaCoCo (the standard Java coverage tool). Add to build.gradle:

  plugins {
      id 'java'
      id 'jacoco'
  }

  test {
      useJUnitPlatform()
      finalizedBy jacocoTestReport   // report generated right after tests run
  }

  jacocoTestReport {
      dependsOn test
      reports {
          xml.required = true
          html.required = true
      }
  }

  Run:
  ./gradlew test jacocoTestReport

  Then open build/reports/jacoco/test/html/index.html in a browser. It shows, per class/method:
  - Green lines/branches = covered
  - Red = never executed
  - Yellow = partially covered (e.g., only one branch of an if taken)

  For your Factorial class, if you look at the report and the if (n < 0) line is yellow or the throw line is red, that tells you immediately you're missing the negative-input test — even before running the
  tests to see if they pass.

  Bottom line: use JaCoCo to find code you haven't touched at all, but don't chase 100% as the goal — use it as a checklist to catch tests you forgot, then rely on the "would a bug slip through?" mental test
  for actual quality.