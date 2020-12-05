package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.question_answer

@Entity(tableName = question_answer)
@IgnoreExtraProperties
class QuestionAnswerVO(
    @PrimaryKey
    var id: String= "",
    var question: String ?= "",
    var answer: String ?= ""
)