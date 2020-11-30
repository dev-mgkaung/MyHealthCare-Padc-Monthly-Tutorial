package mk.padc.share.data.vos
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class MedicineVO(
    var id: String= "",
    var name: String? = "",
    var price : Int ?=0
)