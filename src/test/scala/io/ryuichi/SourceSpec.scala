package io.ryuichi

import org.scalatest._
import scala._

class SourceSpec extends FlatSpec with Matchers {

  "Source.fromFile" should "read a file" in {
    val path = "src/test/resources/source--1.txt"
    readFile(path) shouldEqual "Hello\nWorld"
  }

  def readFile(path: String): String = {
    val source = io.Source.fromFile(path)
    try source.getLines.mkString("\n")
    finally source.close
  }

}
