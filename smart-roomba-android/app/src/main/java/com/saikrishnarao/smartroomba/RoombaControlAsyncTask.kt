package com.saikrishnarao.smartroomba

import android.os.AsyncTask
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class RoombaControlAsyncTask : AsyncTask<ByteArray, Int, Long>() {

    private val mRoombaUdpSocket = DatagramSocket()
    private val roombaHostAddress = InetAddress.getByName("10.0.0.60")
    private val roombaHostPort = 2807

    override fun doInBackground(vararg bytes: ByteArray?): Long {
        mRoombaUdpSocket.connect(roombaHostAddress, roombaHostPort)

        mRoombaUdpSocket.send(DatagramPacket(bytes[0], 0, 2))

        mRoombaUdpSocket.disconnect()
        return 0
    }

}