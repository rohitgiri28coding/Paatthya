package com.coaching.paatthya.domain.model

data class Lecture(
    val dateTimeStamp: String,
    val lectureLink: String,
    val lectureName: String,
    val lectureDetail: String,
    val isYTVideo: Boolean
){
    constructor():this("","","","",false)
}