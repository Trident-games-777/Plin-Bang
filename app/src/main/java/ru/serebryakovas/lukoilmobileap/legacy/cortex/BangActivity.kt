package ru.serebryakovas.lukoilmobileap.legacy.cortex

import androidx.appcompat.app.AppCompatActivity
import ru.serebryakovas.lukoilmobileap.legacy.friendships.DevoteStream

abstract class BangActivity : AppCompatActivity() {
    protected var devoteStream: DevoteStream? = null
}