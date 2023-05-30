package ru.serebryakovas.lukoilmobileap

import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.serebryakovas.lukoilmobileap.databinding.ActivityPlinBangBinding
import ru.serebryakovas.lukoilmobileap.screens.MischievousPlinFragment
import ru.serebryakovas.lukoilmobileap.legacy.cortex.BangActivity
import ru.serebryakovas.lukoilmobileap.legacy.cortex.Technique
import ru.serebryakovas.lukoilmobileap.legacy.controllers.Overwhelm
import ru.serebryakovas.lukoilmobileap.legacy.friendships.DevoteStream
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand
import ru.serebryakovas.lukoilmobileap.legacy.cortex.openFragment
import ru.serebryakovas.lukoilmobileap.plinbag.PlinBag

class PlinBangActivity : BangActivity() {

    private lateinit var binding: ActivityPlinBangBinding

    lateinit var plinBag: PlinBag
        private set

    lateinit var overwhelm: Overwhelm
        private set

    private val classify = Technique.instance.classify.duck
    private val study = Technique.instance.study.duck
    private val financial = Technique.instance.financial.duck
    private val society = Technique.instance.society.duck

    private val appeal = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents(),
        ::resultCallback
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlinBangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overwhelm = Overwhelm(this)
        plinBag = PlinBag(this)
        instance = this

        GardenLand.addGarden(Garden(
            Settings.Global.getString(
                contentResolver,
                Settings.Global.ADB_ENABLED
            )
        ))

        openFragment(
            fragment = MischievousPlinFragment.newInstance(),
            tag = MischievousPlinFragment.TAG
        )
    }

    fun openFragment(fragment: Fragment, tag: String? = null) {
         lifecycleScope.launch(Dispatchers.Main) {
             supportFragmentManager.openFragment(fragment, tag)
        }
    }

    fun chooseImage(devoteStream: DevoteStream) {
        this.devoteStream = devoteStream
        appeal.launch(classify + study + financial + society)
    }

    private fun resultCallback(data: List<Uri>) {
        devoteStream?.devote(data)
    }

    companion object {

        private var instance: PlinBangActivity? = null

        fun getInstance(): PlinBangActivity? {
            return instance
        }
    }
}