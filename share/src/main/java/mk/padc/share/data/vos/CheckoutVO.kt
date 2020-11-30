package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.checkout

@Entity(tableName = checkout)
@IgnoreExtraProperties
class CheckoutVO(
    @PrimaryKey
    var id: String= "",
    var delivery_address: String ?= "",
    var total_price : Int? =0,
    var patientVO: PatientVO ?=null,
    var doctorVO: DoctorVO ?=null,
    var delivery_routine : DeliveryRoutineVO ?= null,
    var prescriptionVO: ArrayList<PrescriptionVO> ?= arrayListOf()
)

@IgnoreExtraProperties
class DeliveryRoutineVO(
    var id: String= "",
    var delivery_date : String ?= ""
)