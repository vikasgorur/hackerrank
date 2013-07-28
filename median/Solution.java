import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
  static PriorityQueue<Long> max = new PriorityQueue<Long>(10, Collections.reverseOrder());
  static PriorityQueue<Long> min = new PriorityQueue<Long>();

  static boolean notFound = false;

  static void rebalance() {
    if (max.size() < min.size()) {
      Long x = min.poll();
      max.add(x);
    }

    if (max.size() == min.size() + 2) {
      Long x = max.poll();
      min.add(x);
    }
  }

  static void addNumber(Long x) {
    if (max.isEmpty() || x.longValue() <= max.peek().longValue()) {
      max.add(x);
    } else {
      min.add(x);
    }

    rebalance();
  }

  static void removeNumber(Long x) {
    if (max.contains(x)) {
      max.remove(x);
    } else if (min.contains(x)) {
      min.remove(x);
    } else {
      notFound = true;
    }

    rebalance();
  }

  static void printMedian() {
    if (notFound || max.isEmpty()) {
      System.out.println("Wrong!");
    
    } else if (max.size() == min.size()) {
      double median = (max.peek().longValue() + min.peek().longValue()) / 2.0;
      
      if ((max.peek().longValue() + min.peek().longValue()) % 2 == 0) {
        System.out.format("%.0f\n", median);
      } else {
        System.out.format("%.1f\n", median);
      }
    
    } else {
      System.out.println(max.peek().longValue());
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String firstLine = sc.nextLine();

    while (sc.hasNextLine()) {
      String line     = sc.nextLine();
      String pieces[] = line.split("\\s+");

      notFound = false;

      if (pieces[0].equals("a")) {
        addNumber(new Long(pieces[1]));
      } else if (pieces[0].equals("r")) {
        removeNumber(new Long(pieces[1]));
      }

      printMedian();
    }
  }
}