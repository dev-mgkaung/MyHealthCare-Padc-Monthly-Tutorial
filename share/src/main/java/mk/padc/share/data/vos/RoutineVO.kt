package mk.padc.share.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class RoutineVO(
    var id: String= "",
    var amount: String? = "",
    var days: String ?="",
    var comment: String? = "",
    var repeat: String ?="",
    var quantity: String? = "",
    var times: String ?="",
)