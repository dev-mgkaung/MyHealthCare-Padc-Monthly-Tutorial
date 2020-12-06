package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.DoctorConverter
import mk.padc.share.utils.recent_doctors

@IgnoreExtraProperties
@Entity(tableName = recent_doctors)
class RecentDoctorVO(
    @PrimaryKey
    var rd_id: String= "",
    var id: String= "",
    var device_id: String ?= "",
    var name: String ? = "",
    var email: String ?= "",
    var phone: String ?= "",
    var photo: String? = "",
    var speciality: String ?= "",
    var specialityname : String ?= "",
    var degree : String? = "",
    var biography: String?= ""
)