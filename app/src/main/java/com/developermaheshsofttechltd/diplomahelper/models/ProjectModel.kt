package com.developermaheshsofttechltd.diplomahelper.models

data class ProjectModel(
    val projectIcon: Int,
    val projectTitle: String,
    val projectDescription: String,
    val projectTotalPrice: String,
    val projectDiscount: String,
    val projectDiscountAnimation: String,
    val projectScreenShots: ArrayList<ScreenShotModel>
)
