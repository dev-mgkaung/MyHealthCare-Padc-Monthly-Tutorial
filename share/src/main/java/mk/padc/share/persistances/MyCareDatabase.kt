package mk.padc.share.persistances

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mk.padc.share.data.vos.*
import mk.padc.share.persistances.daos.*
import mk.padc.share.utils.DATABASE_NAME

@Database(
    entities = [SpecialitiesVO::class,PatientVO::class, DoctorVO::class,RecentDoctorVO::class,
        ConsultationRequestVO::class, ConsultationChatVO::class,GeneralQuestionTemplateVO::class,
        CheckoutVO::class , SpecialQuestionVO::class, QuestionAnswerVO::class ,ConsultedPatientVO::class,
        ChatMessageVO::class , MedicineVO::class, PrescriptionVO::class],
    version = 7,
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
    abstract fun specialityDao(): SpecialityDao
    abstract fun checkoutDao(): CheckoutDao
    abstract fun consultationChatDao(): ConsultationChatDao
    abstract fun consultationRequestDao(): ConsultationRequestDao
    abstract fun generalQuestionTemplateDao(): GeneralQuestionTemplateDao
    abstract fun specialQuestionDao (): SpecialQuestionDao
    abstract fun questionAnswerDao (): QuestionAnswerDao
    abstract  fun recentDoctorDao () : RecentDoctorDao
    abstract fun consultedPatientDao () : ConsultedPatientDao
    abstract fun chatMessageDao() : ChatMessageDao
    abstract fun medicalDao() : MedicalDao
    abstract fun prescriptionDao() : PrescriptionDao
}