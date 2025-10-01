package com.sample.noti.core.common.extensions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.sample.noti.core.common.BuildConfig

fun Context.openPlayStore() {
    val intent = Intent(Intent.ACTION_VIEW, "market://details?id=${BuildConfig.PACKAGE_NAME}".toUri())
    startActivity(intent)
}
