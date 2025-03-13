package com.coaching.srit.domain.model

import java.util.UUID

data class StudyMaterial(
    val materialId: String = UUID.randomUUID().toString(),
    val link: String, // PDF, Zoom link, Image URL, etc.
    val fileType: String, // "pdf", "image", "link"
    val timeStamp: Long = System.currentTimeMillis()
)


//fun getStudyMaterials(batchId: String) {
//    firestore.collection("batches")
//        .document(batchId)
//        .collection("studyMaterials")
//        .orderBy("timestamp", Query.Direction.DESCENDING) // Get latest first
//        .get()
//}

