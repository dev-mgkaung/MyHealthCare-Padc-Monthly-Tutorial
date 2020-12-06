package mk.padc.share.data.models.impl

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.vos.*
import mk.padc.share.networks.ColudFirebaseDatabaseApiImpl
import mk.padc.share.networks.FirebaseApi


object PatientModelImpl : PatientModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = ColudFirebaseDatabaseApiImpl

    override fun uploadPhotoToFirebaseStorage(image: Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.uploadPhotoToFirebaseStorage(image ,onSuccess,onFailure)
    }

    override fun registerNewPatient(
        patientVO: PatientVO,
        onSuccess: (patientVO: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mFirebaseApi.registerNewPatient(
            patientVO,
            onSuccess = {},
            onFailure = { onFailure(it) })
    }

    override fun getSpecialities(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    ) {

        mFirebaseApi.getSpecialities(onSuccess = {
            mTheDB.specialityDao().deleteSpecialities()
            mTheDB.specialityDao().insertSpecialities(it)
        }, onFailure = { onError(it) })

    }

    override fun getSpecialitiesFromDB(): LiveData<List<SpecialitiesVO>> {
          return mTheDB.specialityDao().getAllSpecialitiesData()
    }

    override fun getRecentDoctors(   patientId : String, onSuccess: (List<RecentDoctorVO>) -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getRecentlyConsultatedDoctor(
            patientId,
            onSuccess = {
            mTheDB.recentDoctorDao().deleteAllRecentDoctorData()
            mTheDB.recentDoctorDao().insertRecentDoctorList(it)
        }, onFailure = { onError(it) })

    }

    override fun getRecentDoctorsFromDB(): LiveData<List<RecentDoctorVO>> {
        return mTheDB.recentDoctorDao().getAllRecentDoctorData()
    }

    override fun getSpecialQuestionBySpeciality(
        speciality: String,
        onSuccess: (List<SpecialQuestionVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialQuestionsBySpeciality(speciality,
            onSuccess = {
            mTheDB.specialQuestionDao().deleteSpecialQuestions()
            mTheDB.specialQuestionDao().insertSpecialQuestions(it)
        }, onFailure =
        { onError(it) })
    }

    override fun getSpecialQuestionBySpecialityFromDB() : LiveData<List<SpecialQuestionVO>> {
        return mTheDB.specialQuestionDao().getAllSpecialQuestionsData()
    }

    override fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.sendBroadCastConsultationRequest(speciality,
            questionAnswerList,
            patientVO,
            dateTime,
            onSuccess = {

            }, onFailure = { onFailure(it) })
    }

}