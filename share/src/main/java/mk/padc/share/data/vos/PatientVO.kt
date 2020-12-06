package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.patients

@Entity(tableName = patients)
@IgnoreExtraProperties
class PatientVO(
    @PrimaryKey
    var id: String= "",
    var device_id: String? = "",
    var name: String = "",
    var email: String = "",
    var photo: String? = "",
    var blood_type: String  = "",
    var blood_pressure: String? = "",
    var dateOfBirth: String?= "",
    var weight: String? = "",
    var height: String? = "",
    var allergic_reactions: String? = ""
)