package mk.padc.share.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class CaseSummaryVO(
    var question: String= "",
    var answer: String = ""
)