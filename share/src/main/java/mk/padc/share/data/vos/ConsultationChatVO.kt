package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.consultation_chat

@Entity(tableName = consultation_chat)
@IgnoreExtraProperties
class ConsultationChatVO(
    @PrimaryKey
    var id: String= "",
    var patient_info : PatientVO ? =null ,
    var doctor_info : DoctorVO ? = null,
    var case_summary : ArrayList<GeneralQuestionVO>? = arrayListOf()
)