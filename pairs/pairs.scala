import scala.io.Source._
import scala.collection._

object Solution {
  def main(args: Array[String]) {
    val input = stdin.mkString.split("\n")

    val Array(n, k) = input(0).split(" ").map(_.toInt)
    var numbers     = immutable.Set.empty[Int]

    input(1).split(" ").map { s:String => numbers += s.toInt }

    var k_diffs = 0

    numbers.foreach { i:Int => if (numbers(i+k)) { k_diffs += 1 } }

    println(k_diffs)
  }
}