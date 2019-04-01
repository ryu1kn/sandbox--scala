package io.ryuichi

import argonaut.Argonaut._
import argonaut._
import org.scalatest.{FlatSpec, Matchers}

import scala._

class ArgonautSpec extends FlatSpec with Matchers {

  case class TestObject1(key: String)

  implicit def TestObjectDecodeJson: DecodeJson[TestObject1] = jdecode1L(TestObject1.apply)("key")

  private def decodeObject1(inputJson: String) = inputJson.decodeOption[TestObject1]

  "Argonaut" should "parse JSON object with single key" in {
    decodeObject1("""{"key":"value"}""") shouldEqual Some(TestObject1("value"))
  }

  "Argonaut" should "ignores the key the result object does not mention" in {
    decodeObject1("""{"key":"value","unknownKey":"unknownValue"}""") shouldEqual Some(TestObject1("value"))
  }



  case class TestObject2(key1: String, key2: Int)

  implicit def TestObject2DecodeJson: DecodeJson[TestObject2] = jdecode2L(TestObject2.apply)("key1", "key2")

  private def decodeObject2(inputJson: String) = inputJson.decodeOption[TestObject2]

  "Argonaut" should "parse JSON object with multiple keys" in {
    val input = """{"key1":"value1","key2":2}"""
    decodeObject2(input) shouldEqual Some(TestObject2("value1", 2))
  }



  private def decodeObjectList1(inputJson: String) = inputJson.decodeOption[List[TestObject1]]

  "Argonaut" should "automatically parse an array of the same object" in {
    val input = """[{"key":"value"}]"""
    decodeObjectList1(input) shouldEqual Some(List(TestObject1("value")))
  }



  "it" should "load a JSON file with Source and Argonaut" in {
    val source = io.Source.fromFile("src/test/resources/argonaut--1.json")

    try {
      val input = source.getLines.mkString("\n")
      decodeObject2(input) shouldEqual Some(TestObject2("KEY_1", 100))
    }
    finally source.close
  }



//  // https://stackoverflow.com/questions/40751321/argonaut-decoding-a-polymorphic-array/40753319#40753319
//  "Argonaut" should "parse an array who contains different type of elements" in {
//    val input = """[1, "A"]"""
//  }
}
