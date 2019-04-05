package io.ryuichi

import java.util.Properties

import io.ryuichi.helper.Specification
import scala.collection.JavaConverters._

import scala.io.Source

class PropertyFileSpec extends Specification {

  "Property" should {
    val res = Source.fromResource("country.properties")
    val properties = new Properties
    properties.load(res.reader)
    val props = properties.asScala

    "be easy to read" in {
      props.get("japan") shouldEqual None
      props.get("japan.capital") shouldEqual Some("tokyo")
    }
  }
}
