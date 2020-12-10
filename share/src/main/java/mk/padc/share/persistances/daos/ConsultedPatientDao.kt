package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.ConsultedPatientVO

@Dao
interface ConsultedPatientDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultedPatient(patient: ConsultedPatientVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultedPatient(patient: List<ConsultedPatientVO>)

    @Query("select * from consulted_patient")
    fun getConsultedPatient(): LiveData<List<ConsultedPatientVO>>

    @Query("select * from consulted_patient WHERE id = :id")
    fun getConsultedPatientBy(id: String): LiveData<ConsultedPatientVO>

    @Query("DELETE FROM consulted_patient")
    fun deleteConsultedPatient()


}