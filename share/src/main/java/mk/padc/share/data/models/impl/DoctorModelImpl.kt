package mk.padc.share.data.models.impl

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.models.BaseModel
import mk.padc.share.data.models.DoctorModel
import mk.padc.share.data.vos.*
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

    override fun getConsultationByDoctorId(doctorId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsulationChatForDoctor(doctorId,
                onSuccess = {
                    mTheDB.consultationChatDao().deleteAllConsultationChatData()
                    mTheDB.consultationChatDao().insertConsultationChatData(it)

                }, onFailure = { onError(it) })
    }

    override fun getConsultationChat(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsulationChatById(consulationId,
                onSuccess = {
                    mTheDB.consultationChatDao().deleteAllConsultationChatData()
                    mTheDB.consultationChatDao().insertConsultationChatData(it)
                }, onFailure = { onError(it) })
    }


    override fun getConsultationByDoctorIdFromDB(doctorId: String): LiveData<List<ConsultationChatVO>> {
        return mTheDB.consultationChatDao().getAllConsultationChatDataByDoctorId(doctorId)
    }

    override fun getConsultationChatFromDB(consulationId: String): LiveData<ConsultationChatVO> {
        return mTheDB.consultationChatDao().getAllConsultationChatDataBy(consulationId)
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

    override fun getConsultationByConsulationRequestIdFromDB(consultation_request_id : String): LiveData<ConsultationRequestVO> {
        return mTheDB.consultationRequestDao().getConsultationRequestByConsultationRequestId(consultation_request_id)
    }

    override fun getConsultationByConsulationRequestId(consultation_request_id: String, onSuccess: (consultationRequestVO: ConsultationRequestVO) -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getBroadcastConsultationRequest(consultation_request_id,
                onSuccess = {
                    mTheDB.consultationRequestDao().deleteAllConsultationRequestData()
                    mTheDB.consultationRequestDao().insertConsultationRequest(it)
                }, onFailure = { onError(it) })
    }

    override fun getConsultedPatient(
        doctorId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getConsultedPatient(doctorId,onSuccess = {
            mTheDB.consultedPatientDao().deleteConsultedPatient()
            mTheDB.consultedPatientDao().insertConsultedPatient(it)
        }, onFailure= {})
    }

    override fun getConsultedPatientFromDB(): LiveData<List<ConsultedPatientVO>> {
      return mTheDB.consultedPatientDao().getConsultedPatient()
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

    override fun getGeneralQuestionTemplate(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getGeneralQuestion( onSuccess = {
            mTheDB.generalQuestionTemplateDao().deleteAllGeneralQuestionTemplate()
            mTheDB.generalQuestionTemplateDao().insertGeneralQuestionTemplateList(it)
        }, onFailure = {})
    }

    override fun getGeneralQuestionTemplateFromDB(): LiveData<List<GeneralQuestionTemplateVO>> {
        return mTheDB.generalQuestionTemplateDao().getAllGeneralQuestionTemplateData()
    }

    override fun saveMedicalRecord(consultationChatVO: ConsultationChatVO, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.saveMedicalRecord(consultationChatVO, onSuccess = {
        }, onFailure = {})
    }

    override fun getAllMedicine(speciality: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getAllMedicine(speciality,  onSuccess = {
            mTheDB.medicalDao().deleteAllMedicine()
            mTheDB.medicalDao().insertMedicalDataList(it)
        }, onFailure = {})
    }

    override fun getAllMedicineFromDB(): LiveData<List<MedicineVO>> {
        return mTheDB.medicalDao().getAllMedicine()
    }

    override fun finsishConsultation(consultationChatVO: ConsultationChatVO, prescriptionList: List<PrescriptionVO>, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.finishConsultation(consultationChatVO,prescriptionList, onSuccess = {
        }, onFailure = {})
    }

    override fun getPrescription(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getPrescription(consulationId,  onSuccess = {
            mTheDB.prescriptionDao().deleteAllPrescriptionData()
            mTheDB.prescriptionDao().insertPrescriptionList(it)
        }, onFailure = {})
    }

    override fun getPrescriptionFromDB(): LiveData<List<PrescriptionVO>> {
        return mTheDB.prescriptionDao().getAllPrescriptionData()
    }

    override fun addDoctorInfo(doctorVO: DoctorVO, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.updateDoctorData(doctorVO, onSuccess = {}, onFailure = { onError(it) })
    }

}