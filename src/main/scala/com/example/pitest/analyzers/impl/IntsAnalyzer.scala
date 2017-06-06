package com.example.pitest.analyzers.impl

import com.example.pitest.analyzers.Analyzer

object IntsAnalyzer extends Analyzer[Int]{
  def apply(x: Int, y: Int): Int =
    if(x > y) x - y
    else if (x < y) y - x
    else 0
}
