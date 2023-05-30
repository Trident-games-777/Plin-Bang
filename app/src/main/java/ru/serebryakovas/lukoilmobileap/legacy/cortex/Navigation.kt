package ru.serebryakovas.lukoilmobileap.legacy.cortex

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.serebryakovas.lukoilmobileap.R

fun FragmentManager.openFragment(
    fragment: Fragment,
    tag: String? = null,
) {
    val transaction = beginTransaction()
    transaction.replace(R.id.nav_host, fragment, tag)
    transaction.commit()
}