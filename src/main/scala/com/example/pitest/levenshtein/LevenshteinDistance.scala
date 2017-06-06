package com.example.pitest.levenshtein

/**
  * Created by ivan on 06.06.17.
  */
abstract class LevenshteinDistance {
  def unlimitedCompare(left: String, right: String): Int
}
