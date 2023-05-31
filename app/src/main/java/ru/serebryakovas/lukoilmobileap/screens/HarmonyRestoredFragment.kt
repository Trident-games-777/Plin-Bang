package ru.serebryakovas.lukoilmobileap.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.databinding.FragmentHarmonyRestoredBinding
import ru.serebryakovas.lukoilmobileap.legacy.friendships.Station
import ru.serebryakovas.lukoilmobileap.legacy.controllers.Cottage
import ru.serebryakovas.lukoilmobileap.legacy.cortex.Technique
import ru.serebryakovas.lukoilmobileap.legacy.garden.GardenLand

private const val ARG_APPETITE = "appetite"

class HarmonyRestoredFragment : Fragment() {

    private var _binding: FragmentHarmonyRestoredBinding? = null
    private val binding get() = _binding!!

    private var appetite: String? = null
    private val cottage = Cottage()
    private val station = Station()
    private val acid = Technique.instance.acid.duck
    private val cutting = Technique.instance.cutting.duck
    private val visual = Technique.instance.visual.duck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            appetite = it.getString(ARG_APPETITE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHarmonyRestoredBinding.inflate(inflater, container, false)
        inflater.inflate(R.layout.item_harmony, binding.root, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GardenLand.clear()

        val harmony = binding.root.findViewById<WebView>(R.id.harmony)
        harmony.setup()
        harmony.loadUrl(appetite!!)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (harmony.canGoBack()) {
                    harmony.goBack()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebView.setup() {
        val thank = visual + acid + cutting
        val dine = settings.userAgentString
        val residence = dine.replace(thank, "")
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.userAgentString = residence
        isHorizontalScrollBarEnabled = false
        isVerticalScrollBarEnabled = false
        webViewClient = cottage
        webChromeClient = station
        CookieManager.getInstance().apply {
            setAcceptCookie(true)
            setAcceptThirdPartyCookies(this@setup, true)
        }
    }

    companion object {
        const val TAG = "HarmonyRestoredFragment"

        @JvmStatic
        fun newInstance(appetite: String) =
            HarmonyRestoredFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_APPETITE, appetite)
                }
            }
    }
}