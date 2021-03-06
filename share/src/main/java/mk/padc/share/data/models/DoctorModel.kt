package mk.padc.share.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.vos.*
import mk.padc.share.networks.FirebaseApi
import mk.padc.share.networks.responses.NotiResponse
import mk.padc.share.networks.responses.NotificationVO

interface DoctorModel
{
    var mFirebaseApi : FirebaseApi

    fun sendNotificationToPatient(
            notificationVO: NotificationVO,
            onSuccess: (notiResponse: NotiResponse) -> Unit,
            onFailure: (String) -> Unit
    )

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun registerNewDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getDoctorByEmail( doctorId : String,
                           onSuccess: () -> Unit,
                           onError: (String) -> Unit)

    fun getDoctorByEmailFromDB(email: String) : LiveData<DoctorVO>

    fun getBrodcastConsultationRequests(speciality: String ,
                                        onSuccess: () -> Unit,
                                        onError: (String) -> Unit)

    fun getBrodcastConsultationRequestsFromDB(speciality: String) : LiveData<List<ConsultationRequestVO>>

    fun getConsultationByDoctorId(doctorId: String, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getConsultationChat(consulationId:  String,  onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getConsultationChatFromDB(consulationId : String) : LiveData<ConsultationChatVO>


    fun getConsultationByDoctorIdFromDB(doctorId : String) : LiveData<List<ConsultationChatVO>>


    fun deleteConsultationRequestById(consulationId : String)  : LiveData<List<ConsultationRequestVO>>

    fun startConsultation(
            consulationId: String,
            dateTime: String,
            questionAnswerList: List<QuestionAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )

    fun acceptRequest(
            status: String,
            consulationId: String,
            questionAnswerList: List<QuestionAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )

    fun  getConsultationByConsulationRequestIdFromDB(consultation_request_id : String) : LiveData<ConsultationRequestVO>

    fun  getConsultationByConsulationRequestId(consultation_request_id : String , onSuccess: (consultationRequestVO :ConsultationRequestVO) -> Unit, onError: (String) -> Unit)


    fun getConsultedPatient(doctorId: String ,
                                        onSuccess: () -> Unit,
                                        onError: (String) -> Unit)

    fun  getConsultedPatientFromDB(doctorId: String) : LiveData<List<ConsultedPatientVO>>

    fun sendChatMessage( messageVO: ChatMessageVO , consulationId: String ,
                         onSuccess: () -> Unit,
                         onError: (String) -> Unit)

    fun getChatMessage(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getAllChatMessageFromDB () : LiveData<List<ChatMessageVO>>

    fun getGeneralQuestionTemplate( onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getGeneralQuestionTemplateFromDB () : LiveData<List<GeneralQuestionTemplateVO>>

    fun saveMedicalRecord( consultationChatVO: ConsultationChatVO, onSuccess: () -> Unit, onError: (String) -> Unit)


    fun getAllMedicine(speciality: String ,onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getAllMedicineFromDB() : LiveData<List<MedicineVO>>

    fun finsishConsultation( consultationChatVO: ConsultationChatVO , prescriptionList : List<PrescriptionVO> ,onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getPrescription(consulationId : String ,onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getPrescriptionFromDB( consulationId : String) : LiveData<List<PrescriptionVO>>

    fun addDoctorInfo(doctorVO: DoctorVO, onSuccess: () -> Unit, onError: (String) -> Unit)
}