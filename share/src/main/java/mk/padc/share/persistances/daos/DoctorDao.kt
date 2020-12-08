package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO

@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewDoctor(doctorVO: DoctorVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctorList(doctorList: List<DoctorVO>)

    @Query("select * from doctors")
    fun getAllDoctorData(): LiveData<List<DoctorVO>>

    @Query("select * from doctors WHERE email = :email")
    fun getAllDoctorDataByEmail(email: String): LiveData<DoctorVO>

    @Query("DELETE FROM doctors")
    fun deleteAllDoctorData()

}