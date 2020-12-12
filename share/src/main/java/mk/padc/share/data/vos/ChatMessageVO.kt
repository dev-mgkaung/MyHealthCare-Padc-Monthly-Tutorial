package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.SendByConverter
import mk.padc.share.utils.chat_message
import mk.padc.share.utils.checkout

@IgnoreExtraProperties
@Entity(tableName = chat_message)
@TypeConverters(SendByConverter::class)
class ChatMessageVO(
    @PrimaryKey
    var id : String ="",
    var messageText: String= "",
    var messageImage: String ? = "",
    var sendAt: String ? =null,
    var sendBy: SendBy ? =null,
    var type : String ?= ""
)


@IgnoreExtraProperties
class SendBy(
    var photo: String ? = "",
    var name: String ? = ""
)