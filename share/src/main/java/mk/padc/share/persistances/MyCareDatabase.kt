package mk.podcast.com.persistances

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.persistances.daos.DoctorDao
import mk.padc.share.persistances.daos.PatientDao
import mk.padc.share.utils.DATABASE_NAME

@Database(
    entities = [PatientVO::class, DoctorVO::class],
    version = 1,
    exportSchema = false
)
abstract class MyCareDatabase : RoomDatabase() {
    companion object {

        var dbInstance: MyCareDatabase? = null

        fun getDBInstance(context: Context): MyCareDatabase {
            when (dbInstance) {
                null -> {
                    dbInstance =
                        Room.databaseBuilder(context, MyCareDatabase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun doctorDao(): DoctorDao
    abstract fun patientDao(): PatientDao

}