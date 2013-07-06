import scala.io.Source._
import scala.collection._

// Scala only provides a SortedSet, while we need a SortedList
class SortedList {
  var set        = mutable.SortedSet.empty[Long]
  var duplicates = mutable.Map.empty[Long, Int]

  def apply(x: Long): Boolean = { return set(x) }

  def add(x: Long) {
    val added = set.add(x)

    if (!added) { duplicates(x) = duplicates.getOrElse(x, 0) + 1 }
  }

  def remove(x: Long) {
    if (duplicates contains x) {
      duplicates(x) -= 1

      if (duplicates(x) == 0) { duplicates.remove(x) }
    } else {
      set.remove(x)
    }
  }

  def head: Long = set.head
  def last: Long = set.last

  def isEmpty: Boolean = set.isEmpty

  override def toString() : String = {
    "SortedList(" + set.map { elem => 
      if (duplicates contains elem) { 
        Array.fill(duplicates(elem) + 1) { elem.toString }.mkString(", ") 
      } else { 
        elem.toString 
      }
    }.mkString(", ") + ")"
  }
}

object Solution {
  // Total number of Long's we are currently holding in both lists
  var (leftN, rightN) = (0, 0)

  val left  = new SortedList
  val right = new SortedList

  var notFound = false

  def rebalance() {
    if (leftN < rightN) {
      val rightHead = right.head
      right.remove(rightHead); rightN -= 1
      left.add(rightHead); leftN += 1
    }

    if (leftN == rightN + 2) {
      val leftLast = left.last
      left.remove(leftLast); leftN  -= 1
      right.add(leftLast);   rightN += 1
    }
  }

  def addNumber(x: Long) {
    if (left.isEmpty || x <= left.last) {
      left.add(x); leftN += 1
    } else {
      right.add(x); rightN += 1
    }

    notFound = false
    rebalance()
  }

  def removeNumber(x: Long) {
    if (left(x)) {
      left.remove(x); leftN -= 1; notFound = false

    } else if (right(x)) {
      right.remove(x); rightN -= 1; notFound = false

    } else {
      notFound = true
    }

    rebalance()
  }

  def printMedian() {
    if (notFound || left.isEmpty) {
      println("Wrong!")
    } else if (leftN == rightN) {
      val median = (left.last + right.head) / 2
      if ((left.last + right.head) % 2 == 0) {
        println("%d" format median)
      } else {
        println("%d.5" format median)
      }
    } else {
      println(left.last)
    }
  }

  def main(args: Array[String]) {
    var firstLine = true

    for (line <- stdin.getLines) {
      if (firstLine) {
        firstLine = false     // Skip the first line
      } else {
        var Array(op, num) = line.split(" ")

        op match {
          case "a" => addNumber(num.toLong)
          case "r" => removeNumber(num.toLong)
        }

        printMedian()
      }
    }
  }
}