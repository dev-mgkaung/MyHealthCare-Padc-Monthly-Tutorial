package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.consulation_request

@Entity(tableName = consulation_request)
@IgnoreExtraProperties
class ConsulationRequestVO(
    @PrimaryKey
    var id: String= "",
    var speciality : String ?= "",
    var date_time : Timestamp ?=null ,
    var patient_info : PatientVO ?=null ,
    var case_summary : ArrayList<GeneralQuestionVO> = arrayListOf()
)