package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "consulation_chat")
@IgnoreExtraProperties
class ConsulationChatVO(
    @PrimaryKey
    var cc_id: String= "",
    var patient_id: String = "",
    var doctor_id: String = "",
    var chatMessageVO: ArrayList<ChatMessageVO> = arrayListOf(),
    var caseSummaryVO:  ArrayList<CaseSummaryVO> = arrayListOf(),
)