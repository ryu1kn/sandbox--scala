package io.ryuichi

import io.ryuichi.helper.Specification
import io.chrisdavenport.cormorant._
import io.chrisdavenport.cormorant.implicits._
import io.chrisdavenport.cormorant.generic.semiauto._

class CsvWriteSpec extends Specification {
  case class Country(name: String, population: Int)

  implicit val lw: LabelledWrite[Country] = deriveLabelledWrite

  private val countries = List(
    Country("japan", 127),
    Country("australia", 25)
  )

  "Encode to CSV" in {
    countries.writeComplete.print(Printer.default) shouldEqual
      """name,population
        |japan,127
        |australia,25""".stripMargin
  }
}
