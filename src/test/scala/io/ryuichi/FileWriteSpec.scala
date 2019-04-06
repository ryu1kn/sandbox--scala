package io.ryuichi

import java.io._

import io.ryuichi.helper.Specification

import scala.io.Source

class FileWriteSpec extends Specification {
  val contents = "Hello, World"

  "PrintWriter" should {
    val fileName = "__test-with-printwriter.txt"

    "Write to a file" in {
      val pw = new PrintWriter(new File(fileName))
      pw.write(contents)
      pw.close

      readFile(fileName) shouldEqual contents
    }
  }

  "FileWriter" should {
    val fileName = "__test-with-filewriter.txt"

    "Write to a file" in {
      val file = new File(fileName)
      val bw = new BufferedWriter(new FileWriter(file))
      bw.write(contents)
      bw.close

      readFile(fileName) shouldEqual contents
    }
  }

  "Input/OutputStream" should {
    val fileName = "__test-with-fileoutputstream.txt"

    // convert Stream[Char] -> to InputStream? instead of using ByteArrayInputStream?
    val inputStream = new ByteArrayInputStream(contents.getBytes)
    val outputStream = new FileOutputStream(fileName)

    "write to a file" in {
      inputStream.transferTo(outputStream)

      readFile(fileName) shouldEqual contents
    }
  }

  "Stream" should {
    val fileName = "__test-with-scala-stream.txt"
    val stream = Source.fromString(contents).toStream
    val outputStream = new BufferedOutputStream(new FileOutputStream(fileName))

    "write to a file" in {
      try stream.map(_.toUpper).foreach(outputStream.write(_))
      finally outputStream.close()

      readFile(fileName) shouldEqual contents.toUpperCase
    }
  }

  "Stream - Control flow and use separate buffer to hold data" should {
    val fileName = "__test-with-scala-stream-2.txt"
    val inputStream = new ByteArrayInputStream(contents.getBytes)
    val outputStream = new BufferedOutputStream(new FileOutputStream(fileName))

    "write to a file" in {
      val bytes = new Array[Byte](1024)

      Stream
        .continually(inputStream.read(bytes))
        .takeWhile(-1 !=)
        .foreach(length => outputStream.write(bytes, 0, length))
      outputStream.close()

      readFile(fileName) shouldEqual contents.toUpperCase
    }
  }

  def readFile(file: String): String = Source.fromFile(file).mkString
}
