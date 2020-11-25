package mk.padc.share.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
class RoutineVO(
    var re_id: String= "",
    var name: String? = "",
    var times: Int =0
)