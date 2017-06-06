package com.example.pitest.analyzers

import com.example.pitest.analyzers.impl.BooleansAnalyzer
import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

/**
  * Created by ivan on 03.06.17.
  */
class BooleansAnalyzerSpec extends JUnitSuite with Matchers{
  @Test def compareSpec(): Unit = {
    test1()
    test2()
  }

  private def test1() = {
    BooleansAnalyzer(true, true) should be > 0
    BooleansAnalyzer(false, true) should be > 0
    BooleansAnalyzer(false, false) shouldBe 0
  }

  private def test2() = {
    BooleansAnalyzer(true, true) shouldBe 2
    BooleansAnalyzer(false, true) shouldBe 1
    BooleansAnalyzer(false, false) shouldBe 0
  }
}
