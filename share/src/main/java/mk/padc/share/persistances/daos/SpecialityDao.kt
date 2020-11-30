package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.SpecialitiesVO

@Dao
interface SpecialityDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeciality(specialitiesVO:  SpecialitiesVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(specialities: List<SpecialitiesVO>)

    @Query("select * from general_question_template")
    fun getAllSpecialitiesData(): LiveData<List<SpecialitiesVO>>

    @Query("select * from general_question_template WHERE id = :id")
    fun getAllSpecialitiesBy(id: String): LiveData<SpecialitiesVO>

    @Query("DELETE FROM general_question_template")
    fun deleteSpecialities()


}