package com.saikrishnarao.smartroomba

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_drive_roomba.*


class DriveRoombaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drive_roomba, container, false)
    }

    override fun onStart() {
        super.onStart()

        button_up.setOnTouchListener(RepeatingOnTouchListener(0, 200, View.OnClickListener {
            Log.d("BUTTON", "UP")
            RoombaControlAsyncTask().execute(RoombaControl.UP.byteArray)
        }))
        button_down.setOnTouchListener(RepeatingOnTouchListener(0, 200, View.OnClickListener {
            Log.d("BUTTON", "DOWN")
            RoombaControlAsyncTask().execute(RoombaControl.DOWN.byteArray)
        }))
        button_left.setOnTouchListener(RepeatingOnTouchListener(0, 200, View.OnClickListener {
            Log.d("BUTTON", "LEFT")
            RoombaControlAsyncTask().execute(RoombaControl.LEFT.byteArray)
        }))
        button_right.setOnTouchListener(RepeatingOnTouchListener(0, 200, View.OnClickListener {
            Log.d("BUTTON", "RIGHT")
            RoombaControlAsyncTask().execute(RoombaControl.RIGHT.byteArray)
        }))
    }
}
