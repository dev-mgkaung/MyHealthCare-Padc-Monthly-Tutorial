package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.RoutineConverter
import mk.padc.share.utils.medicine
import mk.padc.share.utils.prescription

@IgnoreExtraProperties
@TypeConverters(RoutineConverter::class)
@Entity(tableName = prescription)
class PrescriptionVO(
        @PrimaryKey
    var id: String= "",
    var count : String ="",
    var medicine : String = "",
    var price : String ="",
    var chat_id : String ="",
    var routineVO: RoutineVO
)

