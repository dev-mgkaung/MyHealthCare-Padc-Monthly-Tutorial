package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO


@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPatient(patientVO: PatientVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatientList(patientList: List<PatientVO>)

    @Query("select * from patients")
    fun getAllPatientData(): LiveData<List<PatientVO>>

    @Query("select * from patients WHERE id = :id")
    fun getAllPatientDataBy(id: String): LiveData<PatientVO>

    @Query("DELETE FROM patients")
    fun deleteAllPatientData()

    @Query("DELETE FROM patients WHERE id = :id")
    fun deletePatientById(id: String)
}