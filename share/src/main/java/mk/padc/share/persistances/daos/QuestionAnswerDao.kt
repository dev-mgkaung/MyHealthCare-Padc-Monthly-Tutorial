package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.QuestionAnswerVO

@Dao
interface QuestionAnswerDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGeneralQuestion(general_question: QuestionAnswerVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGeneralQuestion(general_question: List<QuestionAnswerVO>)

    @Query("select * from question_answer")
    fun getAllGeneralQuestion(): LiveData<List<QuestionAnswerVO>>

    @Query("select * from question_answer WHERE id = :id")
    fun getAllGeneralQuestionBy(id: String): LiveData<QuestionAnswerVO>

    @Query("DELETE FROM question_answer")
    fun deleteGeneralQuestion()


}