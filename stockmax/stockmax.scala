import scala.io.Source._

object Solution {
  def main(args: Array[String]) {
    val input = stdin.mkString.split("\n")
    input.slice(1, input.length).grouped(2) foreach { testCase =>
      val prices = testCase(1).split(" ") map (_.toInt)
      println(maxProfit(prices))
    }
  }

  def maxProfit(prices: Array[Int]): Long = {
    // State kept during fold: (maxToRight, profits)
    val profits = prices.foldRight((prices.last, List[Long]())) {
      (item: Int, result: (Int, List[Long])) =>
        val maxToRight = result._1
        val profit = Seq(0, maxToRight - item).max
        (Seq(maxToRight, item).max, profit :: result._2)
    }

    profits._2.sum
  }
}
