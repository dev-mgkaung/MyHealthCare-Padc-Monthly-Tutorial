package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.*
import mk.padc.share.utils.checkout

@IgnoreExtraProperties
@Entity(tableName = checkout)
@TypeConverters(DeliveryRoutineConverter::class,
    PatientConverter::class,
    DoctorConverter::class,
    PrescriptionConverter::class,
    DeliveryAddressConverter::class)

class CheckoutVO(
    @PrimaryKey
    var id: String= "",
    var delivery_address: DeliveryAddressVO ?= null,
    var total_price : Int? =0,
    var patientVO: PatientVO ?=null,
    var doctorVO: DoctorVO ?=null,
    var delivery_routine : DeliveryRoutineVO ?= null,
    var prescription : ArrayList<PrescriptionVO> = arrayListOf()

)

@IgnoreExtraProperties
class DeliveryRoutineVO(
    var id: String= "",
    var delivery_date : String ?= ""
)

@IgnoreExtraProperties
class DeliveryAddressVO(
    var state: String= "",
    var township : String ?= "",
    var ful_address : String ?= "",
)