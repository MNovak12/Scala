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
    def pascal(c: Int, r: Int): Int = if (r == c | r == 0 | c == 0)
      1
    else
      pascal(c - 1, r) + pascal(c, r - 1)
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = true
//    {
//      if (chars.isEmpty)
//        true
//      if (chars.tail.isEmpty)
//        false
//      if (chars.head == "(")
//        false
//      else
//        false
//    }

//    def isParens(char: Char): Boolean = char == "(" | char == ")"
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money == 0 | coins.isEmpty)
        0
      if (money == 1 | coins.tail.isEmpty)
        1
      else
        countChange(money - 1, coins) + countChange(money, coins.tail)
    }
  }

// 1 and 1 = 1
// 2 and 1,2 = 2
// 3 and 1,2 = 2
// 3 and 1,2,3 = 3
// 3 and 1,3 = 2
// 4 and 1,2 = 3
// 4 and 1,2,3 = 4