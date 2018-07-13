package com.saikrishnarao.smartroomba

import android.os.Handler
import android.view.MotionEvent
import android.view.View



class RepeatingOnTouchListener(private val initialInterval: Long, val regularInterval: Long, val clickListener: View.OnClickListener) : View.OnTouchListener {

    private var handler: Handler? = null
    private var downView: View? = null
    private val mAction = object : Runnable {
        override fun run() {
            clickListener.onClick(downView)
            handler!!.postDelayed(this, regularInterval)
        }
    }
    private val enableButton = Runnable {
        downView!!.isEnabled = true
        handler = null
        downView = null
    }

    override fun onTouch(view: View?, motion: MotionEvent?): Boolean {
        when (motion!!.action){
            MotionEvent.ACTION_DOWN -> {
                if (handler != null)
                    return true
                handler = Handler()
                downView = view
                downView!!.isPressed = true

                handler!!.postDelayed(mAction, initialInterval)
                return true
            }

            MotionEvent.ACTION_UP -> {
                if (handler == null)
                    return true
                handler!!.removeCallbacks(mAction)
                downView!!.isEnabled = false
                downView!!.isPressed = false
                handler!!.postDelayed(enableButton, regularInterval)
                return true
            }

            MotionEvent.ACTION_CANCEL -> {
                if (handler == null)
                    return true
                handler!!.removeCallbacks(mAction)
                downView!!.isPressed = false
                handler = null
                downView = null
                return true
            }
        }
        return false
    }
}