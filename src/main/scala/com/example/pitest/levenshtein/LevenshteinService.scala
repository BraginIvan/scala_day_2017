package com.example.pitest.levenshtein

import com.example.pitest.levenshtein.impl.{ApacheLevenshteinDistance, FuncLevenshteinDistance, ScalaLevenshteinDistance}

/**
  * Created by ivan on 03.06.17.
  */
object LevenshteinService {
  val apache = new ApacheLevenshteinDistance()
  def apache(x: String, y: String): Int = apache.unlimitedCompare(x, y)
  def scala(x: String, y: String): Int = ScalaLevenshteinDistance.unlimitedCompare(x, y)
  def scalaFunc(x: String, y: String): Int = FuncLevenshteinDistance.unlimitedCompare(x, y)
  def notCovered(x: String, y: String) = 0
}
