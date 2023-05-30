package ru.serebryakovas.lukoilmobileap.legacy.friendships

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import ru.serebryakovas.lukoilmobileap.PlinBangActivity

class Station : WebChromeClient(), DevoteStream {

    private var bracket: ValueCallback<Array<Uri>>? = null

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        bracket = filePathCallback
        return PlinBangActivity.getInstance()?.let {
            it.chooseImage(this)
            true
        } ?: false
    }

    override fun devote(gardens: Any) {
        if (gardens is List<*>) {
            bracket?.onReceiveValue(
                gardens.filterIsInstance<Uri>()
                    .toTypedArray()
            )
        }
    }
}

interface DevoteStream {
    fun devote(gardens: Any)
}