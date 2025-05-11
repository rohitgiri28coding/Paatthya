package com.coaching.paatthya.domain.model

data class Assignment(
    val dateTimeStamp: String,
    val assignmentName: String,
    val assignmentLink: String,
){
    constructor():this("","","")
}
