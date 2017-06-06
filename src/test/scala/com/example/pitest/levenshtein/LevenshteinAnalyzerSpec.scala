package com.example.pitest.levenshtein

import org.junit.Assert.assertTrue
import org.junit.Test
import org.scalacheck.Gen
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

import scala.util.{Random, Try}

/**
  * Created by ivan on 03.06.17.
  */
class LevenshteinAnalyzerSpec extends JUnitSuite with Matchers{

  private val distanceFunctions: Seq[(String, String) => Int] = Seq(
    LevenshteinService.apache(_, _),
    LevenshteinService.scala(_, _),
    LevenshteinService.scalaFunc(_, _)
  )

  private def applyAll(x: String, y: String): Seq[Int] = distanceFunctions.map(_.apply(x, y))
  private def applyAllTry(x: String, y: String): Seq[Try[Int]] =
    for(distanceFunction <- distanceFunctions) yield Try(distanceFunction).map(_.apply(x, y))


  @Test def shouldCalculateLevenshtein() {
    applyAll("слон", "кот").foreach(_ shouldBe 3)
    applyAllTry(null, "abc").foreach(x => an [IllegalArgumentException] should be thrownBy x.get)
    applyAllTry("abc", null).foreach(x => an [IllegalArgumentException] should be thrownBy x.get)
    applyAll("abc", "abc").foreach(_ shouldBe 0)
    applyAll("abcd", "abc").foreach(_ shouldBe 1)
    applyAll("abc", "abcd").foreach(_ shouldBe 1)
    applyAll("", "").foreach(_ shouldBe 0)
    applyAll("abcdefg", "abczefg").foreach(_ shouldBe 1)
    applyAll("aaafaaa", "aaaaaa").foreach(_ shouldBe 1)
    applyAll("fj", "").foreach(_ shouldBe 2)
    applyAll("", "a").foreach(_ shouldBe 1)
    applyAll("abc", "def").foreach(_ shouldBe 3)
  }

  @Test def shouldCalculateLevenshteinWithScalacheck() {
    import org.scalacheck.Prop.forAll

    val scalaCheckTest = forAll(Gen.alphaLowerStr, Gen.alphaChar) { (word, char) =>
      val wordPlusChar = word + char
      val wordPlusCharWithUpdate = {if(wordPlusChar.head.toString == "a") "b" else "a" } + wordPlusChar.drop(1)
      val randomIndex = Random.nextInt(wordPlusChar.length)
      val wordPlusCharWithUpdate2 = wordPlusChar.take(randomIndex) + {if(wordPlusChar.charAt(randomIndex).toString == "a") "b" else "a" } + wordPlusChar.drop(randomIndex + 1)
      applyAll(word, word).forall(_ == 0) &&
        applyAll(word, wordPlusChar).forall(_ == 1) &&
        applyAll(wordPlusChar, wordPlusChar.drop(1)).forall(_ == 1) &&
        applyAll(word, "").forall(_ == word.length) &&
        applyAll("", word).forall(_ == word.length) &&
        applyAll(wordPlusChar, wordPlusCharWithUpdate).forall(_ == 1) &&
        applyAll(wordPlusChar, wordPlusCharWithUpdate2).forall(_ == 1)
    }

    import org.scalacheck.Test._
    import org.scalacheck.{Test => SchkTest}
    assertTrue(SchkTest.check(Parameters.default, scalaCheckTest).passed)

   /* val scalaCheckMinTest = forAll(Gen.alphaLowerStr, Gen.alphaChar) { (word, char) =>
      applyAll(word, char + word).forall(_ == 1)
    }
    assertTrue(SchkTest.check(Parameters.default, scalaCheckMinTest).passed)*/

  }

}
