package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.DoctorVO


@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctorData(doctorVO: DoctorVO)

    @Query("select * from doctor")
    fun getAllDoctorData(): LiveData<List<DoctorVO>>

}