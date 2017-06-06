package com.example.pitest.analyzers.impl

import com.example.pitest.analyzers.Analyzer
import com.example.pitest.levenshtein.LevenshteinService

case class StringContainer(s: String)
object StringAnalyzer extends Analyzer[String]{
  override def apply(x: String, y: String): Int ={
    val a = StringContainer(x)
    val b = StringContainer(y)
    (a.s + b.s).length
  }

}
