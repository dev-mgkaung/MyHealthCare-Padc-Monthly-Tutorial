package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "general_question_template")
@IgnoreExtraProperties
class GeneralQuestionTemplateVO(
    @PrimaryKey
    var gq_id: String= "",
    var type: String = "",
    var title: String = "",
    var questions: ArrayList<QuestionVO> = arrayListOf()
)

class QuestionVO(
    var question : String ? =""
)