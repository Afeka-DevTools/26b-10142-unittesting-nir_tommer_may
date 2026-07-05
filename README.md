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