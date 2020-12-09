package mk.padc.share.persistances.daos


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.ConsultationChatVO
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.PatientVO


@Dao
interface ConsultationRequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultationRequest(consultationRequestVO: ConsultationRequestVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultationRequestData(consultationRequestList: List<ConsultationRequestVO>)

    @Query("select * from consultation_request")
    fun getAllConsultationRequestData(): LiveData<List<ConsultationRequestVO>>

    @Query("select * from consultation_request WHERE speciality = :speciality")
    fun getAllConsultationRequestDataBySpeciality(speciality: String): LiveData<List<ConsultationRequestVO>>

    @Query("DELETE FROM consultation_request")
    fun deleteAllConsultationRequestData()

}