package com.skillswap.ai.ui.jitsi

import android.content.Context
import android.widget.Toast
import com.skillswap.ai.data.model.ExchangeRequest
import com.skillswap.ai.data.model.MeetingRequest
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo
import java.net.URL

object JitsiHelper {
    fun launchMeeting(
        context: Context,
        meeting: MeetingRequest,
        request: ExchangeRequest,
        currentUserId: String
    ) {
        val roomName = meeting.jitsiRoomName

        if (roomName.isBlank()) {
            Toast.makeText(
                context,
                "Unable to join meeting. Meeting room is not available.",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val isSender = request.senderId == currentUserId
        val myName = if (isSender) request.senderName else request.receiverName
        val myPic = if (isSender) request.senderProfilePic else request.receiverProfilePic

        val userInfo = JitsiMeetUserInfo()
        userInfo.displayName = myName

        val serverURL = URL("https://meet.ffmuc.net")
        val optionsBuilder = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverURL)
            .setRoom(roomName)
            .setUserInfo(userInfo)
            .setFeatureFlag("welcomepage.enabled", false)

        android.util.Log.d("JitsiMeet", "Launching Jitsi meeting for room: $roomName")
        try {
            JitsiMeetActivity.launch(context, optionsBuilder.build())
        } catch (e: Exception) {
            android.util.Log.e("JitsiMeet", "Failed to launch Jitsi meeting", e)
        }
    }
}
