package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "general_question")
@IgnoreExtraProperties
class GeneralQuestionVO(
    @PrimaryKey
    var sq_id: String= "",
    var gq_questions: String = "",
    var type: String = ""
)