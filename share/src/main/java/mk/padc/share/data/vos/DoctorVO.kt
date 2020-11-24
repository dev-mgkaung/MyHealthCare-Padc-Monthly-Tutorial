package mk.padc.share.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "doctor")
@IgnoreExtraProperties
class DoctorVO(
    var dr_id: String?= "",
    var name: String? = "",
    var photo: String? = "",
    var age: Int =0,
    var degree : String? = "",
    var biography: String?= "",
    var address: String? =""
)