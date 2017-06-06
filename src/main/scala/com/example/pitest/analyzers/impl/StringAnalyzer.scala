package com.example.pitest.analyzers.impl

import com.example.pitest.analyzers.Analyzer
import com.example.pitest.levenshtein.LevenshteinService

object StringAnalyzer extends Analyzer[String]{
  override def apply(x: String, y: String): Int = LevenshteinService.apache(x, y)
}
