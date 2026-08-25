package com.skillswap.ai

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.URL

@HiltAndroidApp
class SkillSwapApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        try {
            // meet.jit.si now requires authentication to create rooms, resulting in the "Waiting for moderator" screen.
            // Using a public community instance (meet.ffmuc.net) bypasses this requirement for development.
            val serverURL = URL("https://meet.ffmuc.net")
            val defaultOptions = JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .setFeatureFlag("welcomepage.enabled", false)
                .setFeatureFlag("prejoinpage.enabled", false)
                .build()
            JitsiMeet.setDefaultConferenceOptions(defaultOptions)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
