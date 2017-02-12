package usaco.buylower

import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class ResultExtractorTest extends FunSuite {

  test("one element") {
    val cache: Array[List[List[Int]]] = Array(List(List(1)))
    assertResult("1 1") {
      new ResultExtractor(cache).getResult
    }
  }

  test("2 element increasing") {
    val cache: Array[List[List[Int]]] = Array(
      List(List(1)),
      List(List(2))
    )
    assertResult("1 2") {
      new ResultExtractor(cache).getResult
    }
  }

  test("2 element decreasing") {
    val cache: Array[List[List[Int]]] = Array(
      List(List(2), List(1)),
      List(List(1))
    )
    assertResult("2 1") {
      new ResultExtractor(cache).getResult
    }
  }

  test("example from problem") {
    val cache: Array[List[List[Int]]] = Array(
      List(List(68), List(64, 67), List(62)),
      List(List(69), List(68), List(64, 67), List(62)),
      List(List(54)),
      List(List(64), List(62)),
      List(List(68), List(64, 67), List(62)),
      List(List(64), List(62)),
      List(List(70), List(67), List(62)),
      List(List(67), List(62)),
      List(List(78), List(62)),
      List(List(62)),
      List(List(98), List(87)),
      List(List(87))
    )
    assertResult("4 2") {
      new ResultExtractor(cache).getResult
    }
  }

  test("my tough case") {
    val cache: Array[List[List[Int]]] = Array(
      List(List(10), List(7, 8)),
      List(List(20), List(15), List(7, 8)),
      List(List(30), List(25), List(15), List(7, 8)),
      List(List(25), List(15), List(7, 8)),
      List(List(35), List(15, 27), List(7, 8)),
      List(List(15), List(7, 8)),
      List(List(55), List(45), List(40), List(35), List(7, 8)),
      List(List(45), List(40), List(35), List(7, 8)),
      List(List(27), List(7, 8)),
      List(List(40), List(35), List(7, 8)),
      List(List(60), List(35), List(7, 8)),
      List(List(35), List(7, 8)),
      List(List(7)),
      List(List(8))
    )
    assertResult("5 2") {
      new ResultExtractor(cache).getResult
    }
  }

  test("made up: more than one longest") {
    val cache: Array[List[List[Int]]] = Array(
      List(List(10), List(7, 8), List(3)),
      List(List(20, 21), List(15, 16), List(7, 8, 9)),
      List(List(25), List(15), List(7, 8)),
      List(List(15), List(7, 8)),
      List(List(8))
    )
    assertResult("3 16") {
      new ResultExtractor(cache).getResult
    }
  }

  test("requires BigInt") {
    val cache: Array[List[List[Int]]] = Array(
      List(List(10), List(7, 8), List(3)),
      Range(0, 30).map(x => List(1, 1, 1) ).toList,
      List(List(8))
    )
    assertResult("30 205891132094649") {
      new ResultExtractor(cache).getResult
    }
  }
}