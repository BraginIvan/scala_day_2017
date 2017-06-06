package com.example.pitest.analyzers.impl

import com.example.pitest.analyzers.Analyzer

object BooleansAnalyzer extends Analyzer[Boolean]{
  def apply(x: Boolean, y: Boolean): Int =
    if(x && y) 2
    else if (x || y) 1
    else 0
}