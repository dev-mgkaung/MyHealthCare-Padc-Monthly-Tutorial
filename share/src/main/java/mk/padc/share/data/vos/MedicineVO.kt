package mk.padc.share.data.vos
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.medicine

@IgnoreExtraProperties
@Entity(tableName = medicine)
class MedicineVO(
   @PrimaryKey
    var id: String= "",
    var name: String? = "",
    var price : Int ?=0,
    var isSelected : Boolean ?=false
)