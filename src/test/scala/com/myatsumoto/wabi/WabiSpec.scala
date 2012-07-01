import com.myatsumoto.wabi.Wabi
import org.scalatest.FunSuite
import scala.collection.mutable._

class TestSuite extends FunSuite {
  test("rank") {
    val expected = HashMap[String, Array[Int]](
      "a" -> Array(1, 1, 1, 1, 1, 1, 2, 2, 2, 3),
      "b" -> Array(0, 1, 1, 1, 2, 3, 3, 4, 4, 4),
      "c" -> Array(0, 0, 1, 2, 2, 2, 2, 2, 3, 3)
    )
    val str  = "abccbbabca"
    val wabi = new Wabi("abccbbabca")
    for (index <- 0 until str.size) {
      assert(expected("a")(index) == wabi.rank(index, "a"))
      assert(expected("b")(index) == wabi.rank(index, "b"))
      assert(expected("c")(index) == wabi.rank(index, "c"))
    }
  }

  test("select") {
    val expected = HashMap[String, Array[Int]](
      "a" -> Array(0, 6, 9),
      "b" -> Array(1, 4, 5),
      "c" -> Array(2, 3, 8)
    )
    val wabi = new Wabi("abccbbabca")
    for (index <- 0 until 3) {
      assert(expected("a")(index) == wabi.select(index, "a"))
      assert(expected("b")(index) == wabi.select(index, "b"))
      assert(expected("c")(index) == wabi.select(index, "c"))
    }
  }
}

