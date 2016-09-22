package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
   test("string take") {
     val message = "hello, world"
     assert(message.take(5) == "hello")
   }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
   test("adding ints") {
     assert(1 + 2 === 3)
   }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements that are in both sets") {
    new TestSets {
      val s = intersect(s1, s1)
      val t = intersect(s1, s2)
      val r = intersect(s2, s2)
      assert(contains(s, 1), "Intersect 1")
      assert(contains(r, 2), "Intersect 1")
      assert(!contains(t, 1), "Intersect 2 should be empty")
    }
  }

  test("diff contains elements that are in the first set but not the second") {
    new TestSets {
      val r = diff(s1, s1)
      val s = diff(s1, s2)
      val t = diff(s2, s2)
      assert(!contains(r, 1), "Diff should be empty")
      assert(contains(s, 1), "Diff should only contain 1")
      assert(!contains(s, 2), "Diff should only contain 1")
      assert(!contains(t, 1), "Diff should be empty")
    }
  }

  test("return elements of set where function holds") {
    new TestSets {
      val r = filter(s1, x => x < 2)
      val s = filter(s2, x => x > 2)
      assert(contains(r, 1))
      assert(contains(r, -1000))
      assert(contains(s, 3))
      assert(contains(s, 1000))
    }
  }

  test("forall test - function holds for all elements") {
    new TestSets {
      val s = union(s1, s2)
      val r = forall(s, x => x == 1)
      val t = forall(s, x => x < 1001)
      val w = forall(s, x => x > -1001)
      assert(!r, "set contains elements that are not 1")
      assert(t, "set contains integers from -1000 to 1000")
      assert(w, "set contains integers from -1000 to 1000")
    }
  }

  test("exists test - function holds for at least one element") {
    new TestSets {
      val r = exists(s1, x => x == 1)
      val t = exists(s1, x => x > 1001)
      val w = exists(s1, x => x < -1001)
      assert(r, "set contains element that is 1")
      assert(!t, "set contains integers from -1000 to 1000")
      assert(!w, "set contains integers from -1000 to 1000")
    }
  }

  test("map test") {
    new TestSets {
      val r = map(s1, x => x)
      printSet(r)
    }
  }

}
