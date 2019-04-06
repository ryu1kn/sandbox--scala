package io.ryuichi

import java.io._

import io.ryuichi.helper.Specification

import scala.io.Source

class FileWriteSpec extends Specification {

  "PrintWriter" should {
    val fileName = "__test-with-printwriter.txt"
    val contents = "Hello, World"

    "Write to a file" in {
      val pw = new PrintWriter(new File(fileName))
      pw.write(contents)
      pw.close

      readFile(fileName) shouldEqual contents
    }
  }

  "FileWriter" should {
    val fileName = "__test-with-filewriter.txt"
    val contents = "Hello, World"

    "Write to a file" in {
      val file = new File(fileName)
      val bw = new BufferedWriter(new FileWriter(file))
      bw.write(contents)
      bw.close

      readFile(fileName) shouldEqual contents
    }
  }

  def readFile(file: String): String = Source.fromFile(file).mkString
}
