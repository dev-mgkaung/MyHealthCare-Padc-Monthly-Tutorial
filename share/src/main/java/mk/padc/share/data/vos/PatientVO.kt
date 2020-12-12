package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.AddressConverter
import mk.padc.share.persistances.converters.RoutineConverter
import mk.padc.share.utils.patients

@Entity(tableName = patients)
@TypeConverters(AddressConverter::class)

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
    var comment: String? = "",
    var phone : String?= "",
    var address : ArrayList<AddressVO> = arrayListOf(),
)

class AddressVO
    (
    var id : String ="" ,
    var address : String ?= "",
    var default_address: Boolean ?= false
)