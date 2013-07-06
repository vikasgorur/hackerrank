import scala.io.Source._

object Solution {
  def main(args: Array[String]) {
    val Array(l1, l2) = stdin.mkString.split("\n")
    val Array(n, k)   = l1.split(" ").map(_.toInt)
    val costs         = l2.split(" ").map(_.toInt)

    // Do n / k rounds of buying
    val rounds = costs.sorted(Ordering[Int].reverse).grouped(k).zipWithIndex.map({
      case (group, i) => group.map(_ * (i+1)).sum
    })

    println(rounds.sum)
  }
}