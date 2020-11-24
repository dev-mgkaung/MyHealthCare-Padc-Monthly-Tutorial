package mk.padc.share.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "patient")
@IgnoreExtraProperties
class PatientVO(
    var pt_id: String?= "",
    var name: String? = "",
    var photo: String? = "",
    var age: Int =0,
    var phone : String? = "",
    var address: String? ="",
    var gender: String?= "",
    var dateOfBirth: String?= ""
)