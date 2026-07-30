package com.skillswap.ai.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SkillSwapFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (!uid.isNullOrEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.updateFcmToken(uid, token)
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // FCM push notifications are handled automatically by the OS
        // when app is in background. When in foreground, handle here:
        remoteMessage.notification?.let { notification ->
            showNotification(
                title = notification.title ?: "SkillSwap",
                body = notification.body ?: ""
            )
        }
    }

    private fun showNotification(title: String, body: String) {
        // Android handles this automatically for data messages;
        // notification messages show in the system tray.
        // For foreground handling, integrate NotificationManager if needed.
    }
}
