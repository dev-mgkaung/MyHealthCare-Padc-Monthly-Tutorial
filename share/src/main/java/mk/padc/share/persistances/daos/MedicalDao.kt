package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.GeneralQuestionTemplateVO
import mk.padc.share.data.vos.MedicineVO


@Dao
interface MedicalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicalData(data: MedicineVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicalDataList(list: List<MedicineVO>)

    @Query("select * from medicines")
    fun getAllMedicine(): LiveData<List<MedicineVO>>

    @Query("select * from medicines WHERE id = :id")
    fun getAllMedicineByData(id: String): LiveData<MedicineVO>

    @Query("DELETE FROM medicines")
    fun deleteAllMedicine()

}