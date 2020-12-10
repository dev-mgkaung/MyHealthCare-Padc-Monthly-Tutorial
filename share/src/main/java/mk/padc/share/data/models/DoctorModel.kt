package mk.padc.share.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import mk.padc.share.data.vos.ConsultationRequestVO
import mk.padc.share.data.vos.DoctorVO
import mk.padc.share.data.vos.PatientVO
import mk.padc.share.data.vos.QuestionAnswerVO
import mk.padc.share.networks.FirebaseApi

interface DoctorModel
{
    var mFirebaseApi : FirebaseApi

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

    fun getConsultationAcceptListFromDB(speciality: String) : LiveData<List<ConsultationRequestVO>>

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
}