package com.developermaheshsofttechltd.diplomahelper.models

import java.io.Serializable

data class ChatModel(
    val chat: String = "",
    val profile: String = "",
    val userName: String = ""

) : Serializable
