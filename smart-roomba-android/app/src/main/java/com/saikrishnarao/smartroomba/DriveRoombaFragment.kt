package com.saikrishnarao.smartroomba

import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_drive_roomba.*
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable


class DriveRoombaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drive_roomba, container, false)
    }
}
