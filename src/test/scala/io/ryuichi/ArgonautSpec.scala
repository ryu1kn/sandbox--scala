package io.ryuichi

import argonaut._, Argonaut._
import scala._
import org.scalatest.{FlatSpec, Matchers}

class ArgonautSpec extends FlatSpec with Matchers {

  case class TestObject1(key: String)

  implicit def TestObjectDecodeJson: DecodeJson[TestObject1] = jdecode1L(TestObject1.apply)("key")

  def decodeObject1(inputJson: String) = inputJson.decodeOption[TestObject1]

  "Argonaut" should "parse JSON object with single key" in {
    val input = """{"key":"value"}"""
    decodeObject1(input) match {
      case Some(TestObject1(key)) => key shouldEqual "value"
    }
  }

  "Argonaut" should "ignores the key the result object does not mention" in {
    val input = """{"key":"value","unknownKey":"unknownValue"}"""
    decodeObject1(input) match {
      case Some(TestObject1(key)) => key shouldEqual "value"
    }
  }



  case class TestObject2(key1: String, key2: Int)

  implicit def TestObject2DecodeJson: DecodeJson[TestObject2] = jdecode2L(TestObject2.apply)("key1", "key2")

  def decodeObject2(inputJson: String) = inputJson.decodeOption[TestObject2]

  "Argonaut" should "parse JSON object with multiple keys" in {
    val input = """{"key1":"value1","key2":2}"""
    decodeObject2(input) match {
      case Some(TestObject2(_, key2)) => key2 shouldEqual 2
    }
  }



  def decodeObjectList1(inputJson: String) = inputJson.decodeOption[List[TestObject1]]

  "Argonaut" should "automatically parse an array of the same object" in {
    val input = """[{"key":"value"}]"""
    decodeObjectList1(input) match {
      case Some(List(TestObject1(key))) => key shouldEqual "value"
    }
  }



  "it" should "load a JSON file with Source and Argonaut" in {
    val source = io.Source.fromFile("src/test/resources/argonaut--1.json")

    try {
      val input = source.getLines.mkString("\n")
      decodeObject2(input) match {
        case Some(TestObject2(_, key2)) => key2 shouldEqual 100
      }
    }
    finally source.close
  }



//  // https://stackoverflow.com/questions/40751321/argonaut-decoding-a-polymorphic-array/40753319#40753319
//  "Argonaut" should "parse an array who contains different type of elements" in {
//    val input = """[1, "A"]"""
//  }
}
