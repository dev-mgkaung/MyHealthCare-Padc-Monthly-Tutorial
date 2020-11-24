package mk.padc.share.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "medicine")
@IgnoreExtraProperties
class MedicineVO(
    var me_id: String?= "",
    var name: String? = "",
    var photo: String? = "",
    var count: Int =0,
    var price : Int =0,
    var total_times : Int =0,
    var start_date: String? ="",
    var end_date: String?= "",
    var rountines : ArrayList<RoutineVO> = arrayListOf()
)