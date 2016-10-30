package common

class Watch() {

  private val start: Long = System.currentTimeMillis

  /**
    * Returns the elapsed time (in seconds) since this object was created.
    */
  def getElapsedTime: Double = {
    val now: Long = System.currentTimeMillis
    (now - start) / 1000.0
  }
}
