package ru.serebryakovas.lukoilmobileap.screens

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.serebryakovas.lukoilmobileap.legacy.fluffygames.FluffyController
import ru.serebryakovas.lukoilmobileap.databinding.FragmentFluffyGamesBinding

class FluffyGamesFragment : Fragment() {

    private var _binding: FragmentFluffyGamesBinding? = null
    private val binding get() = _binding!!

    private var fluffyController: FluffyController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFluffyGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        fluffyController = FluffyController(binding)
        binding.ballPlay.setOnClickListener {
            binding.ballPlay.visibility = View.GONE
            binding.balanceStatus.visibility = View.GONE
            binding.status.visibility = View.GONE
            fluffyController?.start()
        }
    }

    override fun onPause() {
        super.onPause()
        fluffyController?.pauseGame()
    }

    override fun onResume() {
        super.onResume()
        fluffyController?.resumeGame()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        _binding = null
        fluffyController = null
    }

    companion object {
        const val TAG = "FluffyGamesFragment"

        @JvmStatic
        fun newInstance() = FluffyGamesFragment()
    }
}