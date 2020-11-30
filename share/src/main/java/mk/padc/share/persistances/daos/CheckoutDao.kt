package mk.padc.share.persistances.daos


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.CheckoutVO
import mk.padc.share.data.vos.PatientVO


@Dao
interface CheckoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckout(checkoutVO: CheckoutVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckoutData(checkoutList: List<CheckoutVO>)

    @Query("select * from checkout")
    fun getAllCheckoutData(): LiveData<List<CheckoutVO>>

    @Query("select * from checkout WHERE id = :id")
    fun getAllCheckoutDataBy(id: String): LiveData<CheckoutVO>

    @Query("DELETE FROM checkout")
    fun deleteAllCheckoutData()

}