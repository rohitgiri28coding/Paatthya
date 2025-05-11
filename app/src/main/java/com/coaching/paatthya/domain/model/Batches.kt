package com.coaching.paatthya.domain.model

import java.util.UUID

data class Batches(
    val batchId: String = UUID.randomUUID().toString(),
    val title: String = "",
    val startDate: String ="",
    val courseCompletionDate: String="",
    val imgUrl: String = "",
    val price: Double=0.0,
    val mrp: Double=0.0,
    val lectures: List<Lecture> = emptyList<Lecture>(),
    val notes: List<Notes> = emptyList<Notes>(),
    val assignment: List<Assignment> = emptyList<Assignment>(),
    val description: String="",
    val limitedTimeDeal: Boolean = false
){
    constructor() : this("","","","","", 0.0,0.0,emptyList(),emptyList(),emptyList(),"",false)
}