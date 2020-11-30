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

    @Query("select * from specialities")
    fun getAllSpecialitiesData(): LiveData<List<SpecialitiesVO>>

    @Query("select * from specialities WHERE id = :id")
    fun getAllSpecialitiesBy(id: String): LiveData<SpecialitiesVO>

    @Query("DELETE FROM specialities")
    fun deleteSpecialities()


}