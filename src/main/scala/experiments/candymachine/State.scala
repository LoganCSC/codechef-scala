package experiments.candymachine

import State.unit


case class State[S](run: S => (S)) {

  def map[A, B](f: S => S): State[S] = flatMap(a => unit(f(a)))

  def flatMap(f: (S) => State[S]): State[S] = State(s => {
    val s1 = run(s)
    f(s).run(s1)
  })
}


object State {

  def unit[S, A](a: A): State[S] = State(s => s)

  def sequence[S, A](sas: List[State[S]]): State[S] = {
    def go(s: S, actions: List[State[S]]): S =
      actions match {
        case Nil => s
        case head :: tail => head.run(s) match { case (s2) => go(s2, tail) }
      }
    State((s: S) => go(s, sas))
  }

  def modify[S](f: S => S): State[S] = for {
    s <- get       // Gets the current state and assigns it to `s`.
    _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
  } yield s

  def get[S]: State[S] = State(s => s)
  def set[S](s: S): State[S] = State(_ => s)
}
