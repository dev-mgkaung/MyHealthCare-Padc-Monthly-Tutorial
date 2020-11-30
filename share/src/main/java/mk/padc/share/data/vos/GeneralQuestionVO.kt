package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class GeneralQuestionVO(
    var id: String= "",
    var question: String ?= "",
    var answer: String ?= ""
)