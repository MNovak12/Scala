import java.util.NoSuchElementException

object lists {
  trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
  }

  class Cons[T] (val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
  }

  class Nil[T] extends List[T] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  }

  def nth[T](n: Int, l: List[T]): T = {
    if (n < 0) {
      throw new IndexOutOfBoundsException
    } else if (n == 0) {
      return l.head
    }
    else {
      nth(n - 1, l.tail)
    }
  }

  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
  nth(2, list)
  nth(-1, list)
}
