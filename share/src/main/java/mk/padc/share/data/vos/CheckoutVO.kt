package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.DeliveryRoutineConverter
import mk.padc.share.persistances.converters.DoctorConverter
import mk.padc.share.persistances.converters.PatientConverter
import mk.padc.share.persistances.converters.PrescriptionConverter
import mk.padc.share.utils.checkout

@IgnoreExtraProperties
@Entity(tableName = checkout)
@TypeConverters(DeliveryRoutineConverter::class,
    PatientConverter::class,
    DoctorConverter::class,
    PrescriptionConverter::class)
class CheckoutVO(
    @PrimaryKey
    var id: String= "",
    var delivery_address: String ?= "",
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