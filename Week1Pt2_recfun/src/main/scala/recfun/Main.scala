package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (r == c | r == 0 | c == 0)
      1
    else
      pascal(c - 1, r) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def bal(chars: List[Char], opens: Int): Boolean = {
      if (chars.isEmpty && opens == 0) {
        true
      } else if ((chars.isEmpty && opens > 0) || (chars.head.toString == ")" && opens == 0)) {
        false
      } else if (chars.head.toString == ")" && opens > 0) {
        bal(chars.tail, opens - 1)
      } else if (chars.head.toString == "(") {
        bal(chars.tail, opens + 1)
      } else {
        bal(chars.tail, opens)
      }
    }
    bal(chars, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) {
      0
    } else if (money == 0) {
      1
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}