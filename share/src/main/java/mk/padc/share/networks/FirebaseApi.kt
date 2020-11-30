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

    fun getBroadConsultationRequest(
        onSuccess: (consulationRequest : List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun startConsultation(
        onSuccess: (consulation : List<String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun finishConsultation(
        onSuccess: (consulation : List<String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun preScriptionMedicine(
        onSuccess: (consulation : List<String>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun sendBroadCastConsultationRequest(
        speciality:String,
        questionAnswerVO: GeneralQuestionVO,
        patientVO: PatientVO,
        dateTime : String,
        onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun sendDirectRequest(
        questionAnswerVO: GeneralQuestionVO,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)

    fun checkoutMedicine(onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getRecentlyConsultatedDoctor(onSuccess: (doctor:DoctorVO) -> Unit,onFailure: (String) -> Unit)

    fun getConsultationChat(onSuccess: (List<ConsulationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun getAllCheckMessage(documentId: String,onSuccess: (List<ChatMessageVO>) -> Unit,onFailure: (String) -> Unit)

    fun sendMessage(documentId:String,messageVO: ChatMessageVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)


    fun acceptRequest(doctor:DoctorVO,
                      onSuccess: () -> Unit,
                      onFailure: (String) -> Unit)

    fun finishConsultation(onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun preScribeMedicine(medicine:MedicineVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getGeneralQuestion(onSuccess: (List<GeneralQuestionVO>) -> Unit,onFailure: (String) -> Unit)

}