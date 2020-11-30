package mk.padc.share.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class SpecialQuestionVO(
    var id: String= "",
    var question: String = ""
)