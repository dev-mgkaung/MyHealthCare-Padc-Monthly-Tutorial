package mk.padc.share.data.vos

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ChatMessageVO(
    var messageText: String= "",
    var messageImage: String ? = "",
    var sendAt: Timestamp ? =null,
    var sendBy: SendBy ? =null
)


@IgnoreExtraProperties
class SendBy(
    var photo: String ? = "",
    var name: String ? = ""
)