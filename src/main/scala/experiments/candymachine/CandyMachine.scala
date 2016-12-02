package experiments.candymachine

import State._


sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

/**
  * A stateful candy machine.
  * from https://github.com/fpinscala/fpinscala
  */
object CandyMachine {

  def update = (i: Input, s: Machine) =>
    (i, s) match {
      case (_, Machine(_, 0, _)) => s     // ignore everything when no candy
      case (Coin, Machine(false, _, _)) => s   // ignore coins when unlocked
      case (Turn, Machine(true, _, _)) => s    // ignore turns when locked
      case (Coin, Machine(true, candy, coin)) => Machine(locked = false, candy, coin + 1)
      case (Turn, Machine(false, candy, coin)) => Machine(locked = true, candy - 1, coin)
    }

  /**
    * Called like this:
    *   val newState = CandyMachine.simulate(List(Turn, Coin)).run(initialMachineState)
    * @param inputs list of Inputs like List(Coin, Turn, Coin)
    * @return the new state of the candy machine
    */
  def simulate(inputs: List[Input]): State[Machine] = for {
    m <- sequence(inputs.map(i => modify(update(i, _: Machine))))
  } yield m
}

