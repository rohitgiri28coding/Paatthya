package com.coaching.paatthya.domain.model

data class Message(
    val senderId: String,
    val senderName: String,
    val messageText: String,
    val timestamp: Long = System.currentTimeMillis()
)


//fun sendMessage(batchId: String, senderId: String, senderName: String, text: String) {
//    val newMessage = Message(
//        senderId = senderId,
//        senderName = senderName,
//        messageText = text
//    )
//
//    firestore.collection("batches")
//        .document(batchId)
//        .collection("messages")
//        .add(newMessage)
//}

//fun listenForMessages(batchId: String, onNewMessage: (Message) -> Unit) {
//    firestore.collection("batches")
//        .document(batchId)
//        .collection("messages")
//        .orderBy("timestamp", Query.Direction.ASCENDING)
//        .addSnapshotListener { snapshots, error ->
//            if (error != null) return@addSnapshotListener
//            snapshots?.documentChanges?.forEach { change ->
//                val newMessage = change.document.toObject(Message::class.java)
//                onNewMessage(newMessage)
//            }
//        }
//}
