package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.special_questions


@Entity(tableName = special_questions)
@IgnoreExtraProperties
class SpecialQuestionVO(
    @PrimaryKey
    var id: String= "",
    var sq_questions: String ?= ""
)