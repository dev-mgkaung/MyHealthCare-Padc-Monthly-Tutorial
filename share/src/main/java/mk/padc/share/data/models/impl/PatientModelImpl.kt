package mk.padc.share.data.models.impl

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.models.PatientModel
import mk.padc.share.data.vos.*
import mk.padc.share.networks.ColudFirebaseDatabaseApiImpl
import mk.padc.share.networks.FirebaseApi
import mk.padc.share.networks.responses.NotiResponse
import mk.padc.share.networks.responses.NotificationVO


object PatientModelImpl : PatientModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = ColudFirebaseDatabaseApiImpl

    override fun sendBroadcastToDoctor(notificationVO: NotificationVO, onSuccess: (notiResponse: NotiResponse) -> Unit, onFailure: (String) -> Unit) {
        mApi.sendFcm(notificationVO)
                .map { it }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let { data ->
                        onSuccess(it)
                    }
                }, {
                    onFailure(it.localizedMessage ?: "ERROR MESSAGE")
                })
    }

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

    override fun getPatientByEmail(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getPatient(email,
            onSuccess = {
            mTheDB.patientDao().deleteAllPatientData()
            mTheDB.patientDao().insertNewPatient(it)
        }, onFailure = { onError(it) })
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

    override fun getPatientByEmailFromDB(email : String): LiveData<PatientVO> {
        return mTheDB.patientDao().getAllPatientDataByEmail(email)
    }


    override fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.sendBroadCastConsultationRequest(speciality,
            questionAnswerList,
            patientVO,
            doctorVO,
            dateTime,
            onSuccess = {}, onFailure = { onFailure(it) })
    }

    override fun getConsultationAccepts(patientId : String , onSuccess: (List<ConsultationRequestVO>) -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getBroadcastConsultationRequestByPatient(
                patientId,
                onSuccess = {
                    mTheDB.consultationRequestDao().deleteAllConsultationRequestData()
                    mTheDB.consultationRequestDao().insertConsultationRequestData(it)
                }, onFailure =
        { onError(it) })
    }

    override fun getConsultationAcceptsFromDB(): LiveData<List<ConsultationRequestVO>> {
        return mTheDB.consultationRequestDao().getConsultationAcceptData("accept")
    }

    override fun joinedChatRoomPatient(
        consultation_chat_id: String,
        consultationRequestVO: ConsultationRequestVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.startConsultationChatPatient(
            consultation_chat_id,
            consultationRequestVO,
            onSuccess = {}, onFailure =
            { onError(it) })
    }

    override fun getConsultationChat(
        consulationId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getConsulationChatById(consulationId,
            onSuccess = {
                mTheDB.consultationChatDao().deleteAllConsultationChatData()
                mTheDB.consultationChatDao().insertConsultationChatData(it)
            }, onFailure = { onError(it) })
    }

    override fun getConsultationChatFromDB(consulationId: String): LiveData<ConsultationChatVO> {
        return mTheDB.consultationChatDao().getAllConsultationChatDataBy(consulationId)
    }

    override fun sendChatMessage(
        messageVO: ChatMessageVO,
        consulationId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.sendMessage(consulationId,messageVO, onSuccess = {}, onFailure= {})
    }

    override fun getChatMessage(consulationId: String ,onSuccess: () -> Unit,
                                onError: (String) -> Unit ){
       mFirebaseApi.getAllChatMessage(consulationId, onSuccess = {
            mTheDB.chatMessageDao().deleteAllChatMessageData()
            mTheDB.chatMessageDao().insertChatMessages(it)
        }, onFailure = {})
    }

    override fun getAllChatMessageFromDB(): LiveData<List<ChatMessageVO>> {
        return mTheDB.chatMessageDao().getAllChatMessage()
    }

    override fun addPatientInfo(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.updatePatientData(patientVO, onSuccess = {}, onFailure = { onError(it) })
    }

    override fun getConsultationChatByPatientId(patientId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsulationChatByPatientId(patientId,
                onSuccess = {
                    mTheDB.consultationChatDao().deleteAllConsultationChatData()
                    mTheDB.consultationChatDao().insertConsultationChatData(it)
                }, onFailure = { onError(it) })
    }

    override fun getConsultationChatByPatientIdFromDB(patientId: String): LiveData<List<ConsultationChatVO>> {
        return mTheDB.consultationChatDao().getAllConsultationChatDataByPatientId(patientId)
    }

    override fun getPrescription(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        DoctorModelImpl.mFirebaseApi.getPrescription(consulationId,  onSuccess = {
            mTheDB.prescriptionDao().deleteAllPrescriptionData()
            mTheDB.prescriptionDao().insertPrescriptionList(it)
        }, onFailure = {})
    }

    override fun getPrescriptionFromDB(): LiveData<List<PrescriptionVO>> {
        return mTheDB.prescriptionDao().getAllPrescriptionData()
    }
}
