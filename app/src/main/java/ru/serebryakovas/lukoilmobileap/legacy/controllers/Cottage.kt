package ru.serebryakovas.lukoilmobileap.legacy.controllers

import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import ru.serebryakovas.lukoilmobileap.PlinBangActivity
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.screens.FluffyGamesFragment
import ru.serebryakovas.lukoilmobileap.plinbag.Plin

class Cottage : WebViewClient() {

    private val mail: String = PlinBangActivity.getInstance()?.getString(R.string.isolation).toString()
    private var sweater: String? = null

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        CookieManager.getInstance().flush()
        sweater = url
        val match = getMatch()
        polish(match)
    }

    private fun getMatch(): Match = when {
        sweater == null -> Match.PLINBAG_EMPTY
        sweater == mail -> Match.PLINBAG_SUCCESS
        sweater?.contains(mail) == true -> Match.PLINBAG_FAIL
        else -> Match.PLINBAG_FULL
    }

    private fun polish(match: Match) {
        when(match) {
            Match.PLINBAG_FULL -> setPlin()
            Match.PLINBAG_FAIL -> Unit
            Match.PLINBAG_EMPTY -> Unit
            Match.PLINBAG_SUCCESS -> movePlin()
        }
    }

    private fun setPlin() {
        PlinBangActivity.getInstance()?.plinBag?.insert(
            Plin(
                1,
                sweater ?: "",
            )
        )
    }

    private fun movePlin() {
        PlinBangActivity.getInstance()?.openFragment(
            fragment = FluffyGamesFragment.newInstance(),
            tag = FluffyGamesFragment.TAG
        )
    }

    private enum class Match {
        PLINBAG_FAIL,
        PLINBAG_SUCCESS,
        PLINBAG_FULL,
        PLINBAG_EMPTY
    }

}