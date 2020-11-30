package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.specialities

@Entity(tableName = specialities)
@IgnoreExtraProperties
class SpecialitiesVO(
    @PrimaryKey
    var id: String= "",
    var name: String = "",
    var photo: String = ""
)

