package mk.padc.share.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import mk.padc.share.utils.general_question_template

@Entity(tableName = general_question_template)
@IgnoreExtraProperties
class GeneralQuestionTemplateVO(
    @PrimaryKey
    var id: String= "",
    var type: String = "",
    var question:  String = ""
)
