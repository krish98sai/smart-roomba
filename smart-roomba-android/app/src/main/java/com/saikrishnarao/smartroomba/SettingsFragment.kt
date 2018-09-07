package com.saikrishnarao.smartroomba

import android.os.Bundle
import android.os.Handler
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, str: String?) {
        addPreferencesFromResource(R.xml.preferences)

        val buttonRoombaTestConnection = findPreference("button_roomba_test_connection")
        val buttonHomeAssistantTestConnection = findPreference("button_home_assistant_test_connection")

        buttonRoombaTestConnection.setOnPreferenceClickListener {
            Log.d("CLICK", "THE FIRST PREFERENCE BUTTON WAS CLICKED")

            buttonRoombaTestConnection.summary = "Status: Testing..."
            val udpServerThread = UdpServerThread(2807, buttonRoombaTestConnection)
            buttonRoombaTestConnection.isEnabled = false
            udpServerThread.start()
            Handler().postDelayed({
                udpServerThread.setRunning(false)
                if (buttonRoombaTestConnection.summary == "Status: Testing..."){
                    buttonRoombaTestConnection.summary = "Status: Not Connected"
                    buttonRoombaTestConnection.isEnabled = true
                }
            }, 10000)
            RoombaControlAsyncTask().execute(RoombaControl.TEST_CONNECTION.byteArray)

            true
            /*
            Steps to make this happen:
            0. Set status to "Status: Testing"
            1. Start a UDP listening server on port 2807 on Android
            2. Send "TE" (test) UDP packet to Roomba
            3. Start 10 second timer to wait for response from Roomba
            4a. If unsuccessful:
                a. Nothing will come from the roomba
                b. Set status to "Status: Not Connected"
            4b. If successful:
                a. Take response from Roomba and check that packet says "SU" (success)
                b. Set status to "Status: Connected"
            5. Stop UDP server on Android
             */
        }

        buttonHomeAssistantTestConnection.setOnPreferenceClickListener {
            Log.d("CLICK", "THE SECOND PREFERENCE BUTTON WAS CLICKED")
            true
        }
    }
}