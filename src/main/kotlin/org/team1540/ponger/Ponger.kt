@file:JvmName("Ponger")

package org.team1540.ponger

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.nio.ByteBuffer
import java.nio.ByteOrder

fun main(args: Array<String>) {
    val recvSock = DatagramSocket(args.getOrNull(0)?.toIntOrNull() ?: 1540)
    val sendSock = DatagramSocket()

    recvSock.use {
        val receivePacket = DatagramPacket(ByteArray(1), 1)

        while (true) {
            recvSock.receive(receivePacket)

            println("Received packet from ${receivePacket.socketAddress}")

            val data = ByteBuffer.allocate(Long.SIZE_BYTES)
                .putLong(System.currentTimeMillis())
                .array()

            val sendPacket = DatagramPacket(data, data.size, receivePacket.socketAddress)
            sendSock.send(sendPacket)
        }
    }
}
