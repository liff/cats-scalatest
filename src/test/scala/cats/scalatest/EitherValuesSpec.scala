package cats.scalatest

import org.scalatest.exceptions.TestFailedException
import scala.util.{ Either, Right, Left }

class EitherValuesSpec extends TestBase {
  import EitherValues._

  "value on Either" should {
    "return the value inside a Right if that Either is Right" in {
      val r: String Either String = Right(thisRecord)
      r.value should ===(thisRecord)
    }

    "should throw TestFailedException if that Either is a left " in {
      val r: String Either String = Left(thisTobacconist)
      val caught =
        intercept[TestFailedException] {
          r.value should ===(thisRecord)
        }
      caught.failedCodeLineNumber.value should equal(thisLineNumber - 2)
      caught.failedCodeFileName.value should be("EitherValuesSpec.scala")
    }
  }

  "leftValue on Either" should {
    "return the value if it's left" in {
      val r = Left(thisRecord)
      r.leftValue should ===(thisRecord)
    }

    "throw TestFailedException if the Either is right" in {
      val r = Right(thisRecord)
      val caught = intercept[TestFailedException] {
        r.leftValue
      }
      caught.failedCodeLineNumber.value should equal(thisLineNumber - 2)
      caught.failedCodeFileName.value should be("EitherValuesSpec.scala")
    }
  }
}

