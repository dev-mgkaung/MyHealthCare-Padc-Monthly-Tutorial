package mk.padc.share.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class PrescriptionVO(
    var id: String= "",
    var count : Int =0,
    var medicine : String = "",
    var price : Int =0
)

