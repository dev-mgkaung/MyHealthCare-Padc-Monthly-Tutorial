package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "doctor")
@IgnoreExtraProperties
class DoctorVO(
    @PrimaryKey
    var dr_id: String= "",
    var name: String = "",
    var email: String = "",
    var photo: String? = "",
    var age: Int =0,
    var degree : String? = "",
    var biography: String?= "",
    var address: String? =""
)