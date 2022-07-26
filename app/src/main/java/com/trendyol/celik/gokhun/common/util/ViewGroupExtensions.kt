package com.trendyol.celik.gokhun.common.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> ViewGroup.inflate(
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false,
): T {
    return viewBindingFactory.invoke(
        LayoutInflater.from(context),
        this,
        attachToParent
    )
}