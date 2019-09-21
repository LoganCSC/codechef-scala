package hackarrank.packetbuffer

class PacketBuffer() {
  private var nextSeqNum: Int = 0
  private var buffer: Map[Int, Packet] = Map()

  def addToBuffer(packet: Packet): Unit = {
    if (packet.seqNum >= nextSeqNum) {
      buffer += packet.seqNum -> packet
    }
  }

  def readFromBuffer(): Option[Packet] = {

    val packet = buffer.get(nextSeqNum)
    if (packet.isDefined) {
      buffer -= packet.get.seqNum
      nextSeqNum += 1
    }
    packet
  }
}
