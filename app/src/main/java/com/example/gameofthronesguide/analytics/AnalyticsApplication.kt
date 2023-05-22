package com.example.gameofthronesguide.analytics

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import dagger.hilt.android.HiltAndroidApp


/**
 * This is a subclass of [Application] used to provide shared objects for this app, such as
 * the [Tracker].
 */
@HiltAndroidApp
class AnalyticsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        sAnalytics = GoogleAnalytics.getInstance(this)
    }// To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG

    /**
     * Gets the default [Tracker] for this [Application].
     * @return tracker
     */
    @get:Synchronized
    val defaultTracker: Tracker?
        get() {
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            if (sTracker == null) {
                sTracker = sAnalytics?.newTracker("5241566553")

            }
            return sTracker
        }

    companion object {
        private var sAnalytics: GoogleAnalytics? = null
        private var sTracker: Tracker? = null
    }
}