package ru.serebryakovas.lukoilmobileap.legacy.friendships

import android.app.Activity
import com.facebook.applinks.AppLinkData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand

class Aluminium : AppLinkData.CompletionHandler {

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    fun rescue(activity: Activity?) {
        scope.launch {
            AppLinkData.fetchDeferredAppLinkData(
                activity,
                this@Aluminium
            )
        }
    }

    override fun onDeferredAppLinkDataFetched(appLinkData: AppLinkData?) {
        GardenLand.addGarden(Garden(appLinkData?.targetUri?.toString()))
    }

}