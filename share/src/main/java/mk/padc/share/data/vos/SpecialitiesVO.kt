package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties



@Entity(tableName = "specialites")
@IgnoreExtraProperties
class SpecialitiesVO(
    @PrimaryKey
    var sp_id: String= "",
    var name: String = "",
    var photo: String = "",
    var specialitiesCollectionVO: SpecialitiesCollectionVO
)

@IgnoreExtraProperties
class SpecialitiesCollectionVO(
    var specialitiesVO: ArrayList<SpecialitiesVO> = arrayListOf(),
    var generalQuestionVO: ArrayList<GeneralQuestionVO>  = arrayListOf(),
    var medicineVO: ArrayList<MedicineVO> = arrayListOf()
)