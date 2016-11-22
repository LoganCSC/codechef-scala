package experiments.candymachine

import org.scalatest.FunSuite

/**
  * Created by barry on 11/22/2016.
  */
class CandyMachineTest extends FunSuite {

  test("test buy one candy from locked machine)") {

    val machine = Machine(locked = true, candies = 10, coins = 0)

    assertResult(((1, 9), Machine(locked = true, 9, 1))) {
      CandyMachine.simulateMachine(List(Coin, Turn)).run(machine)
    }

  }


  test("test buy 2 candies from unlocked machine)") {

    val machine = Machine(locked = false, 10, 0)

    assertResult(((2, 8), Machine(locked = false, 8, 2))) {
      CandyMachine.simulateMachine(List(Turn, Coin, Turn, Coin, Coin)).run(machine)
    }
  }

  test("test buy 3 candies from unlocked machine)") {

    val machine = Machine(locked = false, 10, 0)

    assertResult(((2, 7), Machine(locked = true, 7, 2))) {
      CandyMachine.simulateMachine(List(Turn, Coin, Turn, Coin, Coin, Coin, Coin, Turn)).run(machine)
    }
  }

  test("test insert coin into locked machine)") {

    val machine = Machine(locked = true, 10, 0)

    assertResult(((1, 10), Machine(locked = false, 10, 1))) {
      CandyMachine.simulateMachine(List(Coin)).run(machine)
    }
  }

  test("test insert 3 coins into locked machine. Second two are ignored.)") {

    val machine = Machine(locked = true, 10, 0)

    assertResult(((1, 10), Machine(locked = false, 10, 1))) {
      CandyMachine.simulateMachine(List(Coin, Coin, Coin)).run(machine)
    }
  }

  test("test turn knob on unlocked machine.)") {

    val machine = Machine(locked = false, 10, 0)

    assertResult(((0, 9), Machine(locked = true, 9, 0))) {
      CandyMachine.simulateMachine(List(Turn, Turn, Turn)).run(machine)
    }
  }

  test("test input ignored when out of candy.)") {

    val machine = Machine(locked = false, 0, 5)

    assertResult(((5, 0), Machine(locked = false, 0, 5))) {
      CandyMachine.simulateMachine(List(Turn, Coin, Turn, Turn, Coin, Turn)).run(machine)
    }
  }
}