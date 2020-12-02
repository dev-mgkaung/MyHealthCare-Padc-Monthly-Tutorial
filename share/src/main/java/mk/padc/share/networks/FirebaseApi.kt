package mk.padc.share.networks

import android.graphics.Bitmap
import mk.padc.share.data.vos.*


interface FirebaseApi {

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun addOrUpdatePatientData(patientVO: PatientVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addOrUpdateDoctorData(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun registerNewDoctor(doctorVO: DoctorVO,
        onSuccess: (doctorList: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun registerNewPatient(patientVO: PatientVO,
        onSuccess: (patientList: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    )

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

    fun getBroadConsultationRequest(
        onSuccess: (consulationRequest : List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun startConsultation(
        dateTime: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


    fun finishConsultation(
        onSuccess: (consulation : List<String>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun sendDirectRequest(
        questionAnswerVO: QuestionAnswerVO,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)

    fun checkoutMedicine(onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getRecentlyConsultatedDoctor(patientId : String, onSuccess: (doctorList : List<DoctorVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsultationChat( patientId: String , onSuccess: (List<ConsultationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun getAllCheckMessage(documentId: String,onSuccess: (List<ChatMessageVO>) -> Unit,onFailure: (String) -> Unit)

    fun sendMessage(consulationChatId: String, messageVO: ChatMessageVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)


    fun acceptRequest(doctor:DoctorVO,
                      onSuccess: () -> Unit,
                      onFailure: (String) -> Unit)

    fun finishConsultation(onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun preScribeMedicine(consulationId : String, prescriptionVO: PrescriptionVO , routineVO: RoutineVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getGeneralQuestion(onSuccess: (List<QuestionAnswerVO>) -> Unit, onFailure: (String) -> Unit)

}