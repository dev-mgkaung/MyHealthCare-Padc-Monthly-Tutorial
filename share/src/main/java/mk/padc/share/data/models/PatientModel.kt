package mk.padc.share.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.vos.*
import mk.padc.share.networks.FirebaseApi
import mk.padc.share.networks.responses.NotiResponse
import mk.padc.share.networks.responses.NotificationVO

interface PatientModel
{
    var mFirebaseApi : FirebaseApi

    fun sendBroadcastToDoctor(
            notificationVO: NotificationVO,
            onSuccess: (notiResponse: NotiResponse) -> Unit,
            onFailure: (String) -> Unit
    )

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    fun registerNewPatient(patientVO: PatientVO ,onSuccess: (patientVO: PatientVO) -> Unit, onFailure: (String) -> Unit)

    fun getPatientByEmail( patientId: String,
                        onSuccess: () -> Unit,
                        onError: (String) -> Unit)

    fun getSpecialities(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialitiesFromDB(): LiveData<List<SpecialitiesVO>>

    fun getRecentDoctors(
        patientId : String,
        onSuccess: (List<RecentDoctorVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getRecentDoctorsFromDB(): LiveData<List<RecentDoctorVO>>

    fun getSpecialQuestionBySpeciality(
        speciality : String,
        onSuccess: (List<SpecialQuestionVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialQuestionBySpecialityFromDB() : LiveData<List<SpecialQuestionVO>>

    fun getPatientByEmailFromDB(email: String) : LiveData<PatientVO>

    fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)
    
    
    fun  getConsultationAccepts(
             patientId: String,
             onSuccess: (List<ConsultationRequestVO>) -> Unit,
             onError: (String) -> Unit)

    fun  getConsultationAcceptsFromDB() : LiveData<List<ConsultationRequestVO>>

    fun joinedChatRoomPatient(consultation_chat_id: String, consultationRequestVO: ConsultationRequestVO,
                              onSuccess: () -> Unit,
                              onError: (String) -> Unit)

    fun getConsultationChat(consulationId:  String,  onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getConsultationChatFromDB(consulationId : String) : LiveData<ConsultationChatVO>

    fun sendChatMessage( messageVO: ChatMessageVO , consulationId: String ,
                         onSuccess: () -> Unit,
                         onError: (String) -> Unit)

    fun getChatMessage(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getAllChatMessageFromDB () : LiveData<List<ChatMessageVO>>

    fun addPatientInfo(patientVO: PatientVO, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getConsultationChatByPatientId(patientId:  String,  onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getConsultationChatByPatientIdFromDB(patientId : String) : LiveData<List<ConsultationChatVO>>

    fun getPrescription(consulationId : String ,onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getPrescriptionFromDB( ) : LiveData<List<PrescriptionVO>>
}