import scala.io.Source._

import java.util.PriorityQueue
import java.util.Collections
import java.util.Comparator

object Solution {
  val maxComp: Comparator[Long] = Collections.reverseOrder[Long]
  var max: PriorityQueue[Long] = new PriorityQueue(10, maxComp)
  var min: PriorityQueue[Long] = new PriorityQueue

  var notFound = false

  def rebalance() {
    if (max.size < min.size) {
      val x = min.poll
      max.add(x)
    }

    if (max.size == min.size + 2) {
      val x = max.poll
      min.add(x)
    }
  }

  def addNumber(x: Long) {
    if (max.isEmpty || x <= max.peek) {
      max.add(x)
    } else {
      min.add(x)
    }

    rebalance()
  }

  def removeNumber(x: Long) {
    if (max.contains(x)) {
      max.remove(x)
    } else if (min.contains(x)) {
      min.remove(x)
    } else {
      notFound = true
    }

    rebalance()
  }

  def printMedian() {
    if (notFound || max.isEmpty) {
      println("Wrong!")
    
    } else if (max.size == min.size) {
      val median = (max.peek + min.peek) / 2.0
      
      if ((max.peek + min.peek) % 2 == 0) {
        println("%.0f" format median)
      } else {
        println("%.1f" format median)
      }
    
    } else {
      println(max.peek)
    }
  }

  def main(args: Array[String]) {
    var firstLine = true

    for (line <- stdin.getLines) {
      if (firstLine) {
        firstLine = false     // Skip the first line
      } else {
        var Array(op, num) = line.split(" ")

        notFound = false

        op match {
          case "a" => addNumber(num.toLong)
          case "r" => removeNumber(num.toLong)
        }

        printMedian()
      }
    }
  }
}