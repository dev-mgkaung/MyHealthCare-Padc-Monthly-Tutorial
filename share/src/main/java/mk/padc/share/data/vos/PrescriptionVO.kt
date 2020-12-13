package mk.padc.share.data.vos

import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.persistances.converters.RoutineConverter

@IgnoreExtraProperties
@TypeConverters(RoutineConverter::class)

class PrescriptionVO(
    var id: String= "",
    var count : String ="",
    var medicine : String = "",
    var price : String ="",
    var routineVO: RoutineVO
)

