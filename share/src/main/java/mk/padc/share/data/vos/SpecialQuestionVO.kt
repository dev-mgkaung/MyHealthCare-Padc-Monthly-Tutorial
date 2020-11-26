package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "special_question")
@IgnoreExtraProperties
class SpecialQuestionVO(
    @PrimaryKey
    var sq_id: String= "",
    var sq_question: String = ""
)