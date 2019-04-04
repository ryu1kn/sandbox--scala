package io.ryuichi

import java.text.SimpleDateFormat

import org.scalatest.{Matchers, WordSpec}

class DateTimeSpec extends WordSpec with Matchers {
  "Date" should {
    val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    import formatter.parse

    "get epoch time" in {
      parse("2019-04-05 19:30:08Z").getTime shouldEqual 1554453008000L
    }

    "be comparable" in {
      parse("2018-04-05 19:30:08Z") should be < parse("2019-04-05 19:30:08Z")
      parse("2019-04-05 19:30:08Z") shouldEqual parse("2019-04-05 19:30:08Z")
    }
  }
}
