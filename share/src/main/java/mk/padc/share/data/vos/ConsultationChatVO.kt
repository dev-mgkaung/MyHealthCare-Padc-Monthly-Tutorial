package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.DoctorConverter
import mk.padc.share.persistances.converters.GeneralQuestionConverter
import mk.padc.share.persistances.converters.PatientConverter
import mk.padc.share.utils.consultation_chat

@Entity(tableName = consultation_chat)
@TypeConverters(GeneralQuestionConverter::class,PatientConverter::class,DoctorConverter::class)
@IgnoreExtraProperties
class ConsultationChatVO(
    @PrimaryKey
    var id: String= "",
    var patient_info : PatientVO ? =null ,
    var doctor_info : DoctorVO ? = null,
    var case_summary : ArrayList<GeneralQuestionVO>? = arrayListOf()
)