package mk.padc.share.data.vos

import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.SendByConverter

@IgnoreExtraProperties
@TypeConverters(SendByConverter::class)
class ChatMessageVO(
    var id : String ="",
    var messageText: String= "",
    var messageImage: String ? = "",
    var sendAt: String ? =null,
    var sendBy: SendBy ? =null
)


@IgnoreExtraProperties
class SendBy(
    var photo: String ? = "",
    var name: String ? = ""
)