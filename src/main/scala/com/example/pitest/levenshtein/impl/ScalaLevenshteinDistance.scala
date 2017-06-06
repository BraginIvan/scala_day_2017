package com.example.pitest.levenshtein.impl

import com.example.pitest.levenshtein.LevenshteinDistance

/**
  * Created by ivan on 01.06.17.
  */
object ScalaLevenshteinDistance extends LevenshteinDistance{

  def unlimitedCompare(left: String, right: String): Int = {
    import scala.annotation.tailrec
    def impl(s: CharSequence, t: CharSequence, n: Int, m: Int) = {

      val p = new Array[Int](n + 1)
      val d = new Array[Int](n + 1)

      @tailrec def fillP(i: Int) {
        p(i) = i
        if (i < n)
          fillP(i + 1)
      }
      fillP(0)

      @tailrec def eachJ(j: Int, t_j: Char, d: Array[Int], p: Array[Int]): Int = {
        d(0) = j
        @tailrec def eachI(i: Int) {
          val a = d(i - 1) + 1
          val b = p(i) + 1
          d(i) = if (a < b) a
          else {
            val c = if (s.charAt(i - 1) == t_j) p(i - 1) else p(i - 1) + 1
            if (b < c) b else c
          }
          if (i < n)
            eachI(i + 1)
        }
        eachI(1)

        if (j < m)
          eachJ(j + 1, t.charAt(j), p, d)
        else
          d(n)
      }
      eachJ(1, t.charAt(0), d, p)
    }
    if(left == null || right == null)
      throw new IllegalArgumentException("Strings must not be null")
    val n = left.length
    val m = right.length
    if (n == 0) m
    else if (m == 0) n
    else {
      Math.min(impl(right, left, m, n), impl(left, right, n, m))
      //impl(t, s, m, n)
    }
  }

}
