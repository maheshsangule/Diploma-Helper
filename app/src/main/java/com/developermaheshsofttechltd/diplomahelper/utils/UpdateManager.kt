package com.developermaheshsofttechltd.diplomahelper.utils

import android.app.Activity

class UpdateManager(context: Activity?) {
    init {
        requireNotNull(context) { "context cannot be null" }
    }
}
