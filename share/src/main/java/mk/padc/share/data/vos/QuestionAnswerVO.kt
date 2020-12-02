package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.general_questions

@Entity(tableName = general_questions)
@IgnoreExtraProperties
class QuestionAnswerVO(
    @PrimaryKey
    var id: String= "",
    var question: String ?= "",
    var answer: String ?= ""
)