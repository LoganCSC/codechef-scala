package hackerrank.packetbuffer

import hackarrank.packetbuffer.{Packet, PacketBuffer}
import org.scalatest.FunSuite

class PacketBufferTest extends FunSuite {

  test("test gap handling") {
    val packetBuffer = new PacketBuffer()
    val packet0 = Packet(0, "foo")
    packetBuffer.addToBuffer(packet0)
    packetBuffer.addToBuffer(Packet(2, "bar"))

    assert(packetBuffer.readFromBuffer().get == packet0)
    assert(packetBuffer.readFromBuffer().isEmpty)
  }

  test("test duplicates handling") {
    val packetBuffer = new PacketBuffer()
    val packet0 = Packet(0, "foo")
    val packet1 = Packet(1, "bar")
    packetBuffer.addToBuffer(packet0)
    packetBuffer.addToBuffer(packet1)

    assert(packetBuffer.readFromBuffer().get == packet0)
    packetBuffer.addToBuffer(packet0)
    assert(packetBuffer.readFromBuffer().get == packet1)
    assert(packetBuffer.readFromBuffer().isEmpty)
  }

}
