package ru.serebryakovas.lukoilmobileap.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.databinding.FragmentFluffstormBinding
import ru.serebryakovas.lukoilmobileap.legacy.friendships.FriendshipController
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenCall
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand
import ru.serebryakovas.lukoilmobileap.legacy.cortex.openFragment

class FluffstormFragment : Fragment(), GardenCall {

    private var _binding: FragmentFluffstormBinding? = null
    private val binding get() = _binding!!

    private var friendshipController: FriendshipController? = null
    private var isHarmonyRestored = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFluffstormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        with(binding.lvBlock) {
            setViewColor(ContextCompat.getColor(requireContext(), R.color.apricot))
            setShadowColor(ContextCompat.getColor(requireContext(), R.color.east_bay))
            isShadow(true)
            startAnim(1000)
        }
        lifecycleScope.launch(Dispatchers.IO) {
            GardenLand.addGarden(Garden(
                AdvertisingIdClient.getAdvertisingIdInfo(requireContext()).id
            ))
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { }
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.plinImage.setImageResource(R.drawable.storm)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.plinImage.setImageResource(R.drawable.mischievous)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        GardenLand.unregisterCall(this)
        friendshipController?.unregister()
        friendshipController = null
        _binding = null
    }

    override fun onReceive(gardens: List<Garden>) {
        when(gardens.size) {
            2 -> {
                gardens.getOrNull(1)?.bed?.let {
                    (requireActivity() as? PlinBangActivity)?.overwhelm?.copper(it)
                }
            }
            6 -> {
                if (isHarmonyRestored) {
                    isHarmonyRestored = false
                    requireActivity().supportFragmentManager.openFragment(
                        fragment = HarmonyRestoredFragment
                            .newInstance(link = gardens.last().bed.toString()),
                        tag = HarmonyRestoredFragment.TAG
                    )
                }
            }
        }
    }

    private fun initFields() {
        (requireActivity() as? PlinBangActivity)?.overwhelm?.rare()
        friendshipController = FriendshipController()
        friendshipController?.register()
        GardenLand.registerCall(this)
    }

    companion object {
        const val TAG = "FluffstormFragment"

        @JvmStatic
        fun newInstance() = FluffstormFragment()
    }
}