package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.RecentDoctorVO


@Dao
interface RecentDoctorDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewRecentDoctor(doctorVO: RecentDoctorVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentDoctorList(doctorList: List<RecentDoctorVO>)

    @Query("select * from recent_doctors")
    fun getAllRecentDoctorData(): LiveData<List<RecentDoctorVO>>

    @Query("select * from recent_doctors WHERE id = :id")
    fun getAllRecentDoctorDataBy(id: String): LiveData<RecentDoctorVO>

    @Query("DELETE FROM recent_doctors")
    fun deleteAllRecentDoctorData()


}