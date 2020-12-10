package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.consulted_patient

@Entity(tableName = consulted_patient)
@IgnoreExtraProperties
class ConsultedPatientVO(
    @PrimaryKey
    var id: String= "",
    var patient_id: String ?= ""
)