package com.example.pitest.analyzers

import com.example.pitest.analyzers.impl.IntsAnalyzer
import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

/**
  * Created by ivan on 03.06.17.
  */
class IntsAnalyzerSpec extends JUnitSuite with Matchers{
  @Test def compareSpec(): Unit ={
    IntsAnalyzer(1,2) should be > 0
    IntsAnalyzer(2,1) should be > 0
    IntsAnalyzer(1,1) shouldBe 0
  }
}
