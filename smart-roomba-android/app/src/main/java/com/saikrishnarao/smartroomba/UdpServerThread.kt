package com.saikrishnarao.smartroomba
import android.support.v7.preference.Preference
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.SocketException

class UdpServerThread(private var serverPort: Int, private val preference: Preference) : Thread() {
    private var running: Boolean = false
    var socket: DatagramSocket? = null

    fun setRunning(running: Boolean) {
        this.running = running
    }

    private fun updatePreferenceSummary(summary: String){
        preference.summary = summary
    }

    override fun run() {
        running = true

        try {
            socket = DatagramSocket(serverPort)

            while (running) {
                val buf = ByteArray(256)
                val packet = DatagramPacket(buf, buf.size)
                socket!!.receive(packet)

                if(packet.data.toString() == "SU"){
                    updatePreferenceSummary("Status: Connected")
                }
            }
        }
        catch (e: SocketException) {
            e.printStackTrace()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        finally {
            if (socket != null) {
                socket!!.close()
            }
        }
    }
}