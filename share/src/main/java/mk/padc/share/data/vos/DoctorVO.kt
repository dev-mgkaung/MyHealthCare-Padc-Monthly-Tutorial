package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.doctors

@Entity(tableName = doctors)
@IgnoreExtraProperties
class DoctorVO(
    @PrimaryKey
    var id: String= "",
    var device_id: String = "",
    var name: String ? = "",
    var email: String ?= "",
    var phone: String ?= "",
    var photo: String? = "",
    var speciality: String ?= "",
    var degree : String? = "",
    var biography: String?= ""
)