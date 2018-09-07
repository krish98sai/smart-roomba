package com.saikrishnarao.smartroomba

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private val basicRoombaFragment = BasicRoombaFragment()
    private val driveRoombaFragment = DriveRoombaFragment()
    private val settingsFragment = SettingsFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentTransaction.replace(R.id.container, basicRoombaFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_drive -> {
                fragmentTransaction.replace(R.id.container, driveRoombaFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                fragmentTransaction.replace(R.id.container, settingsFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, basicRoombaFragment).commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
