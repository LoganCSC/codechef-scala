package experiments.candymachine

import StateOnly.unit

/**
  * From https://github.com/fpinscala/fpinscala
  */
case class StateOnly[S](run: S => (S)) {

  def map[A, B](f: S => S): StateOnly[S] = flatMap(a => unit(f(a)))

  def flatMap(f: (S) => StateOnly[S]): StateOnly[S] = StateOnly(s => {
    val s1 = run(s)
    f(s).run(s1)
  })
}


object StateOnly {

  def unit[S, A](a: A): StateOnly[S] = StateOnly(s => s)

  /**
    * This implementation uses a loop internally and is the same recursion
    * pattern as a left fold. It is quite common with left folds to build
    * up a list in reverse order, then reverse it at the end.
    */
  def sequence[S, A](sas: List[StateOnly[S]]): StateOnly[S] = {
    def go(s: S, actions: List[StateOnly[S]]): S =
      actions match {
        case Nil => s
        case head :: tail => head.run(s) match { case (s2) => go(s2, tail) }
      }
    StateOnly((s: S) => go(s, sas))
  }

  def modify[S](f: S => S): StateOnly[S] = for {
    s <- get       // Gets the current state and assigns it to `s`.
    _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
  } yield s

  def get[S]: StateOnly[S] = StateOnly(s => s)
  def set[S](s: S): StateOnly[S] = StateOnly(_ => s)
}
