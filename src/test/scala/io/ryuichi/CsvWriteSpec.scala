package io.ryuichi

import io.ryuichi.helper.Specification
import io.chrisdavenport.cormorant._
import io.chrisdavenport.cormorant.implicits._
import io.chrisdavenport.cormorant.generic.semiauto._
import io.chrisdavenport.cormorant.parser._
import cats.implicits._

class CsvWriteSpec extends Specification {
  case class Country(name: String, population: Int)

  private val countries = List(
    Country("japan", 127),
    Country("australia", 25)
  )
  private val countriesCsv =
    """name,population
      |japan,127
      |australia,25""".stripMargin

  "CSV encoding" should {
    implicit val lw: LabelledWrite[Country] = deriveLabelledWrite

    "Encode to CSV string" in {
      countries.writeComplete.print(Printer.default) shouldEqual countriesCsv
    }
  }

  "CSV decoding" should {
    implicit val lr: LabelledRead[Country] = deriveLabelledRead

    "Decode from CSV string" in {
      val decoded = parseComplete(countriesCsv).flatMap(_.readLabelled[Country].sequence)
      decoded shouldEqual Right(countries)
    }
  }
}
