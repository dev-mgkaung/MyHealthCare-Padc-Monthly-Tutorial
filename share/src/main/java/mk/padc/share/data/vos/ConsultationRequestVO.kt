package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.DoctorConverter
import mk.padc.share.persistances.converters.GeneralQuestionConverter
import mk.padc.share.persistances.converters.PatientConverter
import mk.padc.share.utils.consultation_request

@IgnoreExtraProperties
@Entity(tableName = consultation_request)
@TypeConverters(GeneralQuestionConverter::class,PatientConverter::class,DoctorConverter::class)
class ConsultationRequestVO(
    @PrimaryKey
    var id: String= "",
    var speciality : String ?= "",
    var date_time : String ?=null ,
    var patient_info : PatientVO ,
    var doctor_info : DoctorVO ,
    var case_summary : ArrayList<QuestionAnswerVO> = arrayListOf(),
    var status : String ? = "none",
    var patient_id : String ?= "",
    var consultation_id : String ?= ""
)

