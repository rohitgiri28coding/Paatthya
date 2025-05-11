package com.coaching.paatthya.domain.model

data class Notes(
    val dateTimeStamp: String,
    val notesName: String,
    val notesLink: String,
){
    constructor():this("","","")
}