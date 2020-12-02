package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.SpecialQuestionVO


@Dao
interface SpecialQuestionDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialQuestions(special_questions: SpecialQuestionVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialQuestions(special_questions: List<SpecialQuestionVO>)

    @Query("select * from special_questions")
    fun getAllSpecialQuestionsData(): LiveData<List<SpecialQuestionVO>>

    @Query("select * from special_questions WHERE id = :id")
    fun getAllSpecialQuestionsBy(id: String): LiveData<SpecialQuestionVO>

    @Query("DELETE FROM special_questions")
    fun deleteSpecialQuestions()


}