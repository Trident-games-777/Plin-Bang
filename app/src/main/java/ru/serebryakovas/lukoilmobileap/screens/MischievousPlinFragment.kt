package ru.serebryakovas.lukoilmobileap.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.databinding.FragmentMischievousPlinBinding
import ru.serebryakovas.lukoilmobileap.legacy.garden.Garden
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenCall
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand

class MischievousPlinFragment : Fragment(), GardenCall {

    private var _binding: FragmentMischievousPlinBinding? = null
    private val binding get() = _binding!!
    private var electronics = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        electronics = -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        electronics++
        _binding = FragmentMischievousPlinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        electronics++
        GardenLand.registerCall(this)
        with(binding.lvBlock) {
            setViewColor(ContextCompat.getColor(requireContext(), R.color.apricot))
            setShadowColor(ContextCompat.getColor(requireContext(), R.color.east_bay))
            isShadow(true)
            startAnim(1000)
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
        _binding = null
    }

    override fun onReceive(gardens: List<Garden>) {
        val activity = requireActivity() as PlinBangActivity
        val plin = activity.plinBag.get()
        when {
            gardens.getOrNull(0)?.bed == electronics.toString() -> {
                activity.openFragment(
                    fragment = FluffyGamesFragment.newInstance(),
                    tag = FluffyGamesFragment.TAG
                )
            }
            plin != null -> {
                activity.openFragment(
                    fragment = HarmonyRestoredFragment
                        .newInstance(link = plin.value_),
                    tag = HarmonyRestoredFragment.TAG
                )
            }
            else -> {
                activity.openFragment(
                    fragment = FluffstormFragment.newInstance(),
                    tag = FluffstormFragment.TAG
                )
            }
        }
    }

    companion object {
        const val TAG = "MischievousPlinFragment"

        @JvmStatic
        fun newInstance() = MischievousPlinFragment()
    }
}