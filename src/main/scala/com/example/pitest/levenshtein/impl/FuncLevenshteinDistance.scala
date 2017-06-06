package com.example.pitest.levenshtein.impl

/**
  * Created by ivan on 03.06.17.
  */
object FuncLevenshteinDistance {
  def unlimitedCompare(left: String, right: String): Int = {
    if(left == null || right == null)
      throw new IllegalArgumentException("Strings must not be null")
    import scala.math._
    def minimum(i1: Int, i2: Int, i3: Int) = min(min(i1, i2), i3)
    val dist = Array.tabulate(right.length + 1, left.length + 1) { (j, i) => if (j == 0) i else if (i == 0) j else 0 }
    for (j <- 1 to right.length; i <- 1 to left.length)
      dist(j)(i) = if (right(j - 1) == left(i - 1)) dist(j - 1)(i - 1)
      else minimum(dist(j - 1)(i) + 1, dist(j)(i - 1) + 1, dist(j - 1)(i - 1) + 1)
    dist(right.length)(left.length)
  }
}
