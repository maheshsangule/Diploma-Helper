package com.developermaheshsofttechltd.diplomahelper.views.activities

data class QuestionsList(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String,
    var userSelectedOption: String = ""
)
