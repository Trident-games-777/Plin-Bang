package ru.serebryakovas.lukoilmobileap.legacy.fluffygames

import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand

class Scale : AppsFlyerConversionListener {

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    fun sharp() {
        scope.launch {
            AppsFlyerLib.getInstance().apply {
                val plinBangActivity = PlinBangActivity.getInstance()!!
                init(
                    plinBangActivity.getString(R.string.tourist),
                    this@Scale,
                    plinBangActivity
                )
                start(plinBangActivity)
            }
        }
    }

    override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
        GardenLand.addGarden(Garden(data))
        composer()
    }
    override fun onConversionDataFail(p0: String?) {
        GardenLand.addGarden(Garden())
        composer()
    }
    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) { }
    override fun onAttributionFailure(p0: String?) { }

    private fun composer() {
        GardenLand.addGarden(Garden(
            PlinBangActivity.getInstance()
                ?.let { AppsFlyerLib.getInstance().getAppsFlyerUID(it) }
        ))
    }

}