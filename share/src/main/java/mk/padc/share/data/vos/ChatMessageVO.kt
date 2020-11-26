package mk.padc.share.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ChatMessageVO(
    var sendAt: String= "",
    var sendBy: String = "",
    var messageText: String= "",
    var messageImage: String = ""
)