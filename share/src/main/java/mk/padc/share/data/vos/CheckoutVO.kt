package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "checkout")
@IgnoreExtraProperties
class CheckoutVO(
    @PrimaryKey
    var ch_id: String= "",
    var patient_id: String ?= "",
    var doctor_id : String ?= "",
    var prescription_id : String ?= "",
    var delivery_address : String ?= "",
    var delivery_routine : String ?= ""
)