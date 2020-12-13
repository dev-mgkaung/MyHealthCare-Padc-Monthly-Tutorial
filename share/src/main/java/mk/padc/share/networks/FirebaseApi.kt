package mk.padc.share.networks

import android.graphics.Bitmap
import mk.padc.share.data.vos.*


interface FirebaseApi {

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun registerNewDoctor(doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


    fun updatePatientData(patientVO: PatientVO ,onSuccess: () -> Unit,
                          onFailure: (String) -> Unit
    )

    fun updateDoctorData(doctorVO: DoctorVO ,onSuccess: () -> Unit,
                          onFailure: (String) -> Unit
    )

    fun registerNewPatient(patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPatient(patientId : String ,
                   onSuccess: (patientVO: PatientVO) -> Unit,
                   onFailure: (String) -> Unit)

    fun getDoctor(doctorId : String ,
                   onSuccess: (doctorVO: DoctorVO) -> Unit,
                   onFailure: (String) -> Unit)

    fun getSpecialities (
        onSuccess: (specialities : List<SpecialitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    
    fun getSpecialQuestionsBySpeciality(
        speciality : String,
        onSuccess: (specialQuestionList : List<SpecialQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendBroadCastConsultationRequest(
        speciality:String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        dateTime : String,
        onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getBroadcastConsultationRequest(
        consulation_request_id : String,
        onSuccess: (consulationRequest : ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getBroadcastConsultationRequestByPatient(
            patientId :String,
            onSuccess: (consulationRequest : List<ConsultationRequestVO>) -> Unit,
            onFailure: (String) -> Unit
    )

    fun getBroadcastConsultationRequestBySpeciality(
            speciality : String,
            onSuccess: (list : List<ConsultationRequestVO>) -> Unit,
            onFailure: (String) -> Unit
    )

    fun startConsultation(
        consulationId: String,
        dateTime: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendDirectRequest(
        questionAnswerVO: QuestionAnswerVO,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)

    fun checkoutMedicine(prescriptionList : List<PrescriptionVO>,deliveryAddressVO: DeliveryAddressVO,
                         doctorVO: DoctorVO, patientVO: PatientVO , total_price : Int,
                         onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getRecentlyConsultatedDoctor(patientId : String, onSuccess: (doctorList : List<RecentDoctorVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsultationChat( patientId: String , onSuccess: (List<ConsultationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun getAllChatMessage(consulationId: String ,onSuccess: (List<ChatMessageVO>) -> Unit,onFailure: (String) -> Unit)

    fun getPrescription(consulationId: String ,onSuccess: (List<PrescriptionVO>) -> Unit,onFailure: (String) -> Unit)


    fun sendMessage(consulationChatId: String, messageVO: ChatMessageVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)


    fun acceptRequest(
            status : String,
            consulationId: String,
            questionAnswerList: List<QuestionAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit)

    fun finishConsultation(consultationChatVO: ConsultationChatVO, prescriptionList : List<PrescriptionVO>, onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun preScribeMedicine(consulationId : String, prescriptionVO: PrescriptionVO ,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getGeneralQuestion(onSuccess: (List<GeneralQuestionTemplateVO>) -> Unit, onFailure: (String) -> Unit)

    fun getAllMedicine(speciality: String ,onSuccess: (List<MedicineVO>) -> Unit, onFailure: (String) -> Unit)


    fun startConsultationChatPatient(consulationChatId: String, consultationRequestVO: ConsultationRequestVO ,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getConsultedPatient(doctorId : String,onSuccess: (List<ConsultedPatientVO>) -> Unit,onFailure: (String) -> Unit)

    fun addConsultedPatient(doctorId: String ,patientId : String,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getConsulationChatForDoctor(doctorId: String ,onSuccess: (List<ConsultationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsulationChatById(consulationId : String ,onSuccess: (List<ConsultationChatVO>) -> Unit,onFailure: (String) -> Unit)


    fun saveMedicalRecord(consultationChatVO: ConsultationChatVO ,onSuccess: () -> Unit,onFailure: (String) -> Unit)


   }