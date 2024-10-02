package com.developermaheshsofttechltd.diplomahelper.views.activities

data class Message(
    val message: String,
    val sentBy: String
) {
    companion object {
        const val SEND_BY_BOT = "bot"
        const val SEND_BY_ME = "me"
    }
}
