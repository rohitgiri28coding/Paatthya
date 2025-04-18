package com.coaching.paatthya.ui.viewmodel.home

data class Lecture(
    val dateTimeStamp: String,
    val lectureLink: String,
    val lectureName: String,
    val lectureDetail: String,
    val isYTVideo: Boolean
){
    constructor():this("","","","",false)
}
data class Notes(
    val dateTimeStamp: String,
    val notesName: String,
    val notesLink: String,
){
    constructor():this("","","")
}
data class Assignment(
    val dateTimeStamp: String,
    val assignmentName: String,
    val assignmentLink: String,
){
    constructor():this("","","")
}
