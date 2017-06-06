package com.example.pitest.analyzers

import com.example.pitest.analyzers.impl.{BooleansAnalyzer, StringAnalyzer}
import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

/**
  * Created by ivan on 03.06.17.
  */
class StringAnalyzerSpec extends JUnitSuite with Matchers{
  @Test def compareSpec(): Unit = {
    StringAnalyzer("aa", "bb") shouldBe 4
  }
}
