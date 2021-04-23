package com.example.package_delivery_app_group_a

import androidx.fragment.app.Fragment

fun Fragment.addChildFragment(fragment: Fragment, frameId: Int) {

    val transaction = childFragmentManager.beginTransaction()
    transaction.replace(frameId, fragment).commit()
}