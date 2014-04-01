import scala.io.Source._

object Solution {
  def main(args: Array[String]) {
    val input = stdin.mkString.split("\n")
    val numbers = input(1).split(" ").map(_.toInt)
    println(numbers.reduce(_ ^ _))
  }
}