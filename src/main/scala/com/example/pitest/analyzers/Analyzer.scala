package com.example.pitest.analyzers

/**
  * Created by ivan on 03.06.17.
  */
trait Analyzer[T] {
  def apply(x: T, y: T): Int
}
