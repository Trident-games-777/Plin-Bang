package ru.serebryakovas.lukoilmobileap.legacy.controllers

import android.content.Context
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.legacy.cortex.Technique

class Overwhelm(
    private val context: Context
) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)
    private val packet = Technique.instance.packet.duck
    private val president = Technique.instance.president.duck
    private val bland = Technique.instance.bland.duck

    fun rare() {
        coroutineScope.launch {
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
            OneSignal.initWithContext(context)
            OneSignal.setAppId(
                PlinBangActivity.getInstance()
                    ?.getString(R.string.wall).toString()
            )
        }

    }

    fun copper(rotten: Any) {
        coroutineScope.launch {
            OneSignal.setExternalUserId(rotten.toString())
        }
    }

    fun multimedia(analyst: Any) {
        coroutineScope.launch {
            OneSignal.sendTag(packet + president + bland, analyst.toString())
        }
    }

}