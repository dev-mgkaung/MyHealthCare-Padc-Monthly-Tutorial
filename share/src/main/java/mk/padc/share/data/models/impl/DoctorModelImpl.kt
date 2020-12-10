package mk.padc.share.data.models.impl

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.networks.ColudFirebaseDatabaseApiImpl
import mk.padc.share.networks.FirebaseApi


object DoctorModelImpl : DoctorModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = ColudFirebaseDatabaseApiImpl

    override fun uploadPhotoToFirebaseStorage(
        image: Bitmap,
        onSuccess: (photoUrl: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
       mFirebaseApi.uploadPhotoToFirebaseStorage(image ,onSuccess,onFailure)
      }

    override fun registerNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mFirebaseApi.registerNewDoctor(
            doctorVO,
            onSuccess = {},
            onFailure = { onFailure(it) })
    }

    override fun getDoctorByEmail(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getDoctor(email,
            onSuccess = {
                mTheDB.doctorDao().deleteAllDoctorData()
                mTheDB.doctorDao().insertNewDoctor(it)
            }, onFailure = { onError(it) })
    }

    override fun getDoctorByEmailFromDB(email: String): LiveData<DoctorVO> {
        return mTheDB.doctorDao().getAllDoctorDataByEmail(email)
    }

    override fun getBrodcastConsultationRequests(speciality: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getBroadcastConsultationRequestBySpeciality(speciality,
                onSuccess = {
                    mTheDB.consultationRequestDao().deleteAllConsultationRequestData()
                    mTheDB.consultationRequestDao().insertConsultationRequestData(it)
                }, onFailure = { onError(it) })
    }

    override fun getBrodcastConsultationRequestsFromDB(speciality: String): LiveData<List<ConsultationRequestVO>> {
        return mTheDB.consultationRequestDao().getAllConsultationRequestDataBySpeciality(speciality)
    }

    override fun getConsultationAcceptListFromDB(doctorId: String): LiveData<List<ConsultationRequestVO>> {
        return mTheDB.consultationRequestDao().getAllConsultationAcceptData(doctorId)
    }

    override fun deleteConsultationRequestById(consulationId: String): LiveData<List<ConsultationRequestVO>> {
         mTheDB.consultationRequestDao().deleteAllConsultationRequestDataById(consulationId)
        return mTheDB.consultationRequestDao().getAllConsultationRequestDataBySpeciality("dentist")
    }

    override fun startConsultation(consulationId: String, dateTime: String, questionAnswerList: List<QuestionAnswerVO>, patientVO: PatientVO, doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.startConsultation(consulationId, dateTime, questionAnswerList, patientVO, doctorVO,
                onSuccess = {}, onFailure = { onFailure(it) })
    }

    override fun acceptRequest(status: String, consulationId: String,  questionAnswerList: List<QuestionAnswerVO>, patientVO: PatientVO, doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.acceptRequest(status,consulationId,  questionAnswerList, patientVO, doctorVO,
                onSuccess = {}, onFailure = { onFailure(it) })
    }

}