package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.consulation_chat

@Entity(tableName = consulation_chat)
@IgnoreExtraProperties
class ConsulationChatVO(
    @PrimaryKey
    var id: String= "",
    var patient_info : PatientVO ? =null ,
    var doctor_info : DoctorVO ? = null,
    var case_summary : ArrayList<GeneralQuestionVO>? = arrayListOf()
)